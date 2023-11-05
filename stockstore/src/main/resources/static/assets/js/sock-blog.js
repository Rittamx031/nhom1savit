let app_blog = angular.module("blog", []);
app_blog.controller("blog-ctrl", function ($scope, $http, $timeout){
    $scope.blogs = [];
    $scope.products = [];
    $scope.blogStatus = [];
    $scope.blogByID = [];
    $scope.formUpdate = {};
    $scope.formInput = {};
    $scope.valueBlogProduct = {};
    $scope.oneBlog = null;
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

    $scope.blogOne = function (blog) {
        $scope.oneBlog = blog;
        $http.get(`/rest/blog-sock/blog-status/${blog.id}`).then(resp => {
            $scope.blogStatus = resp.data;
            $scope.products.forEach(function(product) {
                product.selected = false;
            });
            $scope.blogStatus.forEach(function(blogst) {
                let matchedProduct = $scope.products.find(function(item) {
                    return item.id === blogst.sock.id;
                });

                if (matchedProduct) {
                    matchedProduct.selected = true;
                }
            });
        })
        $http.get(`/rest/blog-sock/blog/${blog.id}`).then(resp => {
            $scope.blogByID = resp.data;
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
        $http.get("/rest/blogs").then(resp => {
            $scope.blogs = resp.data;
        });
        $http.get("/rest/products").then(resp => {
            $scope.products = resp.data;
        });

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
                $http.post(`/rest/blogs`, item).then(resp => {
                    $scope.showSuccessMessage("Create blog successfully!");
                    $scope.resetFormInput();
                    $scope.initialize();
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

    $scope.save = function () {
        $scope.products.forEach(function (product) {
            if (product.selected) {
                let findProduct = $scope.blogByID.find(function (item){
                    return item.sock.id === product.id;
                });
                if (!findProduct) {
                    $scope.valueBlogProduct.sock = product;
                    $scope.valueBlogProduct.blog = $scope.oneBlog;
                    let item = angular.copy($scope.valueBlogProduct);
                    $http.post(`/rest/blog-sock`, item).then(resp => {
                        $scope.showSuccessMessage("Applying Product successfully!")
                        $scope.initialize();
                        $('#modal-product').modal('hide');
                    }).catch(error => {
                        console.log("Error", error);
                    })
                } else {
                    $scope.valueBlogProduct.sock = product;
                    $scope.valueBlogProduct.blog = $scope.oneBlog;
                    $scope.valueBlogProduct.status = true;
                    let item = angular.copy($scope.valueBlogProduct);
                    $http.put(`/rest/blog-sock`, item).then(resp => {
                        $scope.showSuccessMessage("Applying Product successfully!")
                        $scope.initialize();
                        $('#modal-product').modal('hide');
                    }).catch(error => {
                        console.log("Error", error);
                    })
                }
            } else {
                let findProduct = $scope.blogByID.find(function (item){
                    return item.sock.id === product.id;
                });
                if (findProduct) {
                    $scope.valueBlogProduct.sock = product;
                    $scope.valueBlogProduct.blog = $scope.oneBlog;
                    $scope.valueBlogProduct.status = false;
                    let item = angular.copy($scope.valueBlogProduct);
                    $http.put(`/rest/blog-sock`, item).then(resp => {
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

    $scope.apiUpdate = function () {
        let item = angular.copy($scope.formUpdate);
        $http.put(`/rest/products/${item.id}`, item).then(resp => {
            $scope.showSuccessMessage("Update product successfully!")
            $scope.resetFormUpdate();
            $scope.initialize();
            $('#modalUpdate').modal('hide');
            $scope.showError = false;
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

    $scope.resetFormUpdate = function () {
        $scope.formUpdate = {};
        let fileInput = document.getElementById("image-update");
        let imagePreviewUpdate = document.getElementById("image-preview-update")
        imagePreviewUpdate.src = "/assets/img/no-img.svg";
        fileInput.value = "";
        fileInput.type = "file";
        $scope.formAddBlog.$setPristine();
        $scope.formAddBlog.$setUntouched();
    }

    $scope.resetFormInput = function () {
        $scope.formInput = {};
        let fileInput = document.getElementById("image");
        let imagePreview =  document.getElementById("image-preview");
        imagePreview.src = "/assets/img/no-img.svg";
        fileInput.value = "";
        fileInput.type = "file";
        $scope.formUpdateBlog.$setPristine();
        $scope.formUpdateBlog.$setUntouched();
    }

    $scope.paper = {
        page: 0,
        size: 5,
        get items() {
            let start = this.page * this.size;
            return $scope.blogs.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.blogs.length / this.size)
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