(function () {
  "use strict";
  app.controller("loginControl", loginController);
  loginController.$inject = ["$location", "$window", "$http", "$scope"];
  function loginController($location, $window, $http, $scope) {
    $scope.loginRequest = {};
    let urlLogin = "http://localhost:8080/api/v1/";
    $scope.login = function () {
      $http({
        method: "post",
        url: urlLogin + "login",
        headers: { Accept: "*/*", "Content-type": "application/json" },
        data: JSON.stringify($scope.loginRequest),
      }).then(
        function success(response) {
          $window.sessionStorage.setItem(
            "token",
            JSON.stringify(response.data)
          );
          let authData = $window.sessionStorage.getItem("token");
          $http.defaults.headers.common["Authorization"] =
            "Bearer " + JSON.parse(authData).tokens.access_token;
          $location.path("/");
        },
        function error(response) {
          $scope.error = response.data;
        }
      );
    };
  }
})();


