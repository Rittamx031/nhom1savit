
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">
    <div class="row">
        <div class="col-lg-3 col-md-6 col-sm-6">
            <div class="card card-stats">
                <div class="card-body ">
                    <div class="row">
                        <div class="col-5 col-md-4">
                            <div class="icon-big text-center icon-warning">
                                <i class="nc-icon nc-globe text-warning"></i>
                            </div>
                        </div>
                        <div class="col-7 col-md-8">
                            <div class="numbers">
                                <p class="card-category">User</p>
                                <p class="card-title">${count}<p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card-footer ">
                    <hr>

                </div>
            </div>
        </div>
        <div class="col-lg-3 col-md-6 col-sm-6">

        </div>


    </div>

    <div class="row">
        <div class="col-md-12">
            <div class="card ">

                <div class="card-body ">
                    <h4>${message}</h4>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Username</th>
                            <th scope="col">Address</th>
                            <th scope="col">Price</th>
                            <th scope="col">Available</th>
                            <th scope="col">Confirm</th>
                            <th scope="col">CreateDate</th>
                            <th scope="col">Details / Confirmed</th>
                        </tr>
                        </thead>
                        <c:forEach var="item" items="${items}">
                            <tbody>
                            <tr>
                                <td>${item.username}</td>
                                <td>${item.address}</td>
                                <td>${item.price}</td>
                                <td>${item.available?"Ordered":"Cancel"}</td>
                                <td>${item.confirm?"Confirmed":"Cancel"}</td>
                                <td>${item.createDate}</td>
                               <th><a class="" href="/Admin/Order/Details/${item.id}">Details</a> <a class="" href="/Admin/Details/Confirmed/${item.id}">Confirmed</a></th>
                            </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                </div>
                <div class="card-footer ">
                    <hr>
                    <div class="stats">
<%--                        <i class="fa fa-history"></i> Updated 3 minutes ago--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="card ">
<%--                <div class="card-header ">--%>
<%--                    <h5 class="card-title">Users Behavior</h5>--%>
<%--                    <p class="card-category">24 Hours performance</p>--%>
<%--                </div>--%>


            </div>
        </div>
    </div>

</div>
