let app_product = angular.module("product", []);
app_product.controller("product-ctrl", function ($scope, $http, $timeout){
    $scope.products = [];
    $scope.cates = [];
    $scope.producers = [];
    $scope.materials = [];
    $scope.sizes = [];
    $scope.formUpdate = {};
    $scope.formInput = {};
    $scope.showAlert = false;
    $scope.showError = false;

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
        $http.get("/rest/products").then(resp => {
            $scope.products = resp.data;
        });
        $http.get("/rest/categories").then(resp => {
            $scope.cates = resp.data;
        })
        $http.get("/rest/producers").then(resp => {
            $scope.producers = resp.data;
        })
        $http.get("/rest/sizes").then(resp => {
            $scope.sizes = resp.data;
        })
        $http.get("/rest/materials").then(resp => {
            $scope.materials = resp.data;
        })
    }
    $scope.initialize();

    $scope.edit = function(product) {
        $scope.formUpdate = angular.copy(product);
    }
    $scope.create = function() {
        let item = angular.copy($scope.formInput);
        $http.post(`/rest/products`, item).then(resp => {
            $scope.showSuccessMessage("Create product successfully!")
            $scope.resetFormInput();
            $scope.initialize();
            $('#modalAdd').modal('hide');
        }).catch(error => {
            console.log("Error", error);
        })
    }

    $scope.update = function() {
        let item = angular.copy($scope.formUpdate);
        $http.put(`/rest/products/${item.id}`, item).then(resp => {
            $scope.showSuccessMessage("Update product successfully!")
            $scope.resetFormUpdate();
            $scope.initialize();
            $('#modalUpdate').modal('hide');
        }).catch(error => {
            console.log("Error", error);
        })
    }

    $scope.delete = function(item) {
        $http.delete(`/rest/products/${item.id}`).then(resp => {
            $scope.showSuccessMessage("Delete product successfully!")
            $scope.initialize();
        }).catch(error => {
            console.log("Error", error);
        });
    }

    $scope.resetFormUpdate = function () {
        $scope.formUpdate = {};
        $scope.formUpdateProduct.$setPristine();
        $scope.formUpdateProduct.$setUntouched();
    }

    $scope.resetFormInput = function () {
        $scope.formInput = {};
        $scope.formAddProduct.$setPristine();
        $scope.formAddProduct.$setUntouched();
    }

    $scope.paper = {
        page: 0,
        size: 5,
        get items() {
            let start = this.page * this.size;
            return $scope.products.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.products.length / this.size)
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