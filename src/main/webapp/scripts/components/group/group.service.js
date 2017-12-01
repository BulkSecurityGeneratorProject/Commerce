'use strict';

angular.module('bigeaterdashboardApp')
    .factory('Group', function ($rootScope, $cookies, $http, $q, baseUrl) {
        var getShopGroupCategory = function () {
            var get_shop_group_category_url = baseUrl + '/shop/getGroupCategory';
            return $http({method: 'GET', url: get_shop_group_category_url});
        }

        var addShopGroup = function () {
            var add_shop_group_url = baseUrl + '/shop/addGroup';
            var dataObj = {
                name: document.getElementById("groupName").value,
                type: "group",
                editing: false,
                categories: [],
                sortOrder: 3
            }
            return $http({
                url: add_shop_group_url,
                method: 'POST',
                data: dataObj
            });
        }

        var editShopGroup = function (group) {
            var edit_shop_group_url = baseUrl + '/shop/editGroup';
            var dataObj = {
                id: group.id,
                name: group.name,
                type: group.type,
                editing: group.editing,
                categories: group.categories,
                sortOrder: group.sortOrder
            }
            return $http({
                url: edit_shop_group_url,
                method: 'POST',
                data: dataObj
            });
        }

        var deleteShopGroup = function (group) {
            var delete_shop_group_url = baseUrl + '/shop/deleteGroup';
            var dataObj = {
                id: group.id,
                name: group.name,
                type: group.type,
                editing: group.editing,
                categories: group.categories,
                sortOrder: group.sortOrder
            }
            return $http({
                url: delete_shop_group_url,
                method: 'POST',
                data: dataObj
            });
        }

        var addShopGroupCategory = function (group) {
            var add_shop_group_category_url = baseUrl + '/shop/addGroupCategorty';
            var dataObj = {
                name: group.newCategoryName,
                type: "category",
                //sortOrder: group.categories.length,
                // 此处临时用sortOrder传递groupid数据，须解决
                sortOrder: group.id
            }
            return $http({
                url: add_shop_group_category_url,
                method: 'POST',
                data: dataObj
            });
        }

        var editShopGroupCategory = function (group, category) {
            var edit_shop_group_category_url = baseUrl + '/shop/editGroupCategorty';
            var dataObj = {
                id: category.id,
                name: category.name,
                type: "category",
                sortOrder: group.categories.length
            }
            return $http({
                url: edit_shop_group_category_url,
                method: 'POST',
                data: dataObj
            });
        }

        var deleteShopGroupCategory = function (group, category) {
            var delete_shop_group_category_url = baseUrl + '/shop/deleteGroupCategorty';
            var dataObj = {
                id: category.id
            }
            return $http({
                url: delete_shop_group_category_url,
                method: 'POST',
                data: dataObj
            });
        }

        return {
            getShopGroupCategory: getShopGroupCategory,
            addShopGroup: addShopGroup,
            editShopGroup: editShopGroup,
            deleteShopGroup: deleteShopGroup,
            addShopGroupCategory: addShopGroupCategory,
            editShopGroupCategory: editShopGroupCategory,
            deleteShopGroupCategory: deleteShopGroupCategory
        }
    }
);
