angular.module('bigeaterdashboardApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('shopinfo', {
                parent: 'shop',
                url: '/shopinfo',
                data: {
                    roles: ['ROLE_ADMIN'],
                    pageTitle: 'shopinfo.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/shop/shopinfo/shopinfo.html',
                        controller: 'BigEaterShops'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('shopinfo');
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
