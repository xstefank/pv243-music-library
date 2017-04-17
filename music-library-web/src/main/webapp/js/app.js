'use strict';


angular.module('app', [
    'ngRoute'
]).factory('commonTools', ['$http', function ($http) {
    return {
        getAlbumsAvailable: function () {
            return $http.get("/music/album").then(function(response) {
                return response.data;
            });
        },
        getArtistsAvailable: function () {
            return $http.get("/music/artist").then(function(response) {
                return response.data;
            });
        }
    };
}]).service('createUpdateTools', function () {
    var item = null;
    var alerts = [];
    return {
        getItem: function () {
            return item;
        },
        setItem: function (newItem) {
            item = newItem;
        },
        deleteItem: function () {
            item = null;
        },
        getAlerts: function () {
            return alerts;
        },
        addAlert: function (newAlert) {
            alerts.push(newAlert);
        },
        setAlerts: function (newAlerts) {
            alerts = newAlerts;
        },
        deleteAlerts: function () {
            alerts = [];
        }
    }
}).config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/', { templateUrl: 'partials/home.html', controller: 'HomeController' })
            .when('/about', { templateUrl: 'partials/about.html' })
            .when('/albumsOverview', { templateUrl: 'partials/albumsOverview.html', controller: 'albumsOverviewCtrl' })
            .when('/artistsOverview', { templateUrl: 'partials/artistsOverview.html', controller: 'artistsOverviewCtrl' })
            .otherwise({ redirectTo: '/' });
    }]);