var mainImg = document.getElementById('main-img');
var smallimg = document.getElementsByClassName('small-img');

function changeImage(element) {
    var allImages = document.querySelectorAll('.small-img');
    allImages.forEach(function(img) {
        img.classList.remove('active-img');
    });

    // Thêm lớp 'active-img' cho ảnh được click
    element.classList.add('active-img');
    mainImg.src = element.src;
}

$('.owl-carousel').owlCarousel({
    loop:true,
    margin:10,
    nav:true,
    navText: ["<div class='nav-button owl-prev'>‹</div>", "<div class='nav-button owl-next'>›</div>"],
    responsive:{
        0:{
            items:1
        },
        600:{
            items:3
        },
        1000:{
            items:5
        }
    }
})

let app_user = angular.module("app-user");
app_user.controller("user-ctrl", function ($scope, $http){

});

