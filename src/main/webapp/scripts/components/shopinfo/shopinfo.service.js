'use strict';

angular.module('bigeaterdashboardApp')
    .factory('shopService', function ($rootScope, $cookies, $http, $q, baseUrl) {
        var getShopCategory = function() {
            var get_shop_categories_url = baseUrl + '/shop/getShopCategories';
            return $http({method: 'GET', url: get_shop_categories_url});
        }

        var getShops = function() {
            var get_shops_url = baseUrl + '/shop/getShops';
            return $http({method: 'GET', url: get_shops_url});
        }

        var addShop = function(shop) {
            var add_shop_url = baseUrl + '/shop/addShop'
            var dataObj = {
                name : shop.name,
                startDate : shop.startDate,
                endDate : shop.endDate,
                createdate : shop.createdate,
                address : 'Helsinki',
                // 此处暂时用description 传递 category id
                description : shop.shopCategory
            }
            return $http({
                url: add_shop_url,
                method: 'POST',
                data: dataObj
            });
        }

        var editShop = function(shop) {
            var edit_shop_url = baseUrl + '/shop/editShop'
            var dataObj = {
                id : shop.id,
                name : shop.name,
                startDate : shop.startDate,
                endDate : shop.endDate,
                address : shop.address,
                // 此处暂时用description 传递 category id
                description : shop.shopCategory
            }
            return $http({
                url: edit_shop_url,
                method: 'POST',
                data: dataObj
            });
        }

        var deleteShop = function(shop) {
            var delete_shop_url = baseUrl + '/shop/deleteShop'
            var dataObj = {
                id : shop.id
            }
            return $http({
                url: delete_shop_url,
                method: 'POST',
                data: dataObj
            });
        }

        var disableShop = function(shop) {
            var disalbe_shop_url = baseUrl + '/shop/disableShop';
            var dataObj = {
                id : shop.id,
                name : shop.name,
                startDate : shop.startDate,
                endDate : shop.endDate,
                address : shop.address,
                // 此处暂时用description 传递 category id
                description : shop.shopCategory,
                visible : false
            }
            return $http({
                url: disalbe_shop_url,
                method: 'POST',
                data: dataObj
            });
        }

        var enableShop = function(shop) {
            var enable_shop_url = baseUrl + '/shop/enableShop';
            var dataObj = {
                id : shop.id,
                name : shop.name,
                startDate : shop.startDate,
                endDate : shop.endDate,
                address : shop.address,
                // 此处暂时用description 传递 category id
                description : shop.shopCategory,
                visible : true
            }
            return $http({
                url: enable_shop_url,
                method: 'POST',
                data: dataObj
            });
        }

        var setList = function(shopsArray){
            shops.length = 0;
            for(key in shopsArray){
                var shop = shopsArray[key];
                $log.info("shop  : " + shop.shopName);
                shop.startDate = new Date();
                shop.endDate = new Date();
                shops.push(shopsArray[key]);
            }
        }

        return {
            getShopCategory : getShopCategory,
            getShops : getShops,
            setList : setList,
            addShop : addShop,
            editShop : editShop,
            deleteShop : deleteShop,
            disableShop : disableShop,
            enableShop : enableShop
        }
    });
