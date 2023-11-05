var mainImg = document.getElementById('main-img');
var smallimg = document.getElementsByClassName('small-img');

function changeImage(element) {
    mainImg.src = element.src;
}