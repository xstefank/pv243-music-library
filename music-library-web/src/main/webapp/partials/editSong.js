'use strict';

angular.module('app')
    .controller('editSongCtrl', ['$scope', '$location', '$routeParams', 'commonTools', 'createUpdateTools', function ($scope, $location, $routeParams, commonTools, createUpdateTools) {

        $scope.alerts = [];
        $scope.song = {};
        $scope.changed = false;
        $scope.doing = 'Create';
        if ($routeParams.id) {
            commonTools.getSong($routeParams.id).then(function (response) {
                $scope.song = response;
                $scope.oldSong = angular.copy($scope.song);

                if ($scope.song.artist) {
                    $scope.updateAlbums();
                } else {
                    commonTools.getAlbums().then(function (response) {
                        $scope.albums = response;
                    }, function (response) {
                        $scope.alerts.push({
                            type: 'danger',
                            title: 'Error ' + response.status,
                            msg: response.statusText
                        });
                    });
                }
            }, function (response) {
                $scope.alerts.push({type: 'danger', title: 'Error ' + response.status, msg: response.statusText});
            });

            $scope.doing = 'Update';
        }
        commonTools.getArtists().then(function (response) {
            $scope.artists = response;
        }, function (response) {
            $scope.alerts.push({type: 'danger', title: 'Error ' + response.status, msg: response.statusText});
        });

        commonTools.getGenres().then(function (response) {
            $scope.genres = response;
        }, function (response) {
            $scope.alerts.push({type: 'danger', title: 'Error ' + response.status, msg: response.statusText});
        });

        $scope.updateAlbums = function () {
            commonTools.getAlbumsByArtist($scope.song.artist.id).then(function (response) {
                $scope.albums = response;
            }, function (response) {
                $scope.alerts.push({type: 'danger', title: 'Error ' + response.status, msg: response.statusText});
            });
        };

        $scope.update = function () {
            if ($scope.doing === 'Create') {
                commonTools.createSong($scope.song).then(function (response) {
                    $scope.status = "New song successfully created.";
                    createUpdateTools.setAlerts([{type: 'success', title: 'Successful!', msg: $scope.status}]);
                    $location.path("/songsOverview");
                }, function (response) {
                    $scope.alerts.push({type: 'danger', title: 'Error ' + response.status, msg: response.statusText});
                });
            } else {
                $scope.messageBuilder = 'You have successfully updated these fields [';
                if ($scope.song.title !== $scope.oldSong.title) {
                    $scope.messageBuilder += 'title, ';
                    $scope.changed = true;
                }
                if ($scope.song.album.id !== $scope.oldSong.album.id) {
                    $scope.messageBuilder += 'album, ';
                    $scope.changed = true;
                }
                if ($scope.song.artist.id !== $scope.oldSong.artist.id) {
                    $scope.messageBuilder += 'artist, ';
                    $scope.changed = true;
                }
                if ($scope.song.youtubeLink !== $scope.oldSong.youtubeLink) {
                    $scope.messageBuilder += 'youtube link, ';
                    $scope.changed = true;
                }
                if ($scope.song.genre.id !== $scope.oldSong.genre.id) {
                    $scope.messageBuilder += 'genre, ';
                    $scope.changed = true;
                }
                if ($scope.changed) {
                    commonTools.updateSong($scope.song, $scope.song.id).then(function (response) {
                        $scope.status = $scope.messageBuilder.substring(0, $scope.messageBuilder.length - 2) + "] of song.";
                        createUpdateTools.setAlerts([{type: 'success', title: 'Successful!', msg: $scope.status}]);
                        $location.path("/songsOverview");
                    }, function (response) {
                        $scope.alerts.push({
                            type: 'danger',
                            title: 'Error ' + response.status,
                            msg: response.statusText
                        });
                    });
                } else {
                    createUpdateTools.setAlerts([{
                        type: 'info',
                        title: "No change!",
                        msg: 'There have been no changes.'
                    }]);
                    $location.path("/songssOverview");
                }
            }
        };

        $scope.reset = function (form) {
            if (form) {
                form.$setPristine();
                form.$setUntouched();
            }
            $scope.song = null;
        };

        $scope.removeSong = function () {
            if (!$scope.song) {
                confirm('Cannot remove song immediately.\nSong probably have not been saved yet.');
                $location.path("/songsOverview");
            } else {
                if (confirm('You are above to completely remove Song:\n' + $scope.song.title + '\n\nAre you sure?')) {
                    commonTools.deleteSong($scope.song.id).then(function () {
                        createUpdateTools.setAlerts([{
                            type: 'success',
                            title: 'Removed!',
                            msg: 'You have successfully deleted ' + $scope.song.title + ' from system.'
                        }]);
                        $location.path("/songsOverview");
                    }, function () {
                        createUpdateTools.setAlerts([{
                            type: 'danger',
                            title: 'Cannot Remove!',
                            msg: $scope.song.title + ' cannot be removed'
                        }]);
                        $location.path("/songsOverview");
                    })
                }
            }
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };

    }]);
