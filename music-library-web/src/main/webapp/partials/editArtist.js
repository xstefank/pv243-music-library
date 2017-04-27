'use strict';

angular.module('app')
    .controller('editArtistCtrl', ['$scope', '$http', '$location', '$routeParams', 'commonTools', 'createUpdateTools', function ($scope, $http, $location, $routeParams, commonTools, createUpdateTools) {

        $scope.alerts = [];
        $scope.artist = {};
        $scope.changed = false;
        $scope.doing = 'Create';
        $scope.dateRegex = '(((19|20)([2468][048]|[13579][26]|0[48])|2000)[\-]02[\-]29|((19|20)[0-9]{2}[\-](0[4678]|1[02])[\-](0[1-9]|[12][0-9]|30)|(19|20)[0-9]{2}[\-](0[1359]|11)[\-](0[1-9]|[12][0-9]|3[01])|(19|20)[0-9]{2}[\-]02[\-](0[1-9]|1[0-9]|2[0-8])))';
        if ($routeParams.id) {
            commonTools.getArtist($routeParams.id).then(function (response) {
                $scope.artist = response;
                $scope.oldArtist = angular.copy($scope.artist);
            }, function (response) {
                $scope.alerts.push({type: 'danger', title: 'Error ' + response.status, msg: response.statusText});
            });

            $scope.doing = 'Update';
        }

        $scope.update = function () {
            if ($scope.doing === 'Create') {
                $http({
                    url: '/music/api/artist/',
                    method: "POST",
                    data: $scope.artist
                }).then(function (response) {
                    $scope.status = "New artist successfully created.";
                    createUpdateTools.setAlerts([{type: 'success', title: 'Successful!', msg: $scope.status}]);
                    $location.path("/artistsOverview");
                }, function (response) {
                    $scope.alerts.push({type: 'danger', title: 'Error ' + response.status, msg: response.statusText});
                });
            } else {
                $scope.messageBuilder = 'You have successfully updated these fields [';
                if ($scope.artist.name !== $scope.oldArtist.name) {
                    $scope.messageBuilder += 'name, ';
                    $scope.changed = true;
                }
                if ($scope.artist.dateOfBirth !== $scope.oldArtist.dateOfBirth) {
                    $scope.messageBuilder += 'formed date, ';
                    $scope.changed = true;
                }
                if ($scope.changed) {
                    $http({
                        url: '/music/api/artist/' + $scope.artist.id,
                        method: "PUT",
                        data: $scope.artist
                    }).then(function (response) {
                        $scope.status = $scope.messageBuilder.substring(0, $scope.messageBuilder.length - 2) + "] of artist.";
                        createUpdateTools.setAlerts([{type: 'success', title: 'Successful!', msg: $scope.status}]);
                        $location.path("/artistsOverview");
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
                    $location.path("/artistsOverview");
                }
            }
        };

        $scope.reset = function (form) {
            if (form) {
                form.$setPristine();
                form.$setUntouched();
            }
            $scope.artist = null;
        };

        $scope.removeArtist = function () {
            if (!$scope.artist) {
                confirm('Cannot remove Artist immediately.\nArtist probably have not been saved yet.');
                $location.path("/artistsOverview");
            } else {
                if (confirm('You are above to completely remove Artist:\n' + $scope.artist.name + '\n\nAre you sure?')) {
                    $http({
                        url: '/music/api/artist/' + $scope.artist.id,
                        method: "DELETE"
                    }).then(function () {
                        createUpdateTools.setAlerts([{
                            type: 'success',
                            title: 'Removed!',
                            msg: 'You have successfully deleted ' + $scope.artist.name + ' from system.'
                        }]);
                        $location.path("/artistsOverview");
                    }, function () {
                        createUpdateTools.setAlerts([{
                            type: 'danger',
                            title: 'Cannot Remove!',
                            msg: $scope.artist.name + ' still has some albums assigned.'
                        }]);
                        $location.path("/artistsOverview");
                    })
                }
            }
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };

    }]);
