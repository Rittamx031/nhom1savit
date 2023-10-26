let app_producer = angular.module("producer", []);
app_producer.controller("producer-ctrl", function ($scope, $http, $timeout){
    $scope.producers = [];
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
        $http.get("/rest/producers").then(resp => {
            $scope.producers = resp.data;
        });
    }
    $scope.initialize();
    $scope.edit = function(cate) {
        $scope.formUpdate = angular.copy(cate);
    }
    $scope.create = function() {
        let item = angular.copy($scope.formInput);
        $http.post(`/rest/producers`, item).then(resp => {
            $scope.showSuccessMessage("Create producer successfully!")
            $scope.resetFormInput();
            $scope.initialize();
            $('#modalAdd').modal('hide');
        }).catch(error => {
            console.log("Error", error);
        })
    }

    $scope.update = function() {
        let item = angular.copy($scope.formUpdate);
        $http.put(`/rest/producers/${item.id}`, item).then(resp => {
            $scope.showSuccessMessage("Update producer successfully!")
            $scope.resetFormUpdate();
            $scope.initialize();
            $('#modalUpdate').modal('hide');
        }).catch(error => {
            console.log("Error", error);
        })
    }

    $scope.delete = function(item) {
        $http.delete(`/rest/producers/${item.id}`).then(resp => {
            $scope.showSuccessMessage("Delete producer successfully!")
            $scope.initialize();
        }).catch(error => {
            console.log("Error", error);
        });
    }

    $scope.resetFormUpdate = function () {
        $scope.formUpdate = {};
        $scope.formUpdateProducer.$setPristine();
        $scope.formUpdateProducer.$setUntouched();
    }

    $scope.resetFormInput = function () {
        $scope.formInput = {};
        $scope.formAddProducer.$setPristine();
        $scope.formAddProducer.$setUntouched();
    }

    $scope.paper = {
        page: 0,
        size: 5,
        get items() {
            let start = this.page * this.size;
            return $scope.producers.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.producers.length / this.size)
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