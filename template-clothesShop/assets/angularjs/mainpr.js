function load_js(source, status) {
    var head = document.getElementsByTagName('body')[0];
    var script = document.createElement('script');
    script.src = source;
    head.appendChild(script);
}

function setColorLikeProduct(index){
    $('.icon-heart').eq(index).css('color','white')
    $('.favorite').eq(index).css('background-color','red')
    $('.ion-ios-heart').css('color','red');
}

function setColorUnlikeProduct(index){
    $('.icon-heart').eq(index).css('color','black')
    $('.favorite').eq(index).css('background-color','white')
    $('.ion-ios-heart').css('color','grey');
}



app.controller('myControl', function ($route, $scope, $rootScope, $http,$location) {
    //fill hình ảnh
    $rootScope.images = function(fileName){
        return`${urlImages}/${fileName}?alt=media`;
    }
    
    //phân trang
    $rootScope.paginationAll = function(totalPage) {
        $("#pagination").twbsPagination({
          totalPages: totalPage,
          visiblePages: 9,
          autoHidePrevious: true,
          autoHideNext: true,
          onPageClick: function (event, page) {
            $rootScope.allProduct(page -1, 9, true);
          },
        });
      }



    //show product
    $rootScope.allProduct = function (page=0,size=9,unchecked) {
        $http({
            method: "get",
            url: localhost + "products?pageNum="+page+"&size="+size,
            headers: { 'Accept': '*/*' }
        }).then(function success(response) {
            let page = response.data;
            $rootScope.totalPage = page.totalPages;
            $rootScope.products = page.products;
            $rootScope.currentPage = page.currentPage;
        }, function error(response) {
            console.log(response.data)
        })


        //bỏ tích checkbox khi tìm theo màu
        if(unchecked == true){
            $('input:checkbox').each(function () {
                $(this).prop('checked',false)
            });

            $('.size').each(function(){
              $(this).css("color", "#999");
              $(this).css("border-color", "white");
            })
            
        }
    }

    $rootScope.allProduct();
    

    //product detail
    $rootScope.showModalProductDetail = function (idProduct) {
        $rootScope.idProduct = idProduct;
        
        //hiện màu của sản phẩm hiện có
        $http({
            method: "get",
            url: localhost + "colors/byproduct/" + idProduct,
            headers: { 'Accept': '*/*'}
        }).then(function success(response) {
            $rootScope.colors = response.data;
            $rootScope.selectedColor ={color: $rootScope.colors[0]};
            //hiện sản phẩm theo id lên modal
            $http({
                method: "get",
                url: localhost + "productcolor/detail/" + idProduct + "/" + $rootScope.colors[0].idColor,
                headers: { 'Accept': '*/*'}
            }).then(function success(response) {
                $rootScope.product = response.data;
                //hiện size sản phẩm hiện có
                $http({
                    method: "get",
                    url: localhost + "size/" + $rootScope.product.idProductsColors,
                    headers: { 'Accept': '*/*'}
                }).then(function success(response) {
                    $rootScope.sizes = response.data;
                    $rootScope.selectedSize ={size: $rootScope.sizes[0]} ;
                    console.log($rootScope.selectedSize)
                }, function error(response) {
                    console.log('something wrong.....')
                })
            }, function error(response) {
                console.log('something wrong.....')
            })

        }, function error(response) {
            console.log('something wrong.....')
        })
    }

    //thay đổi ảnh màu của sản phầm
    $rootScope.changeColorProduct = function () {
        //thay đổi màu sản phẩm
        $http({
            method: "get",
            url: localhost + "productcolor/detail/" + $rootScope.idProduct + "/" + $rootScope.selectedColor.color.idColor,
            headers: { 'Accept': '*/*'}
        }).then(function success(response) {
            $rootScope.product = response.data;
        }, function error(response) {
            console.log('something wrong.....')
        })
    }
    

    $scope.getIdSize = function (idSize) {
        $rootScope.idSize = idSize
    }


    //đếm số sản phẩm có trong giỏ
    $rootScope.items = 0;
    $rootScope.authData = function(){
       return sessionStorage.getItem('token');
    } 
    if($rootScope.authData()){
        $rootScope.username = JSON.parse($rootScope.authData()).username;
    }
    $rootScope.countItems = function () {
        $rootScope.cart = JSON.parse(localStorage.getItem($rootScope.username));
        if ($rootScope.cart != null)
            $rootScope.items = Object.keys($rootScope.cart).length;
    }


    //add to cart
    $rootScope.addToCart = function () {
        $rootScope.countItems();
        $http({
            method: "post",
            url: localhost + "cart/" + $rootScope.idProduct + "/" + 
            $rootScope.selectedSize.size.idSize + "/" + $rootScope.selectedColor.color.idColor,
            headers: { 'Accept': '*/*'},
            data: $rootScope.cart
        }).then(function success(response) {
            $rootScope.username = JSON.parse($rootScope.authData()).username;
            localStorage.setItem($rootScope.username, JSON.stringify(response.data))
            $rootScope.countItems();
        }, function error(response) {
            console.log('something wrong.....')
        })
    }

    //Yêu thích sản phẩm
    $rootScope.like = function(index,idProduct,username = $rootScope.username){
        $http({
            method: "post",
            url: localhost + "favorites",
            headers: { 'Accept': '*/*'},
            data: JSON.stringify({
                idProduct: idProduct,
                idUsernameAccount:username
            })
        }).then(function success(response) {
            if(response.data === false)
            setColorUnlikeProduct(index);//bỏ thích
            else
            setColorLikeProduct(index);
        }, function error(response) {
            console.log('like product Error')
        })
    }

    //fill các sản phẩm đã thích
    $rootScope.listFavorite = function(){
        $http({
            method: "get",
            url: localhost + "favorites",
            headers: { 'Accept': '*/*' }
        }).then(function success(response) {
            let dataFavorite = response.data;
            $('.favorite').each(function (index){
                dataFavorite.forEach(element => {
                    if($(this).attr('value') == element.idProduct && element.idUsernameAccount == $rootScope.username ){
                        $(this).css('background-color','red')
                        $('.icon-heart').eq(index).css('color','white')
                    }
                });
            })

            dataFavorite.forEach(element => {
                if($('.favoriteDetail').attr('value') == element.idProduct && element.idUsernameAccount == $rootScope.username ){
                    $('.ion-ios-heart').css('color','red');
                    
                }
            });
            
        }, function error(response) {
            console.log("List favorites Error......")
        })
    }



    $rootScope.countItems();



    $rootScope.selectedHoverSize = function(idSize){
        $('.size').each(function(){
            if($(this).attr('value') == idSize){
                $(this).css('color','black')
                $(this).css('border-color','black')
            }else{
                $(this).css('color','#999')
                $(this).css('border-color','white')
            }
       
        })
    }

    

})
//xóa khoảng trắng
app.filter('removeSpaces', [function () {
    return function (string) {
        if (!angular.isString(string)) {
            return string;
        }
        return string.replace(/[\s]/g, '');
    };
}])

