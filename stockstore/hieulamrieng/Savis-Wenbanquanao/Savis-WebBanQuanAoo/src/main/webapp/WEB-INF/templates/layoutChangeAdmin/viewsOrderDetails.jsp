
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

            </div>
        </div>

        </div>


    </div>


    <div class="row">
        <div class="col-md-12">
            <div class="card ">

                <div class="card-body ">
                    <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Order Id</th>
                        <th scope="col">Name Product</th>
                        <th scope="col">Image</th>
                        <th scope="col">Price</th>
                        <th scope="col">Quantity</th>
                    </tr>
                    </thead>
                    <c:forEach var="item" items="${orderDetails}">
                        <tbody>

                            <td >${item.id}</td>
                            <td>${item.order.id}</td>
                            <td>${item.product.name}</td>
                            <td><img src="/static/upload/${item.product.image}"/></td>
                            <td>${item.price}</td>
                            <td>${item.quantity}</td>
                        </tr>

                        </tbody>
                    </c:forEach>
                    <h4>${message}</h4>
                    </table>
                </div>
                <div class="card-footer ">
                    <hr>
                    <div class="stats">
                        <i class="fa fa-history"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
