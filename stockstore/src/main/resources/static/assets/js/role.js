let app = angular.module("app", []);
app.controller("product-ctrl", function ($scope, $http){
    $scope.roles = [];
    $scope.form = {};
    $scope.initialize = function() {
        $http.get("/rest/roles").then(resp => {
            $scope.roles = resp.data;
        });
    }
    $scope.initialize();
    $scope.edit = function(role) {
        $scope.form = angular.copy(role);
    }
})