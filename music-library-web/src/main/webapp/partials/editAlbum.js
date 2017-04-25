'use strict';

angular.module('app')
    .controller('editAlbumCtrl', ['$scope', '$http', '$location', 'commonTools', 'createUpdateTools', function ($scope, $http, $location, commonTools, createUpdateTools) {
        commonTools.getArtistsAvailable().then(function (response) {
            $scope.artists = response;
            if ($scope.album.artist) {
                for (var i = 0; i < $scope.artists.length; i++) {
                    if ($scope.album.artist.id === $scope.artists[i].id) {
                        $scope.album.artist = $scope.artists[i];
                        break;
                    }
                }
            }
        });
        $scope.alerts = [];
        $scope.master = {};
        $scope.doing = 'create';
        if (createUpdateTools.getItem()) {
            $scope.album = angular.copy(createUpdateTools.getItem());
            if(createUpdateTools.getItem().dateOfRelease) {
                $scope.album.dateOfRelease = new Date(createUpdateTools.getItem().dateOfRelease);
            }
            createUpdateTools.deleteItem();
            $scope.genuineAlbum = angular.copy($scope.album);
            $scope.doing = 'update';
        }

        $scope.update = function(album) {
            $scope.master = angular.copy(album);
            var data = {
                title: $scope.master.title,
                artist: $scope.master.artist,
                dateOfRelease: commonTools.formatDateForRest($scope.master.dateOfRelease),
                commentary: $scope.master.commentary,
                albumArt: $scope.master.albumArt
            };
            if ($scope.doing === 'create') {
                $http({
                    url: '/music/api/album',
                    method: "POST",
                    data: data
                }).then(function (response) {
                    $scope.status = "New album successfully created.";
                    createUpdateTools.setAlerts([{type: 'success', title: 'Successful!', msg: $scope.status}]);
                    $location.path("/albumsOverview");
                }, function (response) {
                    $scope.alerts.push({type: 'danger', title: 'Error '+ response.status, msg: response.statusText});
                });
            } else {
                $scope.messageBuilder = 'You have successfully updated these fields [';
                $scope.updatingItem = data;
                if (data.title !== $scope.genuineAlbum.title) {
                    $scope.updatingItem.title = data.title;
                    $scope.messageBuilder += 'title, ';
                }
                if (data.artist.id !== $scope.genuineAlbum.artist.id) {
                    $scope.updatingItem.artist = data.artist;
                    $scope.messageBuilder += 'artist, ';
                }
                if (data.dateOfRelease !== commonTools.formatDateForRest($scope.genuineAlbum.dateOfRelease)) {
                    $scope.updatingItem.dateOfRelease = data.dateOfRelease;
                    $scope.messageBuilder += 'release date, ';
                }
                if (data.commentary !== $scope.genuineAlbum.commentary) {
                    $scope.updatingItem.commentary = data.commentary;
                    $scope.messageBuilder += 'commentary, ';
                }
                if ($scope.messageBuilder.includes(', ')) {
                    $http({
                        url: '/music/api/album/' + $scope.genuineAlbum.id,
                        method: "PUT",
                        data: $scope.updatingItem
                    }).then(function (response) {
                        $scope.status = $scope.messageBuilder.substring(0, $scope.messageBuilder.length - 2) + "] of album.";
                        createUpdateTools.setAlerts([{type: 'success', title:'Successfull!', msg: $scope.status}]);
                        $location.path("/albumsOverview");
                    }, function (response) {
                        $scope.alerts.push({type: 'danger', title: 'Error '+ response.status, msg: response.statusText});
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
            if($scope.genuineAlbum) {
                confirm('Cannot remove Album immediately.\nFor removing album we need to know unique identification, which is not available now.');
                $location.path("/albumsOverview");
            } else {
                if(confirm('You are above to completely remove Album:\n'+ $scope.genuineAlbum.title +'\n\nAre you sure?')) {
                    $http({
                        url: '/music/api/album/' + $scope.genuineAlbum.id,
                        method: "DELETE"
                    }).then(function () {
                        createUpdateTools.setAlerts([{type: 'success', title:'Removed!', msg: 'You have successfully deleted '+ $scope.genuineAlbum.title +' from system.'}]);
                        $location.path("/albumsOverview");
                    }, function () {
                        createUpdateTools.setAlerts([{type: 'danger', title:'Cannot Remove!', msg: $scope.genuineAlbum.title +' still has some songs assigned.'}]);
                        $location.path("/albumsOverview");
                    })
                }
            }
        };

        $scope.closeAlert = function(index) {
            $scope.alerts.splice(index, 1);
        };

    }]);
