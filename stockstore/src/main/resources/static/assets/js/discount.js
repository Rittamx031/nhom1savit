let app_discount = angular.module("discount", []);
app_discount.controller("discount-ctrl", function ($scope, $http, $timeout){
    $scope.discounts = [];
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
        $http.get("/rest/discounts").then(resp => {
            $scope.discounts = resp.data;
        });
    }
    $scope.initialize();

    $scope.edit = function(discount) {
        $scope.formUpdate = angular.copy(discount);
        $scope.formUpdate.valid_from = new Date(discount.valid_from);
        $scope.formUpdate.valid_until = new Date(discount.valid_until)
    }

    $scope.create = function() {
        let item = angular.copy($scope.formInput);
        $http.post(`/rest/discounts`, item).then(resp => {
            $scope.showSuccessMessage("Create discount successfully!")
            $scope.resetFormInput();
            $scope.initialize();
            $('#modalAdd').modal('hide');
        }).catch(error => {
            console.log("Error", error);
        })
    }

    $scope.update = function() {
        let item = angular.copy($scope.formUpdate);
        $http.put(`/rest/discounts/${item.id}`, item).then(resp => {
            $scope.showSuccessMessage("Update discount successfully!")
            $scope.resetFormUpdate();
            $scope.initialize();
            $('#modalUpdate').modal('hide');
        }).catch(error => {
            console.log("Error", error);
        })
    }

    $scope.resetFormUpdate = function () {
        $scope.formUpdate = {};
        $scope.formUpdateVoucher.$setPristine();
        $scope.formUpdateVoucher.$setUntouched();
    }

    $scope.resetFormInput = function () {
        $scope.formInput = {};
        $scope.formAddVoucher.$setPristine();
        $scope.formAddVoucher.$setUntouched();
    }

    $scope.paper = {
        page: 0,
        size: 5,
        get items() {
            let start = this.page * this.size;
            return $scope.discounts.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.discounts.length / this.size)
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