'use strict';

angular.module('app').component('songsList', {
    templateUrl: 'components/songsList.html',
    bindings: {
        songs: '<'
    }
});
