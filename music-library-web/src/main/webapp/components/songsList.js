'use strict';

angular.module('app').component('songsList', {
    templateUrl: 'components/songsList.html',
    controllerAs: 'songList',
    bindings: {
        songs: '<',
        addSong: '<',
        myLibrary: '<',
        remove:'&',
        userRole:'<',
        add: '&'
    },
    controller: function ($scope, $location, $sce) {

        $scope.navigateToEdit = function (id) {
            $location.path('/editSong/' + id)
        };

        $scope.createSong = function () {
            $scope.navigateToEdit(null);
        };

        $scope.updateSong = function (id) {
            $scope.navigateToEdit(id);
        };

        $scope.videoClick = function(song) {
            song.video = true;
            var video_id = song.youtubeLink.split('v=')[1].split('&')[0];
            song.videoUrl = $sce.trustAsResourceUrl('//www.youtube.com/embed/' + video_id);
        }
    }
});
