 'use strict';

angular.module('bigeaterdashboardApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-bigeaterdashboardApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-bigeaterdashboardApp-params')});
                }
                return response;
            },
        };
    });