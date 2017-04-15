'use strict';

angular.module('app')
    .controller('HomeController', ['$scope', function ($scope) {
        $scope.title = 'Welcome to the Music Library!';
    }]);