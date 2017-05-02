'use strict';

angular.module('app').component('albumsList', {
    templateUrl: 'components/albumsList.html',
    bindings: {
        albums: '<'
    },
    controller: function ($scope, $location) {
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
    }
});
