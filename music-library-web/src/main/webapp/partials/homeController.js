'use strict';

angular.module('app')
    .controller('HomeController', ['$scope', 'commonTools', function ($scope, commonTools) {
        $scope.title = 'Welcome to the Music Library!';

        commonTools.getArtistNumberSongs().then(function (response) {
            $scope.artistSongs = response;
            $scope.labelsArtistSongs = [];
            $scope.dataArtistSongs = [];
            Object.keys($scope.artistSongs).forEach(function (key) {
                $scope.labelsArtistSongs.push(key);
                $scope.dataArtistSongs.push($scope.artistSongs[key]);
            });
        }, function (response) {
            $scope.alerts.push({type: 'danger', title: 'Error ' + response.status, msg: response.statusText});
        });

        commonTools.getArtistNumberAlbums().then(function (response) {
            $scope.artistAlbums = response;
            $scope.labelsArtistAlbums = [];
            $scope.dataArtistAlbums = [];
            Object.keys($scope.artistAlbums).forEach(function (key) {
                $scope.labelsArtistAlbums.push(key);
                $scope.dataArtistAlbums.push($scope.artistAlbums[key]);
            });
        }, function (response) {
            $scope.alerts.push({type: 'danger', title: 'Error ' + response.status, msg: response.statusText});
        });

        commonTools.getGenreNumberSongs().then(function (response) {
            $scope.genreSongs = response;
            $scope.labelsGenreSongs = [];
            $scope.dataGenreSongs = [];
            Object.keys($scope.genreSongs).forEach(function (key) {
                $scope.labelsGenreSongs.push(key);
                $scope.dataGenreSongs.push($scope.genreSongs[key]);
            });
        }, function (response) {
            $scope.alerts.push({type: 'danger', title: 'Error ' + response.status, msg: response.statusText});
        });

    }]);

