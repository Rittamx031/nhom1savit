let app_product = angular.module("product", []);
app_product.controller("product-ctrl", function ($scope, $http, $timeout){
    $scope.products = [];
    $scope.cates = [];
    $scope.producers = [];
    $scope.materials = [];
    $scope.formUpdate = {};
    $scope.formInput = {};
    $scope.showAlert = false;
    $scope.showError = false;
    $scope.load = function () {$scope.loading=true}
    $scope.unload = function () {$scope.loading=false}

    imgShow("image", "image-preview");
    imgShow("image-update", "image-preview-update");

     function imgShow (textInput, textPreview) {
        const imageInput = document.getElementById(textInput);
        const imagePreview = document.getElementById(textPreview);
        imageInput.addEventListener("change", function () {
            if (imageInput.files && imageInput.files[0]) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    imagePreview.src = e.target.result;
                };
                reader.readAsDataURL(imageInput.files[0]);
            }
        });
    }

    let allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif)$/i;
    $scope.showErrorImg = function (message) {
        $scope.alertErrorImg = message;
        $scope.showError = true;
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
        $http.get("/rest/products").then(resp => {
            $scope.products = resp.data;
        });
        $http.get("/rest/categories").then(resp => {
            $scope.cates = resp.data;
        })
        $http.get("/rest/producers").then(resp => {
            $scope.producers = resp.data;
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
        let fileInput = document.getElementById("image");
        if (!allowedExtensions.exec(fileInput.value)) {
            $scope.showErrorImg("Please upload file having extensions .jpeg/.jpg/.png/.gif only")
            return;
        } else if (fileInput.files.length > 0) {
            let data = new FormData();
            data.append('file', fileInput.files[0]);
            $scope.load();
            $http.post('/rest/upload/img', data, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(resp => {
                $scope.formInput.path = resp.data.name;
                let item = angular.copy($scope.formInput);
                $http.post(`/rest/products`, item).then(resp => {
                    $scope.showSuccessMessage("Create product successfully!");
                    $scope.initialize();
                    $scope.resetFormInput();
                    $('#modalAdd').modal('hide');
                    $scope.showError = false;
                    $scope.unload();
                }).catch(error => {
                    console.log("Error", error);
                    $scope.unload();
                    return;
                })
            }).catch(error => {
                console.log("Error", error);
                $scope.unload();
            })
        }
    }

    $scope.apiUpdate = function () {
        let item = angular.copy($scope.formUpdate);
        $http.put(`/rest/products/${item.id}`, item).then(resp => {
            $scope.showSuccessMessage("Update product successfully!")
            $scope.resetFormUpdate();
            $scope.initialize();
            $('#modalUpdate').modal('hide');
            $scope.showError = false;
            $scope.unload();
        }).catch(error => {
            console.log("Error", error);
            return;
        })
    }

    $scope.update = function() {
        let fileInput = document.getElementById("image-update");
        if ($scope.formUpdate.path.length > 0 && !fileInput.files.length > 0) {
            $scope.apiUpdate();
        } else {
            let data = new FormData();
            data.append('file', fileInput.files[0]);
            $scope.load();
            $http.post('/rest/upload/img', data, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(resp => {
                $scope.formUpdate.path = resp.data.name;
                $scope.apiUpdate();
                $scope.unload();
            }).catch(error => {
                console.log("Error", error);
                $scope.unload();
            })
        }
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
        let fileInput = document.getElementById("image-update");
        let imagePreviewUpdate = document.getElementById("image-preview-update")
        imagePreviewUpdate.src = "/assets/img/no-img.svg";
        fileInput.value = "";
        fileInput.type = "file";
        $scope.formUpdateProduct.$setPristine();
        $scope.formUpdateProduct.$setUntouched();
    }

    $scope.resetFormInput = function () {
        $scope.formInput = {};
        let fileInput = document.getElementById("image");
        let imagePreview =  document.getElementById("image-preview");
        imagePreview.src = "/assets/img/no-img.svg";
        fileInput.value = "";
        fileInput.type = "file";
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