'use strict';

var app = angular.module('myApp.view2', ['ngRoute', 'ui-leaflet'])

app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view2', {
            templateUrl: 'app/view2/view2.html',
            controller: 'View2Ctrl'
        });
    }])

app.controller('View2Ctrl', function ($scope, $http) {
    $scope.searchByBookTitle = function () {
        $http({
            method: 'GET',
            url: 'api/city',
            params: {title: $scope.toSearch, db: $scope.db}
        }).then(function successCallback(response) {
            $scope.cities = response.data;
            $scope.err = null;
            $scope.markers = new Array();
            angular.forEach($scope.cities, function (product) {
                $scope.markers.push({
                    lat: product.lat,
                    lng: product.lon,
                    message: product.name,
                    focus: false,
                    draggable: false
                });
            });
        }, function errorCallback(response) {
            console.log("ERROR FOUND::> " + response.data);
            $scope.err = response.error;
            $scope.cities = null;
        });
    };


});