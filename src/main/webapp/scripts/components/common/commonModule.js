var commonModule = angular.module('commonModule', []);

/* should replace w/ single collapse */
commonModule.directive('bigeaterCollapse', function() {
    return {
        restrict: 'AE',
        link: function(scope, elem, attrs) {
            scope.bigeaterCollapseState = scope.$eval(attrs.bigeaterCollapseState);
            scope.$watch(attrs.bigeaterCollapse, function (state) {
                if (state) {
                    elem.find('.Slide').slideUp();
                } else {
                    elem.find('.Slide').slideDown();
                }
            });
        }
    };
});

// Only use this one w/ local $scope vars
commonModule.directive('bigeaterSingleCollapse', function() {
    return {
        restrict: 'AE',
        link: function(scope, elem, attrs) {
            scope.$watch(attrs.state, function(state) {
                if(state) {
                    elem.find('.Slide').slideUp();
                }else{
                    elem.find('.Slide').slideDown();
                }
            })
        }
    }
});

commonModule.directive('bigeaterHeadCollapse', function() {
    return {
        restrict: 'AE',
        link: function(scope, elem, attrs) {
            scope.headCollapseState = scope.$eval(attrs.headCollapseState);
            scope.brandHeadCollapseState = scope.$eval(attrs.headCollapseState);
            scope.$watch(attrs.bigeaterHeadCollapse, function (state) {
                if(state) {
                    elem.next().slideUp();
                }else{
                    elem.next().slideDown();
                }
            })
        }
    }
})

commonModule.controller('BigEaterAccordionController', function($scope) {
    this.groups = [];

    this.closeOthers = function(openGroup) {
        angular.forEach(this.groups, function (group) {
            if ( group !== openGroup ) {
                if(group.formOpen) {
                    group.formOpen = false;
                    group.resetItemForm(group.resetObj);
                    toastr.clear();
                }
            }
        });
    };

    this.addGroup = function(groupScope) {
        var that = this;
        this.groups.push(groupScope);

        groupScope.$on('$destroy', function (event) {
            that.removeGroup(groupScope);
        });
    };

    this.removeGroup = function(group) {
        var index = this.groups.indexOf(group);
        if ( index !== -1 ) {
            this.groups.splice(index, 1);
        }
    };
});
commonModule.directive('bigeaterAccordion', function(){
    return {
        restrict: 'A',
        controller: 'BigEaterAccordionController'
    };
});
commonModule.directive('bigeaterAccordionItem', function(){
    return {
        require:'^bigeaterAccordion',
        restrict:'A',
        link: function(scope, element, attrs, accordionCtrl) {
            accordionCtrl.addGroup(scope);

            scope.formOpen = false;
            scope.formLoaded = true;

            scope.$watch('formOpen', function(value) {
                if (value) {
                    accordionCtrl.closeOthers(scope);
                }
            });

            scope.loadItemForm = function(){
                if(!scope.formLoaded) {
                    scope.formOpen = false;
                }else{
                    scope.formOpen = true;
                }
            }

            scope.formDisplay = function() {
                var css = "";
                if(scope.formLoaded) {
                    css = (scope.formOpen) ? 'formOpen' : 'formClosed';
                }
                return css;
            }

        }
    };
});

commonModule.directive('bigeaterFocus', function($timeout){
    return {
        scope: {trigger: '=bigeaterFocus' },
        link : function(scope, element) {
            scope.$watch('trigger', function(value){
                if(value === false){
                    element[0].focus();
                    scope.trigger = false;
                }
            });
        }
    };
});

commonModule.directive('bigeaterSticky', function($window){
    return {
        restrict: 'A',
        link: function(scope, elem, attr) {
            var stickyNavTop = elem.offset().top;
            var stickyNav = function() {
                var scrollTop = $(window).scrollTop();

                if (scrollTop > stickyNavTop) {
                    elem.addClass('fixed');
                } else {
                    elem.removeClass('fixed');
                }
            };

            stickyNav();

            $(window).scroll(function() {
                stickyNav();
            });
        }
    }
});

commonModule.directive('bigeaterBurger', function($rootScope){
    return {
        restrict: "A",
        scope: 'false',
        link: function(scope, elem, attrs) {
            elem.click(function() {
                $rootScope.$apply(function() {
                    $rootScope.navState = ($rootScope.navState == "open") ? "closed" : "open";
                })
            })
        }
    }
});

var textareaExpand = function(param){
    var element = param;
    $(element).height(0);
    $(element).css('padding', '4px');
    $(element).css('resize', 'none');
    $(element).css('overflow', 'hidden');
    var height = $(element)[0].scrollHeight;
    // 8 = 4 + 4, is for the padding
    // offset 20 to make up the scrollbar size
    // 60 = 3 * 20, 3 is the number of maximum lines
    if(height < 20){
        height = 28;
    }else if(height > 60){
        height = 68;
        $(element).css('overflow', 'auto');
    }
    $(element).height(height - 8);
}
commonModule.directive('bigeaterAutoExpand',  function(){
    return {
        restrict: 'A',
        link: function($scope, elem, attr){
            elem.bind('keyup', function($event){
                textareaExpand($event.target);
            });

            //Expand the textarea as soon as it is added to the DOM
            setTimeout(function(){
                textareaExpand(elem);
            }, 0);
        }
    };
});
