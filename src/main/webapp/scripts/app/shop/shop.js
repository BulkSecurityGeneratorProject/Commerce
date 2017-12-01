'use strict';

angular.module('bigeaterdashboardApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('shop', {
                abstract: true,
                parent: 'site'
            });
    });
