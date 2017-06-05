'use strict';

angular.module('app')
    .controller('HomeController', ['$scope', function ($scope) {
        $scope.title = 'Welcome to the Music Library!';
        $scope.labels = ['RHCP', "LP"];

        $scope.data = [
            '15', '20'
        ];


    }]);


// angular.module("app", ["chart.js"]).controller("HomeController",
//     function ($scope) {
//         $scope.title = 'Welcome to the Music Library!';
//         $scope.labels = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
//         $scope.series = ['Series A', 'Series B'];
//
//         $scope.data = [
//             [65, 59, 80, 81, 56, 55, 40],
//             [28, 48, 40, 19, 86, 27, 90]
//         ];
//     });