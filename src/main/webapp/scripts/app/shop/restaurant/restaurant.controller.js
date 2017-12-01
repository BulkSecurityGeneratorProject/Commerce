angular.module('bigeaterdashboardApp')
    .controller('navController', function($scope, $rootScope, $http, $log){
        $scope.nav = {};
        $scope.nav.url = 'scripts/app/shop/restaurant/product.html?i=' + Math.floor(Math.random() * 100) + 1;


        $scope.changeNavItem = function(navItemUrl){
            $scope.nav.url = navItemUrl+'?i=' + Math.floor(Math.random() * 100) + 1;
        }
    });

angular.module('bigeaterdashboardApp')
    .controller('HamburgerBar', function($scope, $rootScope, $log, $location){
        $scope.$watch('$viewContentLoaded', function(){

        });
    });

angular.module('bigeaterdashboardApp')
    .controller('bigeaterProductLanding', function($scope, $rootScope, $log, $location, resService, Upload, $modal, baseUrl){
        /** product category start **/
        $scope.isTypeCollapsed = true;

        $scope.data = [];

        $scope.$watch('$viewContentLoaded', function(){
            resService.getProductCategories().success(function(response){
                $scope.data = response;
            }).error(function(){
                alert('error');
            });
        });

        $scope.createRootType = function(type){
            resService.addCategory(type).success(function(response){
                $scope.resetCreateForm();
                $scope.data = response;
            }).error(function(){
                alert('error');
            });
        }

        var getRootNodesScope = function() {
            return angular.element(document.getElementById("tree-root")).scope();
        };
        $scope.collapseAll = function() {
            var scope = getRootNodesScope();
            scope.collapseAll();
        };
        $scope.expandAll = function() {
            var scope = getRootNodesScope();
            scope.expandAll();
        };
        $scope.moveLastToTheBegginig = function () {
            var a = $scope.data.pop();
            $scope.data.splice(0,0, a);
        };
        $scope.toggle = function(scope) {
            scope.toggle();
        };


        $scope.edit = function(node) {
            node.editing = true;
        }
        $scope.add = function(node) {
            node.adding = true;
        };
        $scope.deleteType = function(node) {
            resService.deleteCategory(node).success(function(response){
                $scope.data = response;
            }).error(function(){
                alert('error');
            });
        };

        $scope.updateType = function(node) {
            resService.editCategory(node).success(function(response){
                $scope.data = response;
                node.editing = false;
            }).error(function(){
                alert('error');
            });
        }
        $scope.cancelUpdatingType = function(node) {
            node.editing = false;
        }
        $scope.addType = function(newNode, node) {
            resService.addChildCategory(newNode, node).success(function(response){
                $scope.data = response;
            }).error(function(){
                alert('error');
            });
            node.adding = false;
        }
        $scope.cancelAddingType = function(node) {
            node.adding = false;
        }


        $scope.newType = {
            name: ""
        }
        var oriNewType = angular.copy($scope.newType);
        $scope.resetCreateForm = function() {
            $scope.newType = angular.copy(oriNewType);
            $scope.createNewType.$setPristine();
            $scope.isTypeCollapsed = true;
        }
        /** product category end **/


        /** product brand start **/
        $scope.isBrandCollapsed = true;

        $scope.brandsobj = {};
        $scope.brandsobj.brands = [];
        $scope.brandsobj.totalItems = 0;
        $scope.brandsobj.itemsPerPage = 0;
        $scope.brandsobj.numPages = 0;

        $scope.brandCurrentPage = 1;
        $scope.maxSize = 5;

        $scope.allBrands = [];

        $scope.refreshAllBrands = function() {
            resService.getProductBrands().success(function(response){
                $scope.allBrands = response;
            }).error(function(){
                alert('error');
            });
        }

        $scope.$watch('$viewContentLoaded', function(){
            resService.getPaginationBrands($scope.brandCurrentPage).success(function(response){
                $scope.brandsobj = response;
                $scope.brandsobj.brands = response.content;
                $scope.brandsobj.totalItems = response.totalElements;
                $scope.brandsobj.itemsPerPage = response.size;
                $scope.brandsobj.numPages = response.totalPages;
            }).error(function(){
                alert('error');
            });

            $scope.refreshAllBrands();
        });

        $scope.brandPageChanged = function() {
            resService.getPaginationBrands($scope.brandCurrentPage).success(function(response){
                $scope.brandsobj.brands = response.content;
            }).error(function(){
                alert('error');
            });
        };

        $scope.createBrand = function(brand){
            Upload.upload({
                url: baseUrl + '/product/addBrand',
                fields: {
                    'shopinfoid': 1,
                    'name': brand.name
                },
                file: brand.logopath
            }).progress(function (evt) {
                //var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                //console.log('progress: ' + progressPercentage + '% ' + evt.config.file.name);
            }).success(function (data, status, headers, config) {
                $scope.resetCreateBrandForm();
                $scope.brandsobj = data;
                $scope.brandsobj.brands = data.content;
                $scope.brandsobj.totalItems = data.totalElements;
                $scope.brandsobj.itemsPerPage = data.size;
                $scope.brandsobj.numPages = data.totalPages;
            }).error(function (data, status, headers, config) {
                alert('error');
            })
        }

        $scope.editBrand = function(brand){
            Upload.upload({
                url: baseUrl + '/product/editBrand',
                fields: {
                    'shopinfoid': 1,
                    'id': brand.id,
                    'name': brand.name
                },
                file: brand.logopath
            }).progress(function (evt) {

            }).success(function (data, status, headers, config) {
                $scope.resetCreateBrandForm();
                $scope.brandsobj = data;
                $scope.brandsobj.brands = data.content;
                $scope.brandsobj.totalItems = data.totalElements;
                $scope.brandsobj.itemsPerPage = data.size;
                $scope.brandsobj.numPages = data.totalPages;
            }).error(function (data, status, headers, config) {
                alert('error');
            })
        }

        $scope.newBrand = {
            name: "",
            logopath: null
        }
        var oriNewBrand = angular.copy($scope.newBrand);
        $scope.resetCreateBrandForm = function(){
            $scope.newBrand = angular.copy(oriNewBrand);
            $scope.createNewBrand.$setPristine();
            $scope.isBrandCollapsed = true;
        }
        /** product brand end **/


        /** product info start **/
        $scope.isProductCollapsed = true;

        $scope.productsobj = {};
        $scope.productsobj.products = [];
        $scope.productsobj.totalItems = 0;
        $scope.productsobj.itemsPerPage = 0;
        $scope.productsobj.numPages = 0;

        $scope.productCurrentPage = 1;
        $scope.maxSize = 5;

        $scope.data = [];

        $scope.$watch('$viewContentLoaded', function(){
            resService.getProductCategories().success(function(response){
                $scope.data = response;
            }).error(function(){
                alert('error');
            });
        });
        $scope.$watch('$viewContentLoaded', function(){
            resService.getPaginationProducts($scope.productCurrentPage).success(function(response){
                $scope.productsobj = response;
                $scope.productsobj.products = response.content;
                $scope.productsobj.totalItems = response.totalElements;
                $scope.productsobj.itemsPerPage = response.size;
                $scope.productsobj.numPages = response.totalPages;
            }).error(function(){
                alert('error');
            });
        });

        $scope.productPageChanged = function(){
            resService.getPaginationProducts($scope.productCurrentPage).success(function(response){
                $scope.productsobj.products = response.content;
            }).error(function(){
                alert('error');
            });
        }

        //pop up for category selection start
        $scope.animationsEnabled = true;

        $scope.openCategoryModal = function (size, product) {
            var modalInstance = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'productCategoryContent.html',
                controller: productCategoryCtrl,
                size: size,
                resolve: {
                    data: function () {
                        return $scope.data;
                    },
                    newProduct: function () {
                        return $scope.newProduct;
                    },
                    updatedProduct : function () {
                        if(product != null) {
                            return product;
                        } else {
                            return "";
                        }
                    }
                }
            });

            modalInstance.result.then(function (selectedItem) {
                $scope.selected = selectedItem;
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

        $scope.toggleAnimation = function () {
            $scope.animationsEnabled = !$scope.animationsEnabled;
        };

        var productCategoryCtrl = function ($scope, $modalInstance, data, newProduct, updatedProduct) {
            $scope.data = data;
            $scope.newProduct = newProduct;
            $scope.updatedProduct = updatedProduct;

            $scope.selectStyle = function(node) {
                $scope.newProduct.producttype = node.id;
                $scope.newProduct.producttypevalue = node.name;

                $scope.updatedProduct.producttype = node;
                $scope.updatedProduct.producttypevalue = node.name;

                $modalInstance.dismiss('cancel');
            }

            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
        }
        //pop up for category selection end

        $scope.createProduct = function(product){
            resService.addProduct(product).success(function(response){

                $scope.productsobj.products = response.content;
                $scope.productsobj.totalItems = response.totalElements;
                $scope.productsobj.itemsPerPage = response.size;
                $scope.productsobj.numPages = response.totalPages;

                $scope.productCurrentPage = 1;

                $scope.resetCreateProductForm();
            }).error(function(){
                alert('error');
            });
        }

        $scope.newProduct = {
            code : "",
            note : "",
            brand : "",
            model : "",
            baseprice : "",
            marketprice : "",
            sellprice : "",
            weight : "",
            producttype : "",
            sexrequest : "",
            styles : "",
            description : "",
            buyexplain : ""
        }
        var oriNewProduct = angular.copy($scope.newProduct);
        $scope.resetCreateProductForm = function(){
            $scope.newProduct = angular.copy(oriNewProduct);
            $scope.createNewProduct.$setPristine();
            $scope.isProductCollapsed = true;
        }

        /** product info end **/
    });

angular.module('bigeaterdashboardApp')
    .controller('bigeaterBrandItem', function($scope, $rootScope, $log, $location, resService){
        $scope.deleteBrand = function(brand) {
            resService.deleteProductBrand(brand).success(function(response){
                $scope.brandsobj.brands = response.content;
                $scope.brandsobj.totalItems = data.totalElements;
                $scope.brandsobj.itemsPerPage = data.size;
                $scope.brandsobj.numPages = response.totalPages;
            }).error(function(){
                alert('error');
            });
        }

        $scope.form = {};
        $scope.resetObj = $scope.brand;
        $scope.resetItemForm = function(brand) {
            $scope.formOpen = false;
            $scope.form.updateForm.$setPristine();
            $scope.updatedBrand = angular.copy(brand);
        }
    });

angular.module('bigeaterdashboardApp')
    .controller('bigeaterProductItem', function($scope, $rootScope, $log, $location, resService, $modal, baseUrl){
        $scope.updateProduct = function(product){
            resService.editProduct(product).success(function(response){
                $scope.productsobj.products = response.content;
                $scope.productsobj.totalItems = response.totalElements;
                $scope.productsobj.itemsPerPage = response.size;
                $scope.productsobj.numPages = response.totalPages;
            }).error(function(){
                alert('error');
            });
        }

        $scope.deleteProduct = function(product){
            resService.deleteProduct(product).success(function(response){
                $scope.productsobj.products = response.content;
                $scope.productsobj.totalItems = response.totalElements;
                $scope.productsobj.itemsPerPage = response.size;
                $scope.productsobj.numPages = response.totalPages;

            }).error(function(){
                alert('error');
            });
        }

        $scope.discommendProduct = function(product){
            resService.discommendProduct(product).success(function(response){
                $scope.productsobj.products = response.content;
                $scope.productsobj.totalItems = response.totalElements;
                $scope.productsobj.itemsPerPage = response.size;
                $scope.productsobj.numPages = response.totalPages;
            }).error(function(){
                alert('error');
            });
        }

        $scope.commendProduct = function(product){
            resService.commendProduct(product).success(function(response){
                $scope.productsobj.products = response.content;
                $scope.productsobj.totalItems = response.totalElements;
                $scope.productsobj.itemsPerPage = response.size;
                $scope.productsobj.numPages = response.totalPages;
            }).error(function(){
                alert('error');
            });
        }

        $scope.disableProduct = function(product){
            resService.disableProduct(product).success(function(response){
                $scope.productsobj.products = response.content;
                $scope.productsobj.totalItems = response.totalElements;
                $scope.productsobj.itemsPerPage = response.size;
                $scope.productsobj.numPages = response.totalPages;
            }).error(function(){
                alert('error');
            });
        }

        $scope.enableProduct = function(product){
            resService.enableProduct(product).success(function(response){
                $scope.productsobj.products = response.content;
                $scope.productsobj.totalItems = response.totalElements;
                $scope.productsobj.itemsPerPage = response.size;
                $scope.productsobj.numPages = response.totalPages;
            }).error(function(){
                alert('error');
            });
        }

        //pop up for style selection start
        $scope.animationsEnabled = true;

        $scope.open = function (size, product) {

            var modalInstance = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'productStyleContent.html',
                controller: productStyleCtrl,
                size: size,
                resolve: {
                    resService : function () {
                        return resService;
                    },
                    product: function () {
                        return product;
                    }
                }
            });

            modalInstance.result.then(function (selectedItem) {
                $scope.selected = selectedItem;
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

        $scope.toggleAnimation = function () {
            $scope.animationsEnabled = !$scope.animationsEnabled;
        };

        var productStyleCtrl = function ($scope, $modalInstance, product, Upload, resService) {

            $scope.isStyleCollapsed = true;

            $scope.stylesobj = {};
            $scope.stylesobj.styles = [];


            $scope.$watch('$viewContentLoaded', function(){
                resService.getStyles(product).success(function(response){
                    $scope.stylesobj.styles = response;
                }).error(function(){
                    alert('error');
                });
            });

            $scope.product = product;

            $scope.createStyle = function(style){
                Upload.upload({
                    url: baseUrl + '/product/addStyle',
                    fields: {
                        'name': style.name,
                        'product': product.id,
                    },
                    file: style.imagename
                }).progress(function (evt) {

                }).success(function (data, status, headers, config) {
                    $scope.resetCreateStyleForm();
                    $scope.stylesobj.styles = data;
                }).error(function (data, status, headers, config) {
                    alert('error');
                })
            }

            $scope.newStyle = {
                name: "",
                imagename: ""
            }
            var oriNewStyle = angular.copy($scope.newStyle);
            $scope.resetCreateStyleForm = function(){
                $scope.newStyle = angular.copy(oriNewStyle);
                $scope.createNewStyle.$setPristine();
                $scope.isStyleCollapsed = true;
            }

//		$scope.ok = function () {
//			$modalInstance.close($scope.selected.item);
//		};

            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
        }
        //pop up for style selection end

        $scope.form = {};
        $scope.resetObj = $scope.product;
        $scope.resetItemForm = function(product) {
            $scope.formOpen = false;
            $scope.form.updateForm.$setPristine();
            $scope.updatedProduct = angular.copy(product);
            $scope.updatedProduct.producttypevalue  = product.producttype.name;
        }
    });
angular.module('bigeaterdashboardApp')
    //.controller('bigeaterStyleItem', function($scope, $rootScope, $log, $location, $routeParams, resService, Upload){ 为什么不能用 $routeParams
    .controller('bigeaterStyleItem', function($scope, $rootScope, $log, $location, resService, Upload, baseUrl){
        $scope.updateStyle = function(style, product){
            Upload.upload({
                url: baseUrl + '/product/editStyle',
                fields: {
                    'id': style.id,
                    'name': style.name,
                    'product': product.id
                },
                file: style.imagename
            }).progress(function (evt) {

            }).success(function (data, status, headers, config) {
                $scope.resetItemForm(style);
                $scope.stylesobj.styles = data;
            }).error(function (data, status, headers, config) {
                alert('error');
            })
        }

        $scope.deleteStyle = function(style){
            resService.deleteStyle(style).success(function(response){
                $scope.stylesobj.styles = response;
            }).error(function(){
                alert('error');
            });
        }

        $scope.enableStyle = function(style){
            resService.enableStyle(style).success(function(response){
                $scope.stylesobj.styles = response;
            }).error(function(){
                alert('error');
            });
        }

        $scope.disableStyle = function(style){
            resService.disableStyle(style).success(function(response){
                $scope.stylesobj.styles = response;
            }).error(function(){
                alert('error');
            });
        }

        $scope.form = {};
        $scope.resetObj = $scope.style;
        $scope.resetItemForm = function(style){
            $scope.formOpen = false;
            $scope.form.updateForm.$setPristine();
            $scope.updatedStyle = angular.copy(style);
        }
    });
angular.module('bigeaterdashboardApp')
    .controller('tennancyUsers', function($scope, $rootScope, $log, $location, resService, Upload, baseUrl, Auth, $translate){

        $scope.roles = [
            {"name" : "Shop Administrator", "value" : "ROLE_SHOPADMIN"},
            {"name" : "Shop user", "value" : "ROLE_SHOPUSER"}
        ];

        $scope.shopUserAccount = {};
        $scope.shopUserAccount.roles = ["ROLE_SHOPADMIN", "ROLE_SHOPUSER"];
        $scope.shopUserAccount.langKey = $translate.use();

        $scope.users = [];
        $scope.shopInfoId = 1;

        $scope.$watch('$viewContentLoaded', function(){
            resService.getEmployees($scope.shopInfoId).success(function(response){
                $scope.users = response;
            }).error(function(){
                alert('error');
            });
        });

        $scope.create = function() {

            Auth.createShopUser($scope.shopUserAccount).then(function () {
                $scope.success = 'OK';
                resService.getEmployees($scope.shopInfoId).success(function(response){
                    $scope.users = response;
                }).error(function(){
                    alert('error');
                });
            }).catch(function (response) {
                $scope.success = null;
                if (response.status === 400 && response.data === 'login already in use') {
                    $scope.errorUserExists = 'ERROR';
                } else if (response.status === 400 && response.data === 'e-mail address already in use') {
                    $scope.errorEmailExists = 'ERROR';
                } else {
                    $scope.error = 'ERROR';
                }
            });

        }

        //$scope.users = [
        //    {
        //        "firstName": "fan",
        //        "lastName": "yang",
        //        "email": "yangfanfinland@gmail.com",
        //        "expand" : false
        //    },
        //    {
        //        "firstName": "teng",
        //        "lastName": "wu",
        //        "email": "wuteng@gmail.com",
        //        "expand" : false
        //    },
        //    {
        //        "firstName": "tong",
        //        "lastName": "wang",
        //        "email": "wangtong@gmail.com",
        //        "expand" : false
        //    },
        //    {
        //        "firstName": "jiao",
        //        "lastName": "zhong",
        //        "email": "zhongjiao@gmail.com",
        //        "expand" : false
        //    }
        //];

        $scope.showExpand = function(user){
            user.expand = !user.expand;
        }
    });
