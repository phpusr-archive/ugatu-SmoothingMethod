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

controllers.controller('TaskCreateController', ['$scope', '$http', '$location', function($scope, $http, $location) {

    var taskData = [];
    $scope.taskData = taskData;

    /** Изменение кол-ва данных задачи */
    $scope.changeCountData = function() {
        var countData = parseInt($scope.countData);

        var times = Math.abs(countData - taskData.length);
        var push = countData > taskData.length;

        for (var i=0; i < times; i++) {
            if (push) {
                taskData.push(new Data(i, i))
            } else {
                taskData.pop();
            }
        }
    };

    /** TODO */
    $scope.save = function(url) {
        var obj = {obj: {one: 'test', two: 'test2'}};

        $http.post(url, obj).success(function(data) {
            //console.log('OK', data);
        });
    };

    $scope.countData = 5;
    $scope.changeCountData();

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
