'use strict';

/* Controllers */

var controllers = angular.module('controllers', []);

/** TaskShowController */
controllers.controller('TaskShowController', ['$scope', '$http', '$location', function($scope, $http, $location) {

    /** Обновление содержимого страницы */
    $scope.updateView = function(a) {
        $http.get($location.absUrl() + '.json?a=' + a).success(function(data) {
            console.log('success data:', data);
            if (data.status && data.status.name == 'ArithmeticException') {
                $scope.hasErrors = true;
                $scope.errorMessage  = data.message;
            } else {
                $scope.data = data;
            }
        });
    };

    $scope.updateView();
}]);

/** Изменение кол-ва данных задачи */
function changeCountData(taskData, countData) {
    countData = parseInt(countData);

    var times = Math.abs(countData - taskData.length);
    var push = countData > taskData.length;

    for (var i=0; i < times; i++) {
        if (push) {
            taskData.push(new Data('', ''))
        } else {
            taskData.pop();
        }
    }
}

/** TaskCreateController */
controllers.controller('TaskCreateController', ['$scope', '$http', function($scope, $http) {

    $scope.task = {};
    $scope.taskData = [];

    /** Изменение кол-ва данных задачи */
    $scope.changeCountData = function() {
        changeCountData($scope.taskData, $scope.countData)
    };

    /** Сохранение Задачи и ее данных */
    $scope.save = function(actionSave, actionShow) {
        var dataIn = {task: $scope.task, taskData: $scope.taskData};
        $http.post(actionSave, dataIn).success(function(data) {
            console.log('OK', data);
            if (data.status.name == 'OK') {
                document.location = actionShow + '/' + data.id;
            } else {
                $scope.hasErrors = true;
                $scope.errorMessage = data.message
            }
        });
    };

    //Начальная инициализация
    $scope.countData = 5;
    $scope.changeCountData();
}]);

/** TaskEditController */
controllers.controller('TaskEditController', ['$scope', '$http', '$location', function($scope, $http, $location) {

    /** Обновление содержимого страницы */
    $scope.updateView = function(a) {
        $http.get($location.absUrl() + '.json').success(function(task) {
            console.log('success data:', task);
            $scope.task = task;
            $scope.countData = task.data.length;
            $scope.taskData = task.data;
        });
    };

    /** Изменение кол-ва данных задачи */
    $scope.changeCountData = function() {
        changeCountData($scope.taskData, $scope.countData)
    };

    /** Изменение Задачи и ее данных */
    $scope.update = function(actionUpdate, actionShow) {
        var dataIn = {task: $scope.task, taskData: $scope.taskData};
        $http.put(actionUpdate, dataIn).success(function(data) {
            console.log('success data:', data);
            if (data.status.name == 'OK') {
                document.location = actionShow + '/' + data.id;
            } else {
                $scope.hasErrors = true;
                $scope.errorMessage = data.message
            }
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
