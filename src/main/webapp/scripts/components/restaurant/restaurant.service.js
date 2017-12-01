'use strict';

angular.module('bigeaterdashboardApp')
    .factory('resService', function ($log, $http, $q, baseUrl) {
        var getProductCategories = function() {
            var get_product_categories_url = baseUrl + '/product/getCategoryList';
            return $http({
                method: 'GET',
                url: get_product_categories_url,
                params: {
                    shopinfoid: 1
                }
            });
        }

        // add father type
        var addCategory = function(type){
            var add_product_category_url = baseUrl + '/product/addCategory';
            var dataObj = {
                name : type.name,
                note : "",
                childtypes : [],
                shopInfo : {
                    id : 1
                }
            }
            return $http({
                method: 'POST',
                url: add_product_category_url,
                data: dataObj
            });
        }

        var addChildCategory = function(newType, type){
            var add_product_category_url = baseUrl + '/product/addCategory';
            var dataObj = {
                name : newType.name,
                note : type.id, //临时传递父类id
                childtypes : [],
                //parent: {
                //    id : type.id
                //},
                shopInfo : {
                    id : 1
                }
            }
            return $http({
                method: 'POST',
                url: add_product_category_url,
                data: dataObj
            });
        }

        var editCategory = function(type){
            var edit_product_category_url = baseUrl + '/product/editCategory';
            var dataObj = {
                id : type.id,
                name : type.name,
                note : ""
            }
            return $http({
                method: 'POST',
                url: edit_product_category_url,
                data: dataObj
            });
        }

        var deleteCategory = function(type){
            var delete_product_category_url = baseUrl + '/product/deleteCategory';
            var dataObj = {
                id : type.id,
                shopInfo : {
                    id : 1
                }
            }
            return $http({
                method: 'POST',
                url: delete_product_category_url,
                data: dataObj
            });
        }

        var getProductBrands = function() {
            var get_product_brands_url = baseUrl + '/product/getProductBrandList';
            return $http({
                method: 'GET',
                url: get_product_brands_url,
                params: {
                    shopinfoid: 1
                }
            });
        }

        var getPaginationBrands = function(pageNumber) {
            var get_product_pagination_brands_url = baseUrl + '/product/getPaginationBrandList';
            return $http({
                method: 'GET',
                url: get_product_pagination_brands_url,
                params: {
                    shopinfoid: 1,
                    pageNumber: pageNumber
                }
            });
        }

        var deleteProductBrand = function(brand){
            var delete_product_brand_url = baseUrl + '/product/deleteBrand';
            var dataObj = {
                id : brand.id
            }
            return $http({
                method: 'POST',
                url: delete_product_brand_url,
                data: dataObj
            });
        }

        var getProducts = function(){
            var get_products_url = baseUrl + '/product/getProducts';
            return $http({
                method: 'GET',
                url: get_products_url,
                params: {
                    shopinfoid: 1
                }
            });
        }

        var getPaginationProducts = function(pageNumber){
            var get_pagination_products_url = baseUrl + '/product/getPaginationProducts';
            return $http({
                method: 'GET',
                url: get_pagination_products_url,
                params: {
                    shopinfoid: 1,
                    pageNumber: pageNumber
                }
            });
        }

        var addProduct = function(product){
            var add_product_url = baseUrl + '/product/addProduct';
            var dataObj = {
                code : product.code,
                name : product.name,
                brand : {
                    id : product.brand
                },
                model : product.model,
                baseprice : product.baseprice,
                marketprice : product.marketprice,
                sellprice : product.sellprice,
                weight : product.weight,
                description : product.description,
                buyexplain : product.buyexplain,
                producttype : product.producttype,
                shopInfo : {
                    id : 1
                }
            }
            return $http({
                method: 'POST',
                url: add_product_url,
                data: dataObj
            });
        }

        var editProduct = function(product){
            var edit_product_url = baseUrl + '/product/addProduct';
            var dataObj = {
                id : product.id,
                code : product.code,
                name : product.name,
                brand : product.brand,
                model : product.model,
                baseprice : product.baseprice,
                marketprice : product.marketprice,
                sellprice : product.sellprice,
                weight : product.weight,
                description : product.description,
                buyexplain : product.buyexplain,
                producttype : product.producttype.id,
                shopInfo : {
                    id : 1
                }
            }
            return $http({
                method: 'POST',
                url: edit_product_url,
                data: dataObj
            });
        }

        var discommendProduct = function(product){
            var discommend_product_url = baseUrl + '/product/changeProductCommendStatus';
            var dataObj = {
                id : product.id,
                commend : false,
                shopInfo : {
                    id : 1
                }
            }
            return $http({
                method: 'POST',
                url: discommend_product_url,
                data: dataObj
            });
        }

        var commendProduct = function(product){
            var commend_product_url = baseUrl + '/product/changeProductCommendStatus';
            var dataObj = {
                id : product.id,
                commend : true,
                shopInfo : {
                    id : 1
                }
            }
            return $http({
                method: 'POST',
                url: commend_product_url,
                data: dataObj
            });
        }

        var disableProduct = function(product){
            var disable_product_url = baseUrl + '/product/changeProductVisibleStatus';
            var dataObj = {
                id : product.id,
                visible : false,
                shopInfo : {
                    id : 1
                }
            }
            return $http({
                method: 'POST',
                url: disable_product_url,
                data: dataObj
            });
        }

        var enableProduct = function(product){
            var enable_product_url = baseUrl + '/product/changeProductVisibleStatus';
            var dataObj = {
                id : product.id,
                visible : true,
                shopInfo : {
                    id : 1
                }
            }
            return $http({
                method: 'POST',
                url: enable_product_url,
                data: dataObj
            });
        }

        var deleteProduct = function(product){
            var delete_product_url = baseUrl + '/product/deleteProduct';
            var dataObj = {
                id : product.id,
                shopInfo : {
                    id : 1
                }
            }
            return $http({
                method: 'POST',
                url: delete_product_url,
                data: dataObj
            });
        }

        var getStyles = function(product){
            var get_styles_url = baseUrl + '/product/getProductStyleListByProduct';
            var dataObj = {
                id : product.id,
            }
            return $http({
                method: 'POST',
                url: get_styles_url,
                data: dataObj
            });
        }

        var deleteStyle = function(style){
            var delete_style_url = baseUrl + '/product/deleteStyle';
            var dataObj = {
                id : style.id
            }
            return $http({
                method: 'POST',
                url: delete_style_url,
                data: dataObj
            });
        }

        var enableStyle = function(style){
            var enable_style = baseUrl + '/product/changeStyleVisible';
            var dataObj = {
                id : style.id,
                visible : true
            }
            return $http({
                method: 'POST',
                url: enable_style,
                data: dataObj
            });
        }

        var disableStyle = function(style){
            var disable_style = baseUrl + '/product/changeStyleVisible';
            var dataObj = {
                id : style.id,
                visible : false
            }
            return $http({
                method: 'POST',
                url: disable_style,
                data: dataObj
            });
        }

        var getEmployees = function(shopInfoId){
            var get_employee_url = baseUrl + '/api/users/employee/' + shopInfoId;
            return $http({
                method: 'GET',
                url: get_employee_url
            });
        }

        return {
            getProductCategories : getProductCategories,
            addCategory : addCategory,
            editCategory : editCategory,
            deleteCategory : deleteCategory,
            addChildCategory : addChildCategory,

            getProductBrands : getProductBrands,
            getPaginationBrands : getPaginationBrands,
            deleteProductBrand : deleteProductBrand,

            getProducts : getProducts,
            getPaginationProducts : getPaginationProducts,
            addProduct : addProduct,
            editProduct : editProduct,
            discommendProduct : discommendProduct,
            commendProduct : commendProduct,
            disableProduct : disableProduct,
            enableProduct : enableProduct,
            deleteProduct : deleteProduct,

            getStyles : getStyles,
            deleteStyle : deleteStyle,
            enableStyle : enableStyle,
            disableStyle : disableStyle,

            getEmployees : getEmployees
        }
    });
