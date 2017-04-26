'use strict';

angular.module('app')
    .controller('albumDetailCtrl', ['$scope', '$http', '$location', '$routeParams', 'commonTools', 'createUpdateTools', function ($scope, $http, $location, $routeParams, commonTools, createUpdateTools) {
        if ($routeParams.id) {
            commonTools.getAlbum($routeParams.id).then(function (response) {
                $scope.album = response;
            }).then(function () {
                commonTools.getSongsByAlbum($scope.album.id).then(function (response) {
                    $scope.songs = response;
                })
            });
        }

        $scope.editAlbum = function() {
            $location.path('/editAlbum/' + $scope.album.id);
        };

    }]);
