
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!--

TemplateMo 546 Sixteen Clothing

https://templatemo.com/tm-546-sixteen-clothing

-->

<!-- Additional CSS Files -->
<link rel="stylesheet" href="/assets/css/fontawesome.css">
<link rel="stylesheet" href="/assets/css/templatemo-sixteen.css">
<link rel="stylesheet" href="/assets/css/owl.css">
<div class="banner header-text">
    <div class="owl-banner owl-carousel">
        <div class="banner-item-01">
            <div class="text-content">
                <h4>Best Offer</h4>
                <h2>New Arrivals On Sale</h2>
            </div>
        </div>
        <div class="banner-item-02">
            <div class="text-content">
                <h4>Flash Deals</h4>
                <h2>Get your best products</h2>
            </div>
        </div>
        <div class="banner-item-03">
            <div class="text-content">
                <h4>Last Minute</h4>
                <h2>Grab last minute deals</h2>
            </div>
        </div>
    </div>
</div>
<!-- Banner Ends Here -->

<div class="latest-products">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-heading">
                    <h2>TOP 10 CLOTHES</h2>
                    <a href="">view all products <i class="fa fa-angle-right"></i></a>
                </div>
            </div>
            <c:forEach var="item" items="${page.content}">
                <div class="col-md-3">
                    <div class="product-item">
                        <a href="#"><img src="/static/upload/${item.image}" alt=""></a>
                        <div class="down-content">
                            <a href="#"><h4>${item.name}</h4></a>
                            <a> $${item.price}</a>
                            <br>
                            <h7>Reduced : $${item.price*(100-item.discount)*0.01}</h7>
                            <p>San pham co tai Sumishop</p>
                            <ul class="stars">
                                <li><i class="fa fa-star"></i></li>
                                <li><i class="fa fa-star"></i></li>
                                <li><i class="fa fa-star"></i></li>
                                <li><i class="fa fa-star"></i></li>
                                <li><i class="fa fa-star"></i></li>
                            </ul>
                            </br>
                            <a class="filled-button" href="/shoppingcart/add/${item.id}">Buy</a>
                        </div>

                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>