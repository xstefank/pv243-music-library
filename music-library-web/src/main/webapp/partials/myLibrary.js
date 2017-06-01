'use strict';

angular.module('app')
    .controller('myLibraryCtrl', ['$scope', '$location', 'commonTools', 'createUpdateTools', function ($scope, $location, commonTools, createUpdateTools) {
        commonTools.getSongsForUser().then(function (response) {
            $scope.songs = response;
        }, function (response) {
            $scope.alerts.push({type: 'danger', title: 'Error '+ response.status, msg: response.statusText});
        });

        $scope.alerts = angular.copy(createUpdateTools.getAlerts());
        createUpdateTools.deleteAlerts();

        $scope.closeAlert = function(index) {
            $scope.alerts.splice(index, 1);
        };
    }]);
