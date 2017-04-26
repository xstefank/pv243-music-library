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
            return $http.get("/music/api/album/" + id).then(function (response) {
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
        getSongsByAlbum: function (id) {
            return $http.get("/music/api/song/album/" + id).then(function (response) {
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
            .when('/', {templateUrl: 'partials/home.html', controller: 'HomeController'})
            .when('/about', {templateUrl: 'partials/about.html'})
            .when('/albumDetail/:id?', {templateUrl: 'partials/albumDetail.html', controller: 'albumDetailCtrl'})
            .when('/albumsOverview', {templateUrl: 'partials/albumsOverview.html', controller: 'albumsOverviewCtrl'})
            .when('/artistsOverview', {templateUrl: 'partials/artistsOverview.html', controller: 'artistsOverviewCtrl'})
            .when('/editAlbum/:id?', {templateUrl: 'partials/editAlbum.html', controller: 'editAlbumCtrl'})
            .when('/editArtist/:id?', {templateUrl: 'partials/editArtist.html', controller: 'editArtistCtrl'})
            .otherwise({redirectTo: '/'});
    }]);