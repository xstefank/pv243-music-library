'use strict';

angular.module('app')
    .controller('logoutCtrl', ['$scope', '$location', '$rootScope', 'commonTools', function ($scope, $location, $rootScope, commonTools ) {
        $scope.alerts = [];
        $scope.logout = function(){
            window.location = "partials/logout.html";
            if ($rootScope.websocketSession) {
                $rootScope.websocketSession.close();
            }
            commonTools.logout().then(function () {
            }, function (response) {
                $scope.alerts.push({type: 'danger', title: 'Error '+ response.status, msg: response.statusText});
            });
        };

        $scope.getPrincipal = function(){
            commonTools.getPrincipal().then(function (response) {
                $rootScope.userRole = response;
            }, function (response) {
                $scope.alerts.push({type: 'danger', title: 'Error '+ response.status, msg: response.statusText});
            });
        };

        $scope.getPrincipal();
    }]);
