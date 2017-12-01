'use strict';

angular.module('bigeaterdashboardApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


