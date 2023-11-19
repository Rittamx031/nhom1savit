<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!--

TemplateMo 546 Sixteen Clothing

https://templatemo.com/tm-546-sixteen-clothing

-->

<!-- Additional CSS Files -->
<link rel="stylesheet" href="/assets/css/fontawesome.css">
<link rel="stylesheet" href="/assets/css/templatemo-sixteen.css">
<link rel="stylesheet" href="/assets/css/owl.css">
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


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

<header class="" style="background-color: #034694">
    <nav class="navbar navbar-expand-lg">
        <div class="container">
            <a class="navbar-brand mt-1" href="/Home/views"><h2>SUMI  <em>SHOP</em></h2></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">

                    <li class="">
                        <a class="nav-link" href="/Home/views"><s:message code="lo.mn.home"/></a>
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link" href="/Home/product"><s:message code="lo.mn.ourproduct"/></a>
                    </li>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="Home/about"> <s:message code="lo.mn.about"/></a>--%>
<%--                    </li>--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="/Home/contact"><s:message code="lo.mn.contact"/></a>--%>

<%--                    </li>--%>
                    <c:if test="${user==null}">
                        <li class="nav-item">
                            <a class="nav-link" href="/account/login"><s:message code="lo.mn.login"/> </a>
<%--                            <a class="nav-link" href="/account/register">Sign Up </a>--%>
                        </li>
                    </c:if>
                    <li class="nav-item ml-1">
                        <div class="dropdown">
                            <a class="nav-link" href="" role="" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" >
                                ${user.username}
                            </a>

                            <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                <a class="dropdown-item" href="/Home/Account/Views/${user.id}">Profile</a>
                                <a class="dropdown-item" href="/Home/YourCart/${user.id}">Your Order</a>
                                <a class="dropdown-item" href="/Home/Account/Edit/${user.username}">Edit Profile</a>
                                <a class="dropdown-item" href="/Home/account/change/${user.id}">Change Password</a>
                                <a class="dropdown-item" href="/account/logout">Log out</a>
                            </div>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a style="padding-left: 10px;" class="nav-icon position-relative text-decoration-none"
                           href="/shoppingcart/index"> <i class="fa fa-fw fa-cart-arrow-down text-white mr-1 mt-3"></i> <span
                                style="margin-top: 15px;height: 15px;width: 13px;background: red;"
                                class="position-absolute  badge rounded-pill text-dark"><b
                                style="color: white; size: 11px;margin-left: -2px;">${NoOfItem}</b></span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<script>
    $(document).ready(function(){
        $("a[href*=lang]").on("click", function(){
            var param = $(this).attr("href");
            $.ajax({
                url: "/Home/views" + param,
                success: function(){
                    location.reload();
                }
            });
            return false;
        })
    })
</script>
