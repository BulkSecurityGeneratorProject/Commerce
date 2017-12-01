/**
 * Created by fan on 11/10/15.
 */
'use strict'

angular.module('bigeaterdashboardApp')
    .factory('CreateShopUser', function($resource) {
        return $resource('api/createshopuser', {}, {
        });
    });
