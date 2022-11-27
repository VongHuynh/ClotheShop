app.controller('cartControl',function($scope,$rootScope,$http,$location){
    let authData = sessionStorage.getItem('token');
    let username = JSON.parse(authData).username;
    $scope.carts = JSON.parse(localStorage.getItem(username));

    $scope.removeCart = function(idCart){
        delete $scope.carts[idCart];
        localStorage.setItem(username,JSON.stringify($scope.carts));
        $rootScope.countItems();//cập nhật lại số lương sản phẩm trong giỏ
    }
    $scope.amounts = 0;
    $scope.amount = function(idCart,price,quantity){
        
        $scope.amounts = price * quantity;
        //tự động xóa cart khi số lượng bằng 0
        if(quantity <= 0)
        $scope.removeCart(idCart)

        //cập nhật lại số lượng sản phầm người dùng
        $scope.carts[idCart].quantity = quantity;
        $scope.carts[idCart].amount = $scope.amounts;
        localStorage.setItem(username,JSON.stringify($scope.carts));

        return $scope.amounts;
    }
    
    $scope.total = function(){
        let total = 0;
        for(let [key,value] of Object.entries($scope.carts)){
            total += value.amount
        }
        return total;
    }

    
    if($scope.carts){
      $scope.pay = function(){
        
        $http({
            method: "post",
            url:
              localhost + "cart/payment?address=đồng nai",
            headers: { Accept: "*/*" },
            data: JSON.stringify($scope.carts)
          }).then(
            function success(response) {
              $scope.bills = response.data;
              $location.path("user/invoice.html")
              delete $scope.carts;
              localStorage.setItem(username,JSON.stringify({}));
              $rootScope.countItems();//cập nhật lại số lương sản phẩm trong giỏ
              $scope.total();
            },
            function error(response) {
              console.log(response.data);
            }
          );
    }
    }
    

}) 