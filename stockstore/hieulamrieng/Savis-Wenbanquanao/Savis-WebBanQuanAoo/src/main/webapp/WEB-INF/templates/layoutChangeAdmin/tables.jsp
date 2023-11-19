<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<link href="/cssadmin/styles.css" rel="stylesheet" />

<div class="content">

    <div class="row">

        <div class="col-md-12">
            <div class="card">
                <ul class="nav nav-pills mb-1 mt-2 ml-2" id="pills-tab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true">Home</a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="false">Profile</a>
                    </li>

                </ul>
                <div class="tab-content" id="pills-tabContent">
                    <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab"><div class="card-header">
                        <h4 class="card-title">PRODUCTS LIST</h4>
                        <form action="/product/search-and-page" method="post">
                            <input class="col-3 form-control" name="keywords" value="${keywords}"/>
                            <button class="btn btn-primary">Search</button>
                        </form>

                    </div>
                        <div class="card-body">

                            <div class="table-responsive">
                                <table class="table table-bordered">

                                    <thead class=" text-primary">
                                    <th>
                                        ID
                                    </th>
                                    <th>
                                        Name
                                    </th>
                                    <th>
                                        Image
                                    </th>
                                    <th>
                                        Ngay Tao
                                    </th>
                                    <th>
                                        so luong
                                    </th>
                                    <th>
                                        giam gia
                                    </th>
                                    <th>
                                        CategoryId
                                    </th>
                                    <th >
                                        gia
                                    </th>
                                    <th class="text-right">
                                        Edit
                                    </th>
                                    </thead>
                                    <c:forEach var="item"  items="${page.content}" >
                                        <tbody>
                                        <tr>
                                            <td>
                                                    ${item.id}
                                            </td>
                                            <td>
                                                    ${item.name}
                                            </td>
                                            <td>
                                                <img  style="width: 70px;height: 40px" src="/static/upload/${item.image}"/>
                                            </td>
                                            <td>
                                                    ${item.createDate}
                                            </td>
                                            <td>
                                                    ${item.quantity}
                                            </td>
                                            <td>
                                                    ${item.discount}
                                            </td>
                                            <td>
                                                    ${item.category.id}
                                            </td>

                                            <td >
                                                    ${item.price}
                                            </td>
                                            <td class="text-right">
                                                <i class="fas fa-trash-alt">  <a href="/Admin/Tables/Edit/${item.id}">Edit</a></i>
                                                <i class="fas fa-edit">  <a href="/Admin/Tables/Remove/${item.id}">Delete</a></i>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </c:forEach>
                                    <h6>${param.message}</h6>
                                </table>

                                <nav aria-label="Page navigation example">
                                    <ul class="pagination justify-content-center">
                                        <li ng-click="first()" class="page-item text-primary"><a
                                                href="/user/search?p=0" class="page-link" style="font-size: 14px;"><s:message
                                                code="lo.mn.first" /></a></li>
                                        <c:choose>
                                            <c:when test="${page.number>0}">
                                                <li ng-click="prev()" class="page-item text-primary"><a
                                                        href="/Admin/Table?p=0${page.number-1}" class="page-link"
                                                        style="font-size: 14px;"><s:message code="lo.mn.prev" /></a></li>
                                            </c:when>
                                            <c:when test="${page.number>-1}">
                                                <li ng-click="prev()" class="page-item text-primary"><a
                                                        onclick="return false;" href="/Admin/Table?p=0${page.number-1}"
                                                        class="page-link" style="font-size: 14px;"><s:message
                                                        code="lo.mn.prev" /></a></li>
                                            </c:when>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${page.number<page.totalPages-1}">
                                                <li ng-click="next()" class="page-item text-primary"><a
                                                        href="/Admin/Table?p=0${page.number+1}" class="page-link"
                                                        style="font-size: 14px;"><s:message code="lo.mn.next" /></a></li>
                                            </c:when>
                                            <c:when test="${page.number<page.totalPages}">
                                                <li ng-click="next()" class="page-item text-primary"><a
                                                        onclick="return false;" href="/Admin/Table?p=0${page.number+1}"
                                                        class="page-link" style="font-size: 14px;"><s:message
                                                        code="lo.mn.next" /></a></li>
                                            </c:when>
                                        </c:choose>
                                        <li ng-click="last()" class="page-item text-primary"><a
                                                href="/Admin/Table?p=0${page.totalPages-1}" class="page-link"
                                                style="font-size: 14px;"><s:message code="lo.mn.last" /></a></li>
                                    </ul>
                                </nav>

                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab"><div class="content">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="card card-user">
                                    <div class="image">

                                    </div>
                                    <div class="card-body">
                                        <div class="author">
<%--                                            <a href="#">--%>
<%--                                                <img class="avatar border-gray" src="/static/upload/${item.image}" alt="...">--%>

<%--                                            </a>--%>
                                            <p class="description">
${item.name}
                                            </p>
                                        </div>

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
                                        <h5 class="card-title">Edit Product</h5>
                                    </div>
                                    <div class="card-body">
                                        <form:form action="/Admin/Table/Save" modelAttribute="item" method="post" enctype="multipart/form-data" >
                                            <div class="form-group">
                                                <label>Id</label>
                                                <form:input type="text" class="form-control" path="id" placeholder="ID" />

                                            </div>
                                            <div class="form-group">
                                                <label>Ten</label>
                                                <form:input type="name" class="form-control" path="name"
                                                            placeholder="Name" />
                                                <form:errors path="name" />

                                            </div>
                                            <div class="form-group">
                                                <label>So Luong</label>
                                                <form:input type="text" class="form-control" path="quantity"
                                                            placeholder="Quantity" />
                                                <form:errors path="quantity" />
                                            </div>
                                            <div class="form-group">
                                                <label>Gia</label>
                                                <form:input type="text" class="form-control" path="price"
                                                            placeholder="Price" />
                                                <form:errors path="price" />
                                            </div>
                                            <div class="form-group">
                                                <label>Khac</label>
                                                <form:input type="text" class="form-control" path="about"
                                                            placeholder="About" />
                                                <form:errors path="about" />
                                            </div>

                                            <div class="form-group">
                                                <label>Giam gia</label>
                                                <form:input type="text" class="form-control" path="discount"
                                                            placeholder="Discount" />
                                                <form:errors path="discount" />
                                            </div>
                                            <div class="form-group">
                                                <label>Ngay tao</label>
                                                <form:input type="text" class="form-control" path="createDate"
                                                            placeholder="Enter email" />
                                                <form:errors path="createDate" />
                                            </div>

                                            <label>Photo</label>
                                            <input type="file" class="form-control" name="photo"
                                                   accept="image/png,image/jpeg" />

                                            <div class="form-group">
                                                <label>So luong</label>
                                                <form:input type="text" class="form-control"
                                                            path="available"/>
                                                <form:errors path="available" />
                                            </div>
                                            <div class="form-group">
                                                <label>Category ID</label>
                                                <form:input type="text" class="form-control"
                                                            path="category.id"/>
                                                <form:errors path="category.id" />
                                            </div>
                                            <h6>${message}</h6>
                                            <%--        <div class="form-group">--%>
                                            <%--            <label>Status</label>--%>
                                            <%--            <div class="radio">--%>
                                            <%--                <!--value = 1 => Nam| 0 =>Nu -->--%>
                                            <%--                <label class="checkbox-inline"> <form:checkbox--%>
                                            <%--                        path="activated" />Activated--%>
                                            <%--                </label> <label class="checkbox-inline"> <form:checkbox--%>
                                            <%--                    path="admin" />Admin--%>
                                            <%--            </label>--%>
                                            <%--            </div>--%>
                                            <%--        </div>--%>
                                            <form:button type="submit" class="btn btn-primary">Submit</form:button>
                                            <form:button type="reset" class="btn btn-primary">Cancel</form:button></form:form>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


