let app = angular.module("app", []);
app.controller("product-ctrl", function ($scope, $http, $timeout){
    $scope.roles = [];
    $scope.formUpdate = {};
    $scope.formInput = {};
    $scope.showAlert = false;
    $scope.showSuccessMessage = function(message) {
        $scope.alertMessage = message;
        $scope.showAlert = true;
        $timeout(function() {
            $scope.closeAlert();
        }, 5000);
    }
    $scope.closeAlert = function() {
        $scope.showAlert = false;
    }
    $scope.initialize = function() {
        $http.get("/rest/roles").then(resp => {
            $scope.roles = resp.data;
        });
    }
    $scope.initialize();
    $scope.edit = function(role) {
        $scope.formUpdate = angular.copy(role);
    }
    $scope.create = function() {
        let item = angular.copy($scope.formInput);
        $http.post(`/rest/roles`, item).then(resp => {
            $scope.showSuccessMessage("Create role successfully!")
            $scope.resetFormInput();
            $scope.initialize();
            $('#modalAdd').modal('hide');
        }).catch(error => {
            console.log("Error", error);
        })
    }

    $scope.update = function() {
        let item = angular.copy($scope.formUpdate);
        $http.put(`/rest/roles/${item.id}`, item).then(resp => {
            $scope.showSuccessMessage("Create role successfully!")
            $scope.resetFormUpdate();
            $scope.initialize();
            $('#modalUpdate').modal('hide');
        }).catch(error => {
            console.log("Error", error);
        })
    }

    $scope.delete = function(item) {
        $http.delete(`/rest/roles/${item.id}`).then(resp => {
            $scope.showSuccessMessage("Delete role successfully!")
            $scope.initialize();
        }).catch(error => {
            console.log("Error", error);
        });
    }

    $scope.resetFormUpdate = function () {
        $scope.formUpdate = {};
    }

    $scope.resetFormInput = function () {
        $scope.formInput = {};
        $scope.formAddRole.$setPristine();
        $scope.formAddRole.$setUntouched();
    }

    $scope.paper = {
        page: 0,
        size: 5,
        get items() {
            let start = this.page * this.size;
            return $scope.roles.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.roles.length / this.size)
        },
        first() {
           this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.last();
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.first();
            }
        },
        last() {
            this.page = this.count - 1;
        }
    }
})