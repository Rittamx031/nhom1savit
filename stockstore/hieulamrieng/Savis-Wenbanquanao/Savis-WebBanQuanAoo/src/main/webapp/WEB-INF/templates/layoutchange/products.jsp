
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

    <title>Sixteen Clothing Products</title>

    <!-- Bootstrap core CSS -->
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!--

TemplateMo 546 Sixteen Clothing

https://templatemo.com/tm-546-sixteen-clothing

-->

    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="/assets/css/fontawesome.css">
    <link rel="stylesheet" href="/assets/css/templatemo-sixteen.css">
    <link rel="stylesheet" href="/assets/css/owl.css">
    <style>

    </style>
    <!-- Page Content -->
    <div class="page-heading products-heading header-text">
      <div class="container">

      </div>
    </div>

    
    <div class="products">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="filters">
<%--&lt;%&ndash;--%>
    <form class="form-inline my-2 my-lg-0 "  action="/product/search" method="post">
        <input type="search" class="form-control" value="${keywords}"name="keywords" placeholder="Name Product" aria-label="Recipient's username" aria-describedby="button-addon2" >
        <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Seach</button>
        </div>
        <ul>
            <c:forEach var="cate" items="${categories}">
                <li ><a  href="/Product/add/${cate.id}" >${cate.name}</a></li>
            </c:forEach>
            <li><a href="/Home/product/sort?field=price" style="color: #f33f3f"> Low to high</a></li>
            <li><a href="/Home/product/sortdesc?field=price" style="color: #f33f3f"> High to low</a></li>
        </ul>
        <ul>
            <li><a href="/product/price/between0_50" style="color: #f33f3f"> 0-50$</a></li>
            <li><a href="/product/price/between50_100" style="color: #f33f3f"> 50-100$</a></li>
            <li><a href="/product/price/between100_150" style="color: #f33f3f"> 100-150$</a></li>
            <li><a href="/product/price/between150_200" style="color: #f33f3f"> 200-250$</a></li>
            <li><a href="/product/price/between200" style="color: #f33f3f"> 250$ +</a></li>
        </ul>
    </form>
            </div>
          </div>
            <c:forEach var="item" items="${page.content}">
                <div class="col-3">
                    <div class="product-item">
                        <a href="#"><img src="/static/upload/${item.image}" alt=""></a>
                        <div class="down-content">
                            <a href="#"><h4>${item.name}</h4></a>
                            <a> $${item.price}</a>
                            <br>
                            <h7>Giam con : $${item.price*(100-item.discount)*0.01}</h7>

                            <p>San pham co tai sumishop</p>
                            <ul class="stars">
                                <li><i class="fa fa-star"></i></li>
                                <li><i class="fa fa-star"></i></li>
                                <li><i class="fa fa-star"></i></li>
                                <li><i class="fa fa-star"></i></li>
                                <li><i class="fa fa-star"></i></li>
                            </ul>
                            </br>
                            <a class="filled-button" href="/Home/product/about/${item.id}">Buy</a>
                        </div>

                    </div>
                </div>
            </c:forEach>
<%--            <a href="/Home/product?p=0">First</a>--%>
<%--            <a href="/Home/product?p=${page.number-1}">Previous</a>--%>
<%--            <a href="/Home/product?p=${page.number+1}">1</a>--%>
<%--            <a href="/Home/product?p=${page.totalPages-1}">Last</a>--%>
<%--            <ul>--%>
<%--                <li>Số thực thể hiện tại: ${page.numberOfElements}</li>--%>
<%--                <li>Trang số: ${page.number}</li>--%>
<%--                <li>Kích thước trang: ${page.size}</li>--%>
<%--                <li>Tổng số thực thể: ${page.totalElements}</li>--%>
<%--                <li>Tổng số trang: ${page.totalPages}</li>--%>
<%--            </ul>--%>
          <div class="col-md-12">
            <ul class="pages">
              <li > <a href="/Home/product?p=0">1</a></li>
              <li ><a href="/Home/product?p=1">2</a></li>
              <li><a href="/Home/product?p=2">3</a></li>
              <li><a href="/Home/product?p=3">4</a></li>
              <li><a href="#"><i class="fa fa-angle-double-right"></i></a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    
    <footer>
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="inner-content">
              <p>PH21824</p>
            </div>
          </div>
        </div>
      </div>
    </footer>
    <!-- Bootstrap core JavaScript -->
    <script src="/vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


    <!-- Additional Scripts -->
    <script src="/assets/js/custom.js"></script>
    <script src="/assets/js/owl.js"></script>
    <script src="/assets/js/slick.js"></script>
    <script src="/assets/js/isotope.js"></script>
    <script src="/assets/js/accordions.js"></script>


    <script language = "text/Javascript"> 
      cleared[0] = cleared[1] = cleared[2] = 0; //set a cleared flag for each field
      function clearField(t){                   //declaring the array outside of the
      if(! cleared[t.id]){                      // function makes it static and global
          cleared[t.id] = 1;  // you could use true and false, but that's more typing
          t.value='';         // with more chance of typos
          t.style.color='#fff';
          }
      }
    </script>
