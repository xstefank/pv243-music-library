'use strict';

angular.module('app')
    .controller('myLibraryCtrl', ['$scope', '$location', '$route', 'commonTools', 'createUpdateTools', function ($scope, $location, $route, commonTools, createUpdateTools) {
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

        $scope.removeSong = function (id) {
            commonTools.removeSongFromUser(id).then(function () {
                $scope.status = "Song was removed from your library.";
                createUpdateTools.setAlerts([{type: 'success', title: 'Successful!', msg: $scope.status}]);
                $route.reload();
            }, function (response) {
                $scope.alerts.push({type: 'danger', title: 'Error ' + response.status, msg: response.statusText});
            });
        };
    }]);
