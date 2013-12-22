'use strict';

/* Controllers */

var controllers = angular.module('controllers', []);

controllers.controller('TaskShowController', ['$scope', '$http', '$location', function($scope, $http, $location) {

    /** Обновление содержимого страницы */
    $scope.updateView = function(a) {
        $http.get($location.absUrl() + '.json?a=' + a).success(function(data) {
            console.log('success data:', data);
            if (data.status && data.status.name == 'ArithmeticException') {
                $scope.hasErrors = true;
                $scope.errorMessage  = data.status.message;
            } else {
                $scope.data = data;
            }
        });
    };

    $scope.updateView();

}]);

controllers.controller('TaskCreateController', ['$scope', '$http', '$location', function($scope, $http, $location) {

    $scope.task = {};
    $scope.taskData = [];
    var taskData = $scope.taskData;

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

    /** Сохранение Задачи и ее данных */
    $scope.save = function(actionSave, actionList) {
        var dataIn = {task: $scope.task, taskData: taskData};
        $http.post(actionSave, dataIn).success(function(data) {
            console.log('OK', data);
            if (data.status.name == 'OK') {
                document.location = actionList;
            } else {
                $scope.hasErrors = true;
            }
        });
    };

    //TODO
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
