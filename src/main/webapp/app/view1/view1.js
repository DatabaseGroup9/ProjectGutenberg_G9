'use strict';

var app = angular.module('myApp.view1', ['ngRoute'])

        app.config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view1', {
                    templateUrl: 'app/view1/view1.html',
                    controller: 'View1Ctrl',
                    controllerAs: 'ctrl'
                });
            }])

        app.controller('View1Ctrl', function ($scope, $http) {
            $scope.searchByCity = function () {
                $http({
                    method: 'GET',
                    url: 'api/book',
                    params: {city: $scope.toSearch, db: $scope.db}
                }).then(function successCallback(response) {
                    $scope.books = response.data;
                    $scope.err = null;
                }, function errorCallback(response) {
                    console.log("ERROR FOUND s::> " + response.data);
                    $scope.err = response.error;
                    $scope.books = null;
                });
            };
        });