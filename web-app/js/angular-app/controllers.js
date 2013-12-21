'use strict';

/* Controllers */

var controllers = angular.module('controllers', []);

controllers.controller('TaskShowController', ['$scope', '$http', '$location', function($scope, $http, $location) {

    /** Обновление содержимого страницы */
    $scope.updateView = function(a) {
        $http.get($location.absUrl() + '.json?a=' + a).success(function(data) {
            $scope.data = data;
            console.log(data);
        });
    };

    $scope.updateView();

}]);

//------------Example------------
controllers.controller('PhoneListCtrl', ['$scope', 'Phone',
    function($scope, Phone) {
        $scope.phones = Phone.query();
        $scope.orderProp = 'age';
    }
]);

controllers.controller('PhoneDetailCtrl', ['$scope', '$routeParams', 'Phone',
    function($scope, $routeParams, Phone) {
        $scope.phone = Phone.get({phoneId: $routeParams.phoneId}, function(phone) {
            $scope.mainImageUrl = phone.images[0];
        });

        $scope.setImage = function(imageUrl) {
            $scope.mainImageUrl = imageUrl;
        }
    }
]);
