angular.module('bigeaterdashboardApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('restaurant', {
                parent: 'shop',
                url: '/restaurant',
                data: {
                    roles: ['ROLE_ADMIN'],
                    pageTitle: 'entity.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/shop/restaurant/restaurant.html',
                        controller: 'navController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('restaurant');
                        return $translate.refresh();
                    }]
                },
                onEnter: function(Tracker) {
                    Tracker.subscribe();
                },
                onExit: function(Tracker) {
                    Tracker.unsubscribe();
                }
            });
    });
