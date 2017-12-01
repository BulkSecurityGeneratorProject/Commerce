angular.module('bigeaterdashboardApp')
    .controller('GroupController', function ($scope, $cookies, $http, Group) {
        $scope.groups = [];

        $scope.$watch('$viewContentLoaded', function(){
            Group.getShopGroupCategory().success(function(response){
                $scope.groups = response;
            });
        });

        $scope.addGroup = function(){
            Group.addShopGroup().success(function(response){
                $scope.groups = response;
            }).error(function(){
                alert('error');
            });
            document.getElementById("groupName").value = '';
        };

        $scope.editGroup = function(group) {
            group.editing = true;
        };

        $scope.cancelEditingGroup = function(group) {
            group.editing = false;
        };

        $scope.saveGroup = function(group) {
            Group.editShopGroup(group).success(function(response){
                $scope.groups = response;
            }).error(function(){
                alert('error');
            });
            group.editing = false;
        };

        $scope.removeGroup = function(group) {
            Group.deleteShopGroup(group).success(function(response){
                $scope.groups = response;
            }).error(function(){
                alert('error');
            });
        };

        $scope.addCategory = function(group) {
            Group.addShopGroupCategory(group).success(function(response){
                $scope.groups = response;
            }).error(function(){
                alert('error');
            });
            group.newCategoryName = '';
        };

        $scope.editCategorty = function(group, category) {
            category.editing = true;
        }

        $scope.cancelEditingCategory = function(group, category) {
            category.editing = false;
        }

        $scope.saveCategory = function(group, category) {
            Group.editShopGroupCategory(group, category).success(function(response){
                $scope.groups = response;
            }).error(function(){
                alert('error');
            });
            category.editing = false;
        }

        $scope.removeCategory = function(group, category) {
            Group.deleteShopGroupCategory(group, category).success(function(response){
                $scope.groups = response;
            }).error(function(){
                alert('error');
            });
        };
    }
);
