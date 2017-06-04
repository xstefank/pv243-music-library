'use strict';

angular.module('app')
    .controller('addSongCtrl', ['$scope', '$location', 'commonTools', 'createUpdateTools', function ($scope, $location, commonTools, createUpdateTools) {

        $scope.init = function () {
            commonTools.getArtists().then(function (response) {
                $scope.artists = response;
            }, function (response) {
                $scope.alerts.push({type: 'danger', title: 'Error ' + response.status, msg: response.statusText});
            });

            commonTools.getSongs().then(function (response) {
                $scope.songs = response;
            }, function (response) {
                $scope.alerts.push({type: 'danger', title: 'Error ' + response.status, msg: response.statusText});
            });

            commonTools.getGenres().then(function (response) {
                $scope.genres = response;
            }, function (response) {
                $scope.alerts.push({type: 'danger', title: 'Error ' + response.status, msg: response.statusText});
            });

            $scope.alerts = angular.copy(createUpdateTools.getAlerts());
            createUpdateTools.deleteAlerts();
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };

        $scope.addSong = function (id) {
            commonTools.addSongToUser(id).then(function () {
                $scope.status = "Song was added to your library.";
                $scope.alerts.push({type: 'success', title: 'Successful!', msg: $scope.status});
            }, function (response) {
                $scope.alerts.push({type: 'danger', title: 'Error ' + response.status, msg: response.statusText});
            });
        };

        $scope.getAlbumsByArtist = function(id){
            commonTools.getAlbumsByArtist(id).then(function (response) {
                $scope.albums = response;
            }, function (response) {
                $scope.alerts.push({type: 'danger', title: 'Error ' + response.status, msg: response.statusText});
            });
        };

        $scope.filterSongs = function(){
            for(var i =0; i < $scope.songs.length; i++){
                if ($scope.artist && $scope.songs[i].artist.id !== $scope.artist.id){
                    $scope.songs[i].invisible = true;
                    continue;
                }
                if ($scope.album && $scope.songs[i].album.id !== $scope.album.id){
                    $scope.songs[i].invisible = true;
                    continue;
                }
                if ($scope.genre && $scope.songs[i].genre.id !== $scope.genre.id){
                    $scope.songs[i].invisible = true;
                    continue;
                }
                $scope.songs[i].invisible = false;
            }
        };
        $scope.init();
    }]);
