'use strict';

angular.module('bigeaterdashboardApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
