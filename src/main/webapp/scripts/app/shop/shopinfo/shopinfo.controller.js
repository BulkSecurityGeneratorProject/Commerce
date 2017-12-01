var Shop = function(){
    var shop = {
        init: function() {
            this.name = "";
            this.startDate = new Date();
            this.endDate = new Date();
            //1 为 category id, 在数据库中为Restaurant下的 Chinese.
            //当取消新建shop时，所有的shop form字段应恢复默认值，shop category的默认值为Restaurant -> Chinese
            this.shopCategory = 1;
        }
    }
    shop.init();
    return shop;
}

angular.module('bigeaterdashboardApp')
    .controller('BigEaterShops', function($scope, $rootScope, $http, $log, shopService, shopUtils){
        $scope.shopsobj = {};
        $scope.shopsobj.shops = [];

        $scope.$watch('$viewContentLoaded', function(){
            shopService.getShops().success(function(response){
                $scope.shopsobj.shops = response;
            }).error(function(){
                alert('error');
            });
        });


        $scope.categories = [];

        $scope.$watch('$viewContentLoaded', function(){
            shopService.getShopCategory().success(function(response){
                $scope.categories = response;
            }).error(function(){
                alert('error');
            });
        });

        $scope.collapse = true;
        $scope.isCrudCollapsed = true;

        $scope.toggleMin = function() {
            $scope.minDate = $scope.minDate ? null : new Date();
        };
        $scope.toggleMin();

        $scope.existingEndDate = {};
        $scope.setExistingEndDate = function($event,idx) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.existingEndDate['idx'+idx] = true;
        };

        $scope.existingStartDate = {};
        $scope.setExistingStartDate = function($event,idx) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.existingStartDate['idx'+idx] = true;
        };

        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1
        };

        $scope.formats = ['dd MMMM yyyy', 'dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
        $scope.format = $scope.formats[0];

        $scope.datepickerStartDate = null;
        $scope.datepickerEndDate = null;



        $scope.createShop = function(shop){
            var msg = [];

            var valid = shopUtils.validateName(shop.name, msg);
            if (valid) valid = shopUtils.validateDates(shop.startDate, shop.endDate, msg);
            if (valid !== false) {
                shopService.addShop(shop).success(function(response){
                    $scope.isCrudCollapsed=true;
                    $scope.resetCreateForm();
                    $scope.shopsobj.shops = response;
                }).error(function(){
                    alert('error');
                });
            } else {
                var obj = {
                    state: 'failure',
                    message: "Create shop " + shop.name+ " failed as reason : " + shopUtils.createErrorString(msg)
                };

                Utils.Toast(obj);
            }
        }


        $scope.crudForm = {};
        $scope.newShop = Shop();
        var oriNewShop = angular.copy($scope.newShop);

        $scope.resetCreateForm = function() {
            $scope.isCrudCollapsed=true;
            $scope.crudForm.createNewShop.$setPristine();
            $scope.newShop = angular.copy(oriNewShop);
        }
    });

angular.module('bigeaterdashboardApp')
    .controller('BigEaterShopsItem', function($scope, $rootScope, $http, $log, shopService, shopUtils){
        $scope.disabled = false;

        $scope.editShop = function(shop){
            var msg = [];

            var valid = shopUtils.validateName(shop.name, msg);
            if (valid) valid = shopUtils.validateDates(shop.startDate, shop.endDate, msg);
            if (valid !== false) {
                shopService.editShop(shop).success(function(response){
                    $scope.resetItemForm(shop);
                    $scope.bigeaterCollapseState = true;
                    $scope.shopsobj.shops = response;
                }).error(function(){
                    alert('error');
                });
            } else {
                var obj = {
                    state: 'failure',
                    message: "Edit shop " + shop.name + " failed as reason : " + shopUtils.createErrorString(msg)
                };

                Utils.Toast(obj);
            }
        }

        $scope.deleteShop = function(shop){
            if (shop.visible == true) {
                var obj = {
                    state: 'error',
                    message: 'Cannot delete an active shop.'
                };
                Utils.Toast(obj);
            } else {
                var obj = {
                    callbacks: {
                        pass: function() {
                            shopService.deleteShop(shop).success(function(response){
                                $scope.shopsobj.shops = response;
                            }).error(function(){
                                alert('error');
                            });
                        },
                        fail: function() {
                            $scope.$apply(function() {
                                $scope.disabled = false;
                            })
                        }
                    },
                    msg : "Deleting a shop will delete all data. Are you sure you wish to continue?"
                }

                $scope.disabled = true;

                Utils.ModalToast(obj);
            }

        }

        $scope.disableShop = function(shop){
            shopService.disableShop(shop).success(function(response){
                $scope.shopsobj.shops = response;
            }).error(function(){
                alert('error');
            });
        }

        $scope.enableShop = function(shop){
            shopService.enableShop(shop).success(function(response){
                $scope.shopsobj.shops = response;
            }).error(function(){
                alert('error');
            });
        }

        $scope.form = {};
        $scope.resetObj = $scope.shop;
        $scope.resetItemForm = function(shop) {
            $scope.formOpen = false;
            if($scope.disabled){
                $scope.disabled = false;
            }
            $scope.form.updateForm.$setPristine();
            $scope.updatedShop = angular.copy(shop);
        }
    });
