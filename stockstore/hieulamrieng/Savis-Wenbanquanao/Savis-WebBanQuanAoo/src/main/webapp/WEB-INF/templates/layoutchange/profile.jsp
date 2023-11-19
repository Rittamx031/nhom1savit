<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <%--    <div class="owl-banner owl-carousel">--%>
    <%--        <div class="banner-item-01">--%>
    <%--            <div class="text-content">--%>
    <%--                <h4>XIN CHAO</h4>--%>
    <%--                <h2>${user.username}</h2>--%>
    <%--            </div>--%>
    <%--        </div>--%>
    <%--    </div>--%>
</div>
<!-- Banner Ends Here -->
<div class="latest-products mt-5">
    <div class="container">

        <div class="content">
            <div class="row">
                <div class="col-md-4">
                    <div class="card card-user">
                        <div class="image">
                        </div>
                        <div class="card-body">
                            <div class="author">
                                <a href="#">
                                    <img class="avatar border-gray" src="/assets/images/${user.photo}" style="width: 150px ; height: 150px;">
                                    <h5 class="title">${user.username}</h5>
                                </a>
                                <p class="description">
<%--                                    @chetfaker--%>
                                </p>
                            </div>
                            <p class="description text-center">
                                "I like the way you work it <br>
                                No diggity <br>
                                I wanna bag it up"
                            </p>
                        </div>
                        <div class="card-footer">
                            <hr>
                            <div class="button-container">
                                <div class="row">
                                    <div class="col-lg-3 col-md-6 col-6 ml-auto">
                                        <h5>12<br><small>Files</small></h5>
                                    </div>
                                    <div class="col-lg-4 col-md-6 col-6 ml-auto mr-auto">
                                        <h5>2GB<br><small>Used</small></h5>
                                    </div>
                                    <div class="col-lg-3 mr-auto">
                                        <h5>24,6$<br><small>Spent</small></h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="card card-user">
                        <div class="card-header">
                            <h5 class="card-title">Edit Account</h5>
                        </div>
                        <div class="card-body">
                            <form>
                                <c:forEach var="item" items="${items}">
                            <div class="row">
                                <div class="col-4">
                                    <div class="form-group">
                                        <label>Id</label>
                                        <input type="text" disabled="true" class="form-control" placeholder="id" value="${item.id}" path="id"/>
                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="form-group">
                                        <label>username</label>
                                        <input type="text" class="form-control" name="username"  placeholder="Username"  value="${item.username}" path="username"/>

                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="form-group">
                                        <label>fullname</label>
                                        <input type="text" class="form-control" placeholder="fullname" value="${item.fullname}" path="fullname"/>

                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="form-group">
                                        <label>email</label>
                                        <input type="text" class="form-control" placeholder="email" value="${item.email}" path="email"/>

                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="form-group">
                                        <label>address</label>
                                        <input type="text" class="form-control" placeholder="address" value="${item.address}" path="address"/>

                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="form-group">
                                        <label>Number Phone</label>
                                        <input type="text" class="form-control" placeholder="phone" value="${item.phone}" path="phone"/>
                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="form-group">

                                        <input type="hidden" class="form-control" placeholder="Username" value="${item.activated}" path="activated"/>
                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="form-group">
                                        <input type="hidden" class="form-control" placeholder="Username" value="" path="admin"/>

                                    </div>
                                </div>
                                <div class="row">
                                    <div class="create ml-auto mr-auto">                                         <%--                                        <button type="submit" class="btn btn-primary btn-round ml-4" formaction="/Home/Account/Update" >Save</button>--%>
                                    </div>
                                    <div class="update ml-auto mr-auto">

                                    </div>
                                </div>
                                </c:forEach>
                                </form>
                            </div>
                            <a>${message}</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

