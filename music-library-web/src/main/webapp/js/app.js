'use strict';


var app = angular.module('app', [
    'ngRoute'
]);


app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/', { templateUrl: 'partials/home.html', controller: 'HomeController' })
            .when('/about', { templateUrl: 'partials/about.html' })
            .otherwise({ redirectTo: '/' });
    }]);