'use strict';

angular.module('app')
    .controller('editAlbumCtrl', ['$scope', '$http', '$location', 'commonTools', 'createUpdateTools', function ($scope, $http, $location, commonTools, createUpdateTools) {
        $scope.master = {};
        $scope.doing = 'create';
        if (createUpdateTools.getItem() != null) {
            $scope.album = angular.copy(createUpdateTools.getItem());
            createUpdateTools.deleteItem();
            $scope.genuineAlbum = angular.copy($scope.album);
            $scope.doing = 'update';
        }

        $scope.update = function(album) {
            $scope.master = angular.copy(album);
            var data = {
                title: $scope.master.title,
                commentary: $scope.master.commentary
            };
            if ($scope.doing == 'create') {
                $http({
                    url: '/music/api/album',
                    method: "POST",
                    data: data
                }).then(function (response) {
                    $scope.status = "New album successfully created.";
                    createUpdateTools.setAlerts([{type: 'success', title: 'Successful!', msg: $scope.status}]);
                    $location.path("/albumsOverview");
                }, function (response) {
                    $scope.status = "Cannot create, "+ response.status;
                });
            } else {
                $scope.messageBuilder = 'You have successfully updated these fields [';
                $scope.updatingItem = {};
                if (data.title != $scope.genuineAlbum.title) {
                    $scope.updatingItem.title = data.title;
                    $scope.messageBuilder += 'title, ';
                }
                if (data.commentary != $scope.genuineAlbum.commentary) {
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
                        $scope.status = "Cannot update album. An error "+ response.status +" occured.";
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
            if($scope.genuineAlbum == null || $scope.genuineAlbum.id == undefined) {
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


    }]);
