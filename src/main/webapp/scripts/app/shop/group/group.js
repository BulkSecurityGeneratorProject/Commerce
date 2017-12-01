angular.module('bigeaterdashboardApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('group', {
                parent: 'shop',
                url: '/group',
                data: {
                    roles: ['ROLE_ADMIN'],
                    pageTitle: 'shop.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/shop/group/group.html',
                        controller: 'GroupController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('group');
                        return $translate.refresh();
                    }]
                },
                onEnter: function(Tracker) {
                    Tracker.subscribe();
                },
                onExit: function(Tracker) {
                    Tracker.unsubscribe();
                },
            });
    });
