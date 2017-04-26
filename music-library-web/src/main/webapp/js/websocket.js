/**
 * Created by Martin Styk on 18.04.2017.
 */
'use strict';

angular.module('app')
    .controller('websocketCtrl', ['$scope', '$http', '$location', 'commonTools', 'createUpdateTools', function ($scope, $http, $location, commonTools, createUpdateTools) {
        $scope.songs = [];
        $scope.songId = "";

        $scope.recommendSong = function (msg) {
            $scope.websocketSession.send(msg);
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
            if (!$scope.websocketSession) {
                $scope.websocketSession = new WebSocket('ws://127.0.0.1:8080/music/recommendations');
                $scope.websocketSession.onmessage = $scope.onMessage;
            }
        };
        
        this.$onDestroy = function () {
            if ($scope.websocketSession) {
                $scope.websocketSession.close();
            }
        };

        $scope.getUserString = function (item) {
            var result = item.users[0].firstName;
            if (item.users.length > 1) {
                result += '+'+ (item.users.length - 1) +' other recommend';
            } else {
                result += ' recommends';
            }
            return result;
        };

    }]);
