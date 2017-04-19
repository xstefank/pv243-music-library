'use strict';

angular.module('app')
    .controller('editArtistCtrl', ['$scope', '$http', '$location', 'commonTools', 'createUpdateTools', function ($scope, $http, $location, commonTools, createUpdateTools) {
        $scope.master = {};
        $scope.doing = 'create';
        if (createUpdateTools.getItem() != null) {
            $scope.artist = angular.copy(createUpdateTools.getItem());
            if(createUpdateTools.getItem().dateOfBirth != undefined) {
                $scope.artist.dateOfBirth = new Date(createUpdateTools.getItem().dateOfBirth);
            }
            createUpdateTools.deleteItem();
            $scope.genuineArtist = angular.copy($scope.artist);
            $scope.doing = 'update';
        }

        $scope.update = function(artist) {
            $scope.master = angular.copy(artist);
            var data = {
                name: $scope.master.name,
                dateOfBirth: commonTools.formatDateForRest($scope.master.dateOfBirth)
            };
            if ($scope.doing == 'create') {
                $http({
                    url: '/music/api/artist',
                    method: "POST",
                    data: data
                }).then(function (response) {
                    $scope.status = "New artist successfully created.";
                    createUpdateTools.setAlerts([{type: 'success', title: 'Successful!', msg: $scope.status}]);
                    $location.path("/artistsOverview");
                }, function (response) {
                    $scope.status = "Cannot create, "+ response.status;
                });
            } else {
                $scope.messageBuilder = 'You have successfully updated these fields [';
                $scope.updatingItem = {};
                if (data.name != $scope.genuineArtist.name) {
                    $scope.updatingItem.name = data.name;
                    $scope.messageBuilder += 'name, ';
                }
                if (data.dateOfBirth != commonTools.formatDateForRest($scope.genuineArtist.dateOfBirth)) {
                    $scope.updatingItem.dateOfBirth = data.dateOfBirth;
                    $scope.messageBuilder += 'formed date, ';
                }
                if ($scope.messageBuilder.includes(', ')) {
                    $http({
                        url: '/music/api/artist/' + $scope.genuineArtist.id,
                        method: "PUT",
                        data: $scope.updatingItem
                    }).then(function (response) {
                        $scope.status = $scope.messageBuilder.substring(0, $scope.messageBuilder.length - 2) + "] of artist.";
                        createUpdateTools.setAlerts([{type: 'success', title:'Successful!', msg: $scope.status}]);
                        $location.path("/artistsOverview");
                    }, function (response) {
                        $scope.status = "Cannot update artist. An error "+ response.status +" occured.";
                    });
                } else {
                    createUpdateTools.setAlerts([{type: 'info', title: "No change!", msg: 'There have been no changes.'}]);
                    $location.path("/artistsOverview");
                }
            }
        };

        $scope.reset = function(form) {
            if (form) {
                form.$setPristine();
                form.$setUntouched();
            }
            $scope.artist = null;
        };

        $scope.removeArtist = function () {
            if($scope.genuineArtist == null || $scope.genuineArtist.id == undefined) {
                confirm('Cannot remove Artist immediately.\nFor removing artist we need to know unique identification, which is not available now.');
                $location.path("/artistsOverview");
            } else {
                if(confirm('You are above to completely remove Artist:\n'+ $scope.genuineArtist.name +'\n\nAre you sure?')) {
                    $http({
                        url: '/music/api/artist/' + $scope.genuineArtist.id,
                        method: "DELETE"
                    }).then(function () {
                        createUpdateTools.setAlerts([{type: 'success', title:'Removed!', msg: 'You have successfully deleted '+ $scope.genuineArtist.name +' from system.'}]);
                        $location.path("/artistsOverview");
                    }, function () {
                        createUpdateTools.setAlerts([{type: 'danger', title:'Cannot Remove!', msg: $scope.genuineArtist.name +' still has some albums assigned.'}]);
                        $location.path("/artistsOverview");
                    })
                }
            }
        };


    }]);
