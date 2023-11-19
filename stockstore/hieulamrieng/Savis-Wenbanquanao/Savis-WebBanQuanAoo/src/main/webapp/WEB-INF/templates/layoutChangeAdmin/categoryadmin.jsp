<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="content">
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <ul class="nav nav-pills mb-1 mt-2 ml-2" id="pills-tab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true">CATEGORY_LIST</a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="false">EDIT</a>
                    </li>

                </ul>
                <div class="card-header">
                    <h4 class="card-title">CATEGORY LIST</h4>
                    <form action="/Admin/category/search-and-page" method="post">
                        <input class="col-3 form-control" name="keywords" value="${keywords}"/>
                        <button class="btn btn-primary">Search</button>
                    </form>
                </div>
                <div class="tab-content" id="pills-tabContent">
                    <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab"><div class="card-body">

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
                                    Status
                                </th>

                                </thead>
                                <c:forEach var="item" items="${page.content}">
                                    <tbody>
                                    <tr>
                                        <td>
                                                ${item.id}
                                        </td>
                                        <td>
                                                ${item.name}
                                        </td>
                                        <td>
                                                ${item.status}
                                        </td>
                                        <td><a href="/Admin/Category/Edit/${item.id}">Edit</a></td>
                                      <td> <a href="/Admin/Category/Remove/${item.id}">Delete</a></td>
                                    </tr>
                                    </tbody>
                                </c:forEach>
                                <h6>${param.message}</h6>
                            </table>
<%--                            <a href="/Admin/Category?p=0">First</a>--%>
<%--                            <a href="/Admin/Category?p=${page.number-1}">Previous</a>--%>
<%--                            <a href="/Admin/Category?p=${page.number+1}">1</a>--%>
<%--                            <a href="/Admin/Category?p=${page.totalPages-1}">Last</a>--%>
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <li ng-click="first()" class="page-item text-primary"><a
                                            href="/Admin/Category?p=0" class="page-link" style="font-size: 14px;"><s:message
                                            code="lo.mn.first" /></a></li>
                                    <c:choose>
                                        <c:when test="${page.number>0}">
                                            <li ng-click="prev()" class="page-item text-primary"><a
                                                    href="/Admin/Table?p=0${page.number-1}" class="page-link"
                                                    style="font-size: 14px;"><s:message code="lo.mn.prev" /></a></li>
                                        </c:when>
                                        <c:when test="${page.number>-1}">
                                            <li ng-click="prev()" class="page-item text-primary"><a
                                                    onclick="return false;" href="/Admin/Category?p=${page.number-1}"
                                                    class="page-link" style="font-size: 14px;"><s:message
                                                    code="lo.mn.prev" /></a></li>
                                        </c:when>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${page.number<page.totalPages-1}">
                                            <li ng-click="next()" class="page-item text-primary"><a
                                                    href="/Admin/Category?p=${page.number+1}" class="page-link"
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
                                            href="/Admin/Category?p=${page.totalPages-1}" class="page-link"
                                            style="font-size: 14px;"><s:message code="lo.mn.last" /></a></li>
                                </ul>
                            </nav>
                            <%--                        <ul>--%>
                            <%--                            <li>Số thực thể hiện tại: ${page.numberOfElements}</li>--%>
                            <%--                            <li>Trang số: ${page.number}</li>--%>
                            <%--                            <li>Kích thước trang: ${page.size}</li>--%>
                            <%--                            <li>Tổng số thực thể: ${page.totalElements}</li>--%>
                            <%--                            <li>Tổng số trang: ${page.totalPages}</li>--%>
                            <%--                        </ul>--%>

                        </div>

                    </div></div>
                    <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab"><div class="col">
                        <div class="card card-user">
                            <div class="card-header">
                                <h5 class="card-title">Edit Category</h5>
                            </div>
                            <div class="card-body">
                                <form:form action="/Admin/Category/Add" modelAttribute="item" >
                                <div class="row">
                                    <div class="col-md-6 pr-1">
                                        <div class="form-group">
                                            <label>Name Category</label>
                                            <form:input type="text" class="form-control" placeholder="Username" value="" path="name"/>
                                            <form:errors path="name"/>
                                        </div>
                                    </div>
                                    <div class="col-md-5 pr-1">
                                        <div class="form-group">

                                            <form:input type="hidden" class="form-control" placeholder="Id" value="" path="id"/>

                                        </div>
                                    </div>

                                    <div class="col-md-6 pr-1">
                                        <div class="form-group">

                                            <form:label path = "status">Status</form:label>

                                            <form:radiobutton path = "status" value = "True" label = "Còn hàng" />
                                            <form:radiobutton path = "status" value = "False" label = "Đã hết" />
                                            <form:errors path="status"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="create ml-auto mr-auto">
                                            <button type="submit" class="btn btn-primary btn-round" formaction="/Admin/Category/Add">Save</button>
                                            <button type="submit" class="btn btn-primary btn-round" formaction="/Admin/Category/Update">Update </button>
                                        </div>
                                        <div class="update ml-auto mr-auto">

                                        </div>
                                    </div>
                                        <h6>${message}</h6>
                                    </form:form>
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
