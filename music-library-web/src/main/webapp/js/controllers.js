'use strict';

var controllers = angular.module("controllers", []);

controllers.controller("HomeController", ['$scope', function ($scope) {
    $scope.title = 'Welcome to Music Library!';
}]);