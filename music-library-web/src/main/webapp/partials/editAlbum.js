'use strict';

angular.module('app')
    .controller('editAlbumCtrl', ['$scope', '$http', '$location', '$routeParams', 'commonTools', 'createUpdateTools', function ($scope, $http, $location, $routeParams, commonTools, createUpdateTools) {
        $scope.alerts = [];
        $scope.album = {};

        $scope.artists = [];
        $scope.doing = 'create';
        $scope.changed = false;
        if ($routeParams.id) {
            commonTools.getAlbum($routeParams.id).then(function (response) {
                $scope.album = response;
                $scope.oldAlbum = angular.copy($scope.album);
            }, function (response) {
                $scope.alerts.push({type: 'danger', title: 'Error '+ response.status, msg: response.statusText});
            });

            $scope.doing = 'update';
        }

        commonTools.getArtists().then(function (response) {
            $scope.artists = response;

        });

        $scope.update = function() {
            if ($scope.doing === 'create') {
                $http({
                    url: '/music/api/album',
                    method: "POST",
                    data: $scope.album
                }).then(function (response) {
                    $scope.status = "New album successfully created.";
                    createUpdateTools.setAlerts([{type: 'success', title: 'Successful!', msg: $scope.status}]);
                    $location.path("/albumsOverview");
                }, function (response) {
                    $scope.alerts.push({type: 'danger', title: 'Cannot update album! Error '+ response.status, msg: response.statusText});
                });
            } else {
                $scope.messageBuilder = 'You have successfully updated these fields [';
                if ($scope.album.title !== $scope.oldAlbum.title) {
                    $scope.messageBuilder += 'title, ';
                    $scope.changed = true;
                }
                if ($scope.album.artist.id !== $scope.oldAlbum.artist.id) {
                    $scope.messageBuilder += 'artist, ';
                    $scope.changed = true;
                }
                if ($scope.album.dateOfRelease !== $scope.oldAlbum.dateOfRelease) {
                    $scope.messageBuilder += 'release date, ';
                    $scope.changed = true;
                }
                if ($scope.album.commentary !== $scope.oldAlbum.commentary) {
                    $scope.messageBuilder += 'commentary, ';
                    $scope.changed = true;
                }
                if ($scope.changed) {
                    $http({
                        url: '/music/api/album/' + $scope.album.id,
                        method: "PUT",
                        data: $scope.album
                    }).then(function (response) {
                        $scope.status = $scope.messageBuilder.substring(0, $scope.messageBuilder.length - 2) + "] of album.";
                        createUpdateTools.setAlerts([{type: 'success', title:'Successfull!', msg: $scope.status}]);
                        $location.path("/albumsOverview");
                    }, function (response) {
                        $scope.alerts.push({type: 'danger', title: 'Cannot update album! Error '+ response.status, msg: response.statusText});
                    });
                } else {
                    createUpdateTools.setAlerts([{type: 'info', title: "No change!", msg: 'There have been no changes.'}]);
                    $location.path("/albumsOverview");
                }
            }
        };

        $scope.reset = function(form) {
            if (form) {
                form.$setPristine();
                form.$setUntouched();
            }
            $scope.album = null;
        };

        $scope.removeAlbum = function () {
            if(!$scope.album) {
                confirm('Cannot remove Album immediately.\nAlbum probably have not been saved yet.');
                $location.path("/albumsOverview");
            } else {
                if(confirm('You are above to completely remove Album:\n'+ $scope.album.title +'\n\nAre you sure?')) {
                    $http({
                        url: '/music/api/album/' + $scope.album.id,
                        method: "DELETE"
                    }).then(function () {
                        createUpdateTools.setAlerts([{type: 'success', title:'Removed!', msg: 'You have successfully deleted '+ $scope.album.title +' from system.'}]);
                        $location.path("/albumsOverview");
                    }, function () {
                        createUpdateTools.setAlerts([{type: 'danger', title:'Cannot Remove!', msg: $scope.album.title +' still has some songs assigned.'}]);
                        $location.path("/albumsOverview");
                    })
                }
            }
        };

        $scope.closeAlert = function(index) {
            $scope.alerts.splice(index, 1);
        };

    }]);
