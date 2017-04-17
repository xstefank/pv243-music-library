'use strict';

angular.module('app')
    .controller('artistsOverviewCtrl', ['$scope', '$http', '$location', 'commonTools', 'createUpdateTools', function ($scope, $http, $location, commonTools, createUpdateTools) {
        commonTools.getArtistsAvailable().then(function (response) {
            $scope.artists = response;
        }, function (response) {
            $scope.alerts.push({type: 'danger', title: 'Error '+ response.status, msg: response.statusText});
        });

        $scope.alerts = angular.copy(createUpdateTools.getAlerts());
        createUpdateTools.deleteAlerts();

        $scope.closeAlert = function(index) {
            $scope.alerts.splice(index, 1);
        };


        $scope.navigateToEdit = function (artist) {
            createUpdateTools.setItem(artist);
            $location.path('/editArtist')
        };

        $scope.createArtist = function () {
            $scope.navigateToEdit(null);
        };

        $scope.updateArtist = function (artist) {
            $scope.navigateToEdit(artist);
        };
    }]);
