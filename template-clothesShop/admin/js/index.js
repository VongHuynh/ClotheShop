app.run([
  "$http",
  function ($http) {
    var authData = sessionStorage.getItem("token");
    if (authData) {
      //set header
      $http.defaults.headers.common["Authorization"] =
        "Bearer " + JSON.parse(authData).tokens.access_token;
    }
  },
]);
let urlImages = `https://firebasestorage.googleapis.com/v0/b/clothesshop-1e4f2.appspot.com/o`;
app.controller(
  "formController",
  function ($scope, $rootScope, $http, $location) {
    $scope.formProduct = {};
    var authData = sessionStorage.getItem("token");
    if(authData){
      $scope.user =  JSON.parse(authData).username;
    }
    function getProducts() {
      $http({
        method: "get",
        url: urlUser + "products",
        headers: { Accept: "*/*", "Content-type": "application/json" },
      }).then(
        function success(response) {
          $scope.products = response.data.products;
        },
        function error(response) {
          $scope.error = response.data;
          console.log(response.data);
        }
      );
    }
    //fill hình ảnh
    $scope.images = function (fileName) {
      return `${urlImages}/${fileName}?alt=media`;
    };

    $scope.edit = function (product) {
      $scope.formProduct = product;
      $scope.update = true;
      $http({
        method: "get",
        url: `${urlUser}productstyle/show/${product.idCategory}`,
      }).then(
        function success(response) {
          $scope.productStyles = response.data;
        },
        function error(response) {
          console.log(response.data);
        }
      );

      $scope.formProduct.idProductStyle = "" + product.idProductStyle;
      $scope.formProduct.idCategory = "" + product.idCategory;
    };
    $scope.update = false;
    $scope.clearForm = function () {
      $scope.formProduct = {};
      $scope.update = false;
    };

    $scope.upload = function (file) {
      $scope.uploadImage = file[0];
    };

    $scope.submit = function () {
      if($scope.uploadImage)
      $scope.formProduct.productPhoto = $scope.uploadImage.name;
      if ($scope.update) {
        $http({
          method: "put",
          url:`${urlAdmin}products`,
          data: JSON.stringify($scope.formProduct)
        }).then(
          function success(response) {
            getProducts();
          },
          function error(response) {
            console.log(response.data);
          }
        );
        $scope.clearForm();
      } else {
        $http({
          method: "post",
          url: `${urlAdmin}products`,
          data: JSON.stringify($scope.formProduct)
        }).then(
          function success(response) {
            console.log(response.data)
            $scope.products.push(response.data);
            var form = new FormData();
            form.append("file", $scope.uploadImage)
            $http({
              method: "post",
              url: `${urlAdmin}upload`,
              data: form,
              transformRequest: angular.identity,
                    headers: { 'Content-Type': undefined }
            }).then(
              function success(response) {
                
              
              },
              function error(response) {
                console.log(response.data);
              }
            );


          },
          function error(response) {
            console.log(response.data);
          }
        );
        $scope.clearForm();
      }
    };

    $scope.productStyle = function (idCategory) {
      $http({
        method: "get",
        url: `${urlUser}productstyle/show/${idCategory}`,
      }).then(
        function success(response) {
          $scope.productStyles = response.data;
          $scope.formProduct.idProductStyle =
            "" + $scope.productStyles[0].idProductStyle;
        },
        function error(response) {
          console.log(response.data);
        }
      );
    };

    function category() {
      $http({
        method: "get",
        url: `${urlUser}categories`,
      }).then(
        function success(response) {
          $scope.categories = response.data;
          $scope.formProduct.idCategory = "" + $scope.categories[0].idCategory;
          $scope.productStyle($scope.formProduct.idCategory);
        },
        function error(response) {
          console.log(response.data);
        }
      );
    }

    $scope.remove = function (idProduct) {
      $http({
        method: "DELETE",
        url: `${urlAdmin}products/${idProduct}`,
      }).then(
        function success(response) {
          getProducts();
        },
        function error(response) {
          console.log(response.data);
        }
      );
    };

    getProducts();
    category();
  }
);
