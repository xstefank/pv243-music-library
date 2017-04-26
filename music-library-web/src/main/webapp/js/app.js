'use strict';

angular.module('app', [
    'ngRoute'
]).factory('commonTools', ['$http', function ($http) {
    return {
        getAlbums: function () {
            return $http.get("/music/api/album").then(function (response) {
                return response.data;
            });
        },
        getAlbum: function (id) {
            return $http.get("/music/api/album" + id).then(function (response) {
                return response.data;
            });
        },
        getArtists: function () {
            return $http.get("/music/api/artist").then(function (response) {
                return response.data;
            });
        },
        getArtist: function (id) {
            return $http.get("/music/api/artist/" + id).then(function (response) {
                return response.data;
            });
        },
        formatDateForRest: function (dateToFormat) {
            // return [dateToFormat.getFullYear(), (dateToFormat.getMonth() + 1), dateToFormat.getDate()];
            if (dateToFormat) {
                return null;
            }
            var m = "";
            if (dateToFormat.getMonth() + 1 > 9) {
                m = (dateToFormat.getMonth() + 1);
            } else {
                m = "0" + (dateToFormat.getMonth() + 1);
            }
            var d = "";
            if (dateToFormat.getDate() > 9) {
                d = dateToFormat.getDate();
            } else {
                d = "0" + dateToFormat.getDate();
            }

            return dateToFormat.getFullYear() + "-" + m + "-" + d;
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
            .when('/', {templateUrl: 'partials/home.html', controller: 'HomeController'})
            .when('/about', {templateUrl: 'partials/about.html'})
            .when('/albumsOverview', {templateUrl: 'partials/albumsOverview.html', controller: 'albumsOverviewCtrl'})
            .when('/artistsOverview', {templateUrl: 'partials/artistsOverview.html', controller: 'artistsOverviewCtrl'})
            .when('/editAlbum', {templateUrl: 'partials/editAlbum.html', controller: 'editAlbumCtrl'})
            .when('/editArtist/:id?', {templateUrl: 'partials/editArtist.html', controller: 'editArtistCtrl'})
            .otherwise({redirectTo: '/'});
    }]);