(function () {
  "use strict";

  app.config(config).run(run);
  config.$inject = ["$routeProvider", "$locationProvider"];
  function config($routeProvider, $locationProvider) {
    $routeProvider
      .when("/home", {
        templateUrl: "user/home.html",
        controller: "homeControl",
      })
      .when("/shop", {
        templateUrl: "user/shop.html",
        controller: "shopControl",
      })
      .when("/cart", {
        templateUrl: "user/cart.html",
        controller: "cartControl",
      })
      .when("/productDetail", {
        templateUrl: "user/product-details.html",
        controller: "productDetailControl",
      })
      .when("/login", {
        templateUrl: "user/login.html",
        controller: "loginControl",
      }).when("/invoice",{
        templateUrl: "user/invoice.html",
        controller: "invoiceControl",
      })
    .otherwise({ redirectTo: '/home' });
  }

  run.$inject = ["$rootScope", "$location", "$http", "$window"];
  function run($rootScope, $location, $http, $window) {
    var authData = $window.sessionStorage.getItem("token");
    if (authData) {
      //set header
      $http.defaults.headers.common["Authorization"] =
        "Bearer " + JSON.parse(authData).tokens.access_token;

        $rootScope.username = authData.username;
    }

    $rootScope //nếu ko phải admin mà thay đổi url sang admin sẽ redirect về home
    .$on('$locationChangeStart', function (event, next, current) {
      let roles;
      if(authData)
      roles = JSON.parse(authData).roles;
      

        if(roles.includes("admin") == -1 && next.toLowerCase().includes("admin")){
          alert("Bạn không được quyền vào trang này")
        }
        else if(next.toLowerCase().includes("admin"))
        window.location.href = "admin/index.html";
          
    });

    //access tonken hết hạn thì đăng nhập lại
    $rootScope.$on("ajaxError", function (e, data) {
      sessionStorage.removeItem("token");
      $location.path("/login");
    });
  }

})();
