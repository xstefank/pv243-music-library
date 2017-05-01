'use strict';

angular.module('app')
    .controller('songsOverviewCtrl', ['$scope', '$location', 'commonTools', 'createUpdateTools', function ($scope, $location, commonTools, createUpdateTools) {
        commonTools.getSongs().then(function (response) {
            $scope.songs = response;
        }, function (response) {
            $scope.alerts.push({type: 'danger', title: 'Error '+ response.status, msg: response.statusText});
        });

        $scope.alerts = angular.copy(createUpdateTools.getAlerts());
        createUpdateTools.deleteAlerts();

        $scope.closeAlert = function(index) {
            $scope.alerts.splice(index, 1);
        };


        $scope.navigateToEdit = function (id) {
            $location.path('/editSong/' + id)
        };

        $scope.createSong = function () {
            $scope.navigateToEdit(null);
        };

        $scope.updateSong = function (id) {
            $scope.navigateToEdit(id);
        };

    }]);
