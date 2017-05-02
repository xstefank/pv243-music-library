'use strict';

angular.module('app')
    .controller('artistDetailCtrl', ['$scope', '$http', '$location', '$routeParams', 'commonTools', 'createUpdateTools', function ($scope, $http, $location, $routeParams, commonTools, createUpdateTools) {
        if ($routeParams.id) {
            commonTools.getArtist($routeParams.id).then(function (response) {
                $scope.artist = response;
            }).then(function () {
                commonTools.getAlbumsByArtist($scope.artist.id).then(function (response) {
                    $scope.albums = response;
                })
            });
        }

        $scope.editArtist = function() {
            $location.path('/editArtist/' + $scope.artist.id);
        };

    }]);
