app
  .controller("shopControl", function ($scope, $rootScope, $http, $route) {
    $scope.idSize=0
    $scope.idCo=0
    $rootScope.paginationSize = function (totalPage) {
      $("#pagination").twbsPagination({
        totalPages: totalPage,
        visiblePages: 9,
        autoHidePrevious: true,
        autoHideNext: true,
        onPageClick: function (event, page) {
            if($scope.idSize!=0){
                $scope.fillBySize($scope.idSize, page - 1,3);
            }else if($scope.idCo!=0)
                $scope.fillProductByColor($scope.idCo, page -1,1)
            else
            $rootScope.allProduct(page -1, 2, true);

        },
      });
    };

    //   $rootScope.paginationAll = function(totalPage) {
    //     $("#pagination").twbsPagination({
    //       totalPages: totalPage,
    //       visiblePages: 5,
    //       autoHidePrevious: true,
    //       autoHideNext: true,
    //       onPageClick: function (event, page) {
    //         event.preventDefault();
    //         $rootScope.allProduct(page -1, 2, true);
    //       },
    //     });
    //   }
    $scope.allShop = function () {
      $('#pagination').twbsPagination('destroy');
        $scope.idSize = 0
        $scope.idCo = 0
        $rootScope.allProduct(0, 9, true);
       
 
    };

    // $rootScope.allProduct(0, 9, true);

    //lấy toàn bộ size
    $scope.getAllColor = function () {
      $http({
        method: "get",
        url: localhost + "colors",
        headers: { Accept: "*/*" },
      }).then(
        function success(response) {
          $scope.colorsShop = response.data;
        },
        function error(response) {
          alert(response.data);
        }
      );
    };
    // lấy toàn bộ màu
    $scope.getAllSize = function () {
      $http({
        method: "get",
        url: localhost + "sizes",
        headers: { Accept: "*/*" },
      }).then(
        function success(response) {
          $scope.sizesShop = response.data;
        },
        function error(response) {
          alert(response.data);
        }
      );
    };
    //show danh mục và loại
    $scope.categories = function () {
      $http({
        method: "get",
        url: localhost + "categories",
        headers: { Accept: "*/*" },
      }).then(
        function success(response) {
          $scope.categories = response.data;
        },
        function error(response) {
          console.log(response.data);
        }
      );
    };

    //fill sản phẩm theo style
    $scope.fillByProductStyle = function (idCategory, idProductStyle) {
      $http({
        method: "get",
        url:
          localhost + "products/bystyle/" + idProductStyle + "/" + idCategory,
        headers: { Accept: "*/*" },
      }).then(
        function success(response) {
          $rootScope.products = response.data;
        },
        function error(response) {
          console.log(response.data);
        }
      );
      $("input:checkbox").each(function () {
        $(this).prop("checked", false);
      });

      $(".size").each(function () {
        $(this).css("color", "#999");
        $(this).css("border-color", "white");
      });
    };

    $scope.paginationColor = function (idColor) {
      $('#pagination').twbsPagination('destroy');
      $scope.idSize = 0;
      $scope.fillProductByColor(idColor);
  };

    //fill sản phẩm theo màu
    $scope.fillProductByColor = function (idColor, pageNum = 0, size = 1) {
      $scope.idCo = idColor
      $http({
        method: "get",
        url:
          localhost +
          "productcolor?idColor=" +
          idColor +
          "&pageNum=" +
          pageNum +
          "&size=" +
          size,
        headers: { Accept: "*/*" },
      }).then(
        function success(response) {
          let page = response.data;
          $rootScope.totalPage = page.totalPages;
          $rootScope.products = page.products;
          $rootScope.currentPage = page.currentPage;
          //   $("#pagination").removeData("twbs-pagination");
          //   $rootScope.paginationAll($rootScope.totalPage);
        },
        function error(response) {
          console.log(response.data);
        }
      );

      $(".size").each(function () {
        $(this).css("color", "#999");
        $(this).css("border-color", "white");
      });
    };

    $scope.paginationSize = function (idSize, pageNum = 0, size = 9) {
        $scope.idCo = 0;
        $scope.fillBySize(idSize, 0,3);
        $('#pagination').twbsPagination('destroy');
        
    };
  

    //fill sản phẩm theo size
    $scope.fillBySize = function (idSize, pageNum = 0, size = 3) {
      $scope.idSize = idSize;
      $http({
        method: "get",
        url:
          localhost +
          "productcolor/bysize?idSize=" +
          idSize +
          "&pageNum=" +
          pageNum +
          "&size=" +
          size,
        headers: { Accept: "*/*" },
      }).then(
        function success(response) {
          let page = response.data;
          $rootScope.totalPage = page.totalPages;
          $rootScope.products = page.products;
          $rootScope.currentPage = page.currentPage;
        },
        function error(response) {
          console.log(response.data);
        }
      );
      $rootScope.selectedHoverSize(idSize);
      $("input:checkbox").each(function () {
        $(this).prop("checked", false);
      });
    };

    $scope.getAllColor();
    $scope.getAllSize();
    $scope.categories();

    angular.element(document).ready(function () {
      $('#pagination').twbsPagination('destroy');
      $rootScope.paginationAll($rootScope.totalPage);
    });
  })
  .directive("onFinishRenderShop", function ($timeout, $rootScope) {
    //check nếu ng-repeat đã lặp xong
    return {
      link: function (scope, element, attr) {
        if (scope.$last === true) {
          $timeout(function () {
            //chọn 1 checkbox sẽ hủy bỏ các checkbox khác
            $("input:checkbox").on("change", function () {
              $("input:checkbox").not(this).prop("checked", false);
            });
            
            setColorLikeProduct();
            setColorUnlikeProduct();
            $rootScope.paginationSize($rootScope.totalPage);
            $rootScope.listFavorite();
          });

         
        }
      },
    };
  });
