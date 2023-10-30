let app_discount = angular.module("discount", []);
app_discount.controller("discount-ctrl", function ($scope, $http, $timeout){
    $scope.discounts = [];
    $scope.productDetails = [];
    $scope.discountProductByStatus = [];
    $scope.formUpdate = {};
    $scope.formInput = {};
    $scope.alldispro = [];
    $scope.showAlert = false;
    $scope.discountById = [];
    $scope.oneDiscount = null;
    $scope.dataall = {
        sock_detail: {},
        discount: {}
    };

    $scope.discountOne = function (dis) {
        $scope.oneDiscount = dis;
        $http.get(`/rest/discount-sock/${dis.id}`).then(resp => {
            $scope.discountProductByStatus = resp.data;
            $scope.productDetails.forEach(function(product) {
                product.selected = false;
            });
            $scope.discountProductByStatus.forEach(function(product) {
                let matchedProduct = $scope.productDetails.find(function(item) {
                    return item.id === product.sock_detail.id;
                });

                if (matchedProduct) {
                    matchedProduct.selected = true;
                }
            });
        })
        $http.get(`/rest/discount-sock/discount/${dis.id}`).then(resp => {
            $scope.discountById = resp.data;
        });
    }

        $scope.save = function () {
            $scope.productDetails.forEach(function (product) {
                if (product.selected) {
                    let findProduct = $scope.discountById.find(function (item){
                        return item.sock_detail.id === product.id;
                    });
                    if (!findProduct) {
                        $scope.dataall.sock_detail = product;
                        $scope.dataall.discount = $scope.oneDiscount;
                        let item = angular.copy($scope.dataall);
                        $http.post(`/rest/discount-sock`, item).then(resp => {
                            $scope.showSuccessMessage("Applying Product successfully!")
                            $scope.initialize();
                            $('#modal-product').modal('hide');
                        }).catch(error => {
                            console.log("Error", error);
                        })
                    } else {
                        $scope.dataall.sock_detail = product;
                        $scope.dataall.discount = $scope.oneDiscount;
                        $scope.dataall.status = true;
                        let item = angular.copy($scope.dataall);
                        $http.put(`/rest/discount-sock`, item).then(resp => {
                            $scope.showSuccessMessage("Applying Product successfully!")
                            $scope.initialize();
                            $('#modal-product').modal('hide');
                        }).catch(error => {
                            console.log("Error", error);
                        })
                    }
                } else {
                    let findProduct = $scope.discountById.find(function (item){
                        return item.sock_detail.id === product.id;
                    });
                    if (findProduct) {
                        $scope.dataall.sock_detail = product;
                        $scope.dataall.discount = $scope.oneDiscount;
                        $scope.dataall.status = false;
                        let item = angular.copy($scope.dataall);
                        $http.put(`/rest/discount-sock`, item).then(resp => {
                            $scope.showSuccessMessage("Applying Product successfully!")
                            $scope.initialize();
                            $('#modal-product').modal('hide');
                        }).catch(error => {
                            console.log("Error", error);
                        })
                    }
                }
            })
        }

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
        $http.get("/rest/product-detail").then(resp => {
            $scope.productDetails = resp.data;
        })
        $http.get("/rest/discount-sock/getAll").then(resp => {
            $scope.alldispro = resp.data;
        })
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