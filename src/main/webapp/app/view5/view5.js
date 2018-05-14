'use strict';

var app = angular.module('myApp.view5', ['ngRoute'])

        app.config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view5', {
                    templateUrl: 'app/view5/view5.html'
                });
            }])
