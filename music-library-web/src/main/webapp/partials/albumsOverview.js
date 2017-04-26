'use strict';

angular.module('app')
    .controller('albumsOverviewCtrl', ['$scope', '$http', '$location', 'commonTools', 'createUpdateTools', function ($scope, $http, $location, commonTools, createUpdateTools) {
        commonTools.getAlbums().then(function (response) {
            $scope.albums = response;
        }, function (response) {
            $scope.alerts.push({type: 'danger', title: 'Error '+ response.status, msg: response.statusText});
        });

        $scope.alerts = angular.copy(createUpdateTools.getAlerts());
        createUpdateTools.deleteAlerts();

        $scope.closeAlert = function(index) {
            $scope.alerts.splice(index, 1);
        };


        $scope.navigateToEdit = function (id) {
            $location.path('/editAlbum/' + id)
        };

        $scope.createAlbum = function () {
            $scope.navigateToEdit(null);
        };

        $scope.updateAlbum = function (id) {
            $scope.navigateToEdit(id);
        };

        $scope.albumDetail = function (id) {
            $location.path('/albumDetail/' + id);
        };
    }]);
