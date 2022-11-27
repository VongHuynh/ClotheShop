app
  .controller(
    "productDetailControl",
    function ($scope, $rootScope, $http, $route) {
      $scope.getColor = function (colorSelected) {
        $rootScope.selectedColor = { color: colorSelected };
        //thay đổi màu sản phẩm
        $http({
          method: "get",
          url:
            localhost +
            "productcolor/detail/" +
            $rootScope.idProduct +
            "/" +
            $rootScope.selectedColor.color.idColor,
          headers: { Accept: "*/*" },
        }).then(
          function success(response) {
            $rootScope.product = response.data;
            console.log($rootScope.product);
          },
          function error(response) {
            alert(response);
          }
        );
      };
      $scope.getSize = function (sizeSelected) {
        $rootScope.selectedSize = { size: sizeSelected };
        $rootScope.selectedHoverSize($rootScope.selectedSize.size.idSize);
      };

      angular.element(document).ready(function () {
        setColorLikeProduct();
        setColorUnlikeProduct();

        $rootScope.listFavorite();  
      });
    }
  )
  .directive("onFinishRender", function ($timeout, $rootScope) {
    //check nếu ng-repeat đã lặp xong
    return {
      link: function (scope, element, attr) {
        if (scope.$last === true) {
          $timeout(function () {
            //chọn 1 checkbox sẽ hủy bỏ các checkbox khác
            $("input:checkbox").on("change", function () {
              $("input:checkbox").not(this).prop("checked", false);
            });
            //check sẵn 1 màu
            $("input:checkbox").each(function () {
              if (angular.toJson($rootScope.colors[0]) === this.value) {
                $(this).prop("checked", true);
                $rootScope.selectedColor = { color: JSON.parse(this.value) };
              }
            });
            //check sẵn 1 size
            $rootScope.selectedHoverSize($rootScope.sizes[0].idSize);
          });
        }
      },
    };
  });
