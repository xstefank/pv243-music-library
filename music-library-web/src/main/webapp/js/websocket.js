/**
 * Created by Martin Styk on 18.04.2017.
 */
'use strict';

angular.module('app')
    .controller('websocketCtrl', ['$scope', '$location', '$rootScope', function ($scope, $location, $rootScope) {
        $scope.songs = [];
        $scope.songId = "";

        $scope.recommendSong = function (msg) {
            $rootScope.websocketSession.send(msg);
        };
        
        $scope.onMessage = function (evt) {
            var songData = JSON.parse(evt.data);
            $scope.songs.length = 0;
            angular.forEach(songData, function (item) {
                $scope.songs.push(item);
            });
            $scope.songId = "";
            $scope.$apply();
        };

        this.$onInit = function () {
            if (!$rootScope.websocketSession) {
                $rootScope.websocketSession = new WebSocket('ws://' + document.location.host +'/music/recommendations');
                $rootScope.websocketSession.onmessage = $scope.onMessage;
            }
        };
        
        // this.$onDestroy = function () {
        //     if ($rootScope.websocketSession) { $rootScope.websocketSession.close(); }
        // };

        $scope.getUserString = function (item) {
            var result = item.users[0].firstName;
            if (item.users.length > 1) {
                result += ' +'+ (item.users.length - 1) +' other recommend';
            } else {
                result += ' recommends';
            }
            return result;
        };
        $scope.artistDetail = function (id) {
            $location.path('/artistDetail/' + id);
        }

    }]);
