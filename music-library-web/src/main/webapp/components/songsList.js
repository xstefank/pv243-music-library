'use strict';

angular.module('app').component('songsList', {
    templateUrl: 'components/songsList.html',
    bindings: {
        songs: '<'
    },
    controller: function ($scope, $location) {
        $scope.navigateToEdit = function (id) {
            $location.path('/editSong/' + id)
        };

        $scope.createSong = function () {
            $scope.navigateToEdit(null);
        };

        $scope.updateSong = function (id) {
            $scope.navigateToEdit(id);
        };

        $scope.songDetail = function (id) {
            //TODO $location.path('/songDetail/' + id);
        };
    }
});
