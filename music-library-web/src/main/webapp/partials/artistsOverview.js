'use strict';

angular.module('app')
    .controller('artistsOverviewCtrl', ['$scope', '$location', 'commonTools', 'createUpdateTools', function ($scope, $location, commonTools, createUpdateTools) {
        commonTools.getArtists().then(function (response) {
            $scope.artists = response;
        }, function (response) {
            $scope.alerts.push({type: 'danger', title: 'Error '+ response.status, msg: response.statusText});
        });

        $scope.alerts = angular.copy(createUpdateTools.getAlerts());
        createUpdateTools.deleteAlerts();

        $scope.closeAlert = function(index) {
            $scope.alerts.splice(index, 1);
        };


        $scope.navigateToEdit = function (id) {
            $location.path('/editArtist/' + id)
        };

        $scope.createArtist = function () {
            $scope.navigateToEdit(null);
        };

        $scope.updateArtist = function (id) {
            $scope.navigateToEdit(id);
        };

        $scope.artistDetail = function (id) {
            $location.path('/artistDetail/' + id);
        };
        
        $scope.runBatch = function () {
            commonTools.runBatch().then(function () {
                $scope.alerts.push({type: 'success', title: 'Running!' , msg: 'Batch started.'});
            }, function (response) {
                $scope.alerts.push({type: 'danger', title: 'Error starting batch '+ response.status, msg: response.statusText});
            })
        };
    }]);
