'use strict';

angular.module('app', [
    'ngRoute', 'ngFileUpload', 'ngSanitize', 'chart.js'
]).factory('commonTools', ['$http', function ($http) {
    return {
        //Album
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
        getSongsByAlbum: function (id) {
            return $http.get("/music/api/song/album/" + id).then(function (response) {
                return response.data;
            });
        },
        createAlbum: function (data){
            return $http.post("/music/api/album", data).then(function (response){
                return response.data;
            });
        },
        updateAlbum: function (data, id){
            return $http.put("/music/api/album/" + id, data).then(function (response){
                    return response.data;
            });
        },
        deleteAlbum: function (id){
             return $http.delete("/music/api/album/" + id).then(function (response){
                    return response.data;
              });
         },
        getAlbumsByArtist: function (id){
            return $http.get("/music/api/artist/" + id + "/albums").then(function (response){
                return response.data;
            });
        },

        //Artist
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
        createArtist: function (data){
            return $http.post("/music/api/artist", data).then(function (response){
                return response.data;
            });
        },
        updateArtist: function (data, id){
            return $http.put("/music/api/artist/" + id, data).then(function (response){
                return response.data;
            });
        },
        deleteArtist: function (id){
            return $http.delete("/music/api/artist/" + id).then(function (response){
                return response.data;
            });
        },

        //Song
        getSongs: function () {
            return $http.get("/music/api/song").then(function (response) {
                return response.data;
            });
        },
        getSong: function (id) {
            return $http.get("/music/api/song/" + id).then(function (response) {
                return response.data;
            });
        },
        createSong: function (data){
            return $http.post("/music/api/song", data).then(function (response){
                return response.data;
            });
        },
        updateSong: function (data, id){
            return $http.put("/music/api/song/" + id, data).then(function (response){
                return response.data;
            });
        },
        deleteSong: function (id){
            return $http.delete("/music/api/song/" + id).then(function (response){
                return response.data;
            });
        },
        getSongsForUser: function (){
            return $http.get("/music/api/song/user").then(function (response){
                return response.data;
            });
        },
        addSongToUser: function (id) {
            return $http.put("/music/api/song/" + id + "/user").then(function (response){
                return response.data;
            });
        },
        removeSongFromUser: function (id) {
            return $http.delete("/music/api/song/" + id + "/user").then(function (response){
                return response.data;
            });
        },

        //Genre
        getGenres: function (){
            return $http.get("/music/api/genre").then(function (response){
                return response.data;
            });
        },

        //User
        logout: function () {
            return $http.get("/music/api/user/logout").then(function (response){
                return response.data;
            });
        },
        getPrincipal: function () {
            return $http.get("/music/api/user/role").then(function (response){
                return response.data;
            });
        },

        //Overview
        getArtistNumberSongs: function () {
            return $http.get("/music/api/overview/artistSongs").then(function (response){
                return response.data;
            });
        },
        getArtistNumberAlbums: function () {
            return $http.get("/music/api/overview/artistAlbums").then(function (response){
                return response.data;
            });
        },
        getGenreNumberSongs: function () {
            return $http.get("/music/api/overview/genreSongs").then(function (response){
                return response.data;
            });
        },
        runBatch: function () {
            return $http.get("/music/api/artist/runbatch").then(function (response){
                return response.data;
            });
        }

    };
}]).service('createUpdateTools', function () {
    var alerts = [];
    return {
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
            .when('/artistDetail/:id?', {templateUrl: 'partials/artistDetail.html', controller: 'artistDetailCtrl'})
            .when('/artistsOverview', {templateUrl: 'partials/artistsOverview.html', controller: 'artistsOverviewCtrl'})
            .when('/editAlbum/:id?', {templateUrl: 'partials/editAlbum.html', controller: 'editAlbumCtrl'})
            .when('/editArtist/:id?', {templateUrl: 'partials/editArtist.html', controller: 'editArtistCtrl'})
            .when('/editSong/:id?', {templateUrl: 'partials/editSong.html', controller: 'editSongCtrl'})
            .when('/songsOverview', {templateUrl: 'partials/songsOverview.html', controller: 'songsOverviewCtrl'})
            .when('/myLibrary', {templateUrl: 'partials/myLibrary.html', controller: 'myLibraryCtrl'})
            .when('/addSong', {templateUrl: 'partials/addSong.html', controller: 'addSongCtrl'})
            .otherwise({redirectTo: '/'});
    }]);