'use strict';

var app = angular.module('myApp.view3', ['ngRoute', 'ui-leaflet'])

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/view3', {
        templateUrl: 'app/view3/view3.html',
        controller: 'View3Ctrl'
    });
}])

app.service('booksGlobal', function () {
    var books = new Array();

    return {
        getBooks: function () {
            return books;
        },
        setBooks: function (value) {
            books = value;
        }
    };
});

app.controller('View3Ctrl', function ($scope, $http, booksGlobal) {
    $scope.searchCitiesByBooksOfAuthor = function () {
        $http({
            method: 'GET',
            url: 'api/author',
            params: {author: $scope.toSearch, db: $scope.db}
        }).then(function successCallback(response) {
            $scope.books = response.data;
            $scope.markers = new Array();
            angular.forEach($scope.books, function (book) {
                $scope.cities = book.cities;
                angular.forEach($scope.cities, function (city) {
                    if($scope.markers.match({}))
                    $scope.markers.push({
                        lat: city.lat,
                        lng: city.lon,
                        message: book.title + " - " + city.name,
                        focus: false,
                        draggable: false
                    });
                });
            });
            $scope.err = null;
        }, function errorCallback(response) {
            console.log("ERROR FOUND::> " + response.data);
            $scope.err = response.error;
            $scope.cities = null;
        });
    };


});

