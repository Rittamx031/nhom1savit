<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="content">
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title">CATEGORY LIST</h4>

                </div>
                <div class="card-body">

                    <div class="table-responsive">
                        <table class="table">

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
                                </tr>
                                </tbody>
                            </c:forEach>
                        </table>
                        <a href="/product/search-and-page?p=0">First</a>
                        <a href="/product/search-and-page?p=${page.number-1}">Previous</a>
                        <a href="/product/search-and-page?p=${page.number+1}">1</a>
                        <a href="/product/search-and-page?p=${page.totalPages-1}">Last</a>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<%--<div class="col">--%>
<%--    <div class="card card-user">--%>
<%--        <div class="card-header">--%>
<%--            <h5 class="card-title">Edit Category</h5>--%>
<%--        </div>--%>
<%--        <div class="card-body">--%>
<%--            <form:form action="/Admin/Category" modelAttribute="item">--%>
<%--            <div class="row">--%>
<%--                <div class="col-md-5 pr-1">--%>
<%--                    <div class="form-group">--%>
<%--                        <label>ID Category</label>--%>
<%--                        <form:input type="text" class="form-control" placeholder="Id" value="" path="id"/>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col-md-6 pr-1">--%>
<%--                    <div class="form-group">--%>
<%--                        <label>Name Category</label>--%>
<%--                        <form:input type="text" class="form-control" placeholder="Username" value="" path="name"/>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col-md-6 pr-1">--%>
<%--                    <div class="form-group">--%>

<%--                        <form:label path = "status">Status</form:label>--%>

<%--                        <form:radiobutton path = "status" value = "True" label = "Còn hàng" />--%>
<%--                        <form:radiobutton path = "status" value = "False" label = "Đã hết" />--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="create ml-auto mr-auto">--%>
<%--                        <button type="submit" class="btn btn-primary btn-round" formaction="/Admin/Category/Add">Save</button>--%>
<%--                        <button type="submit" class="btn btn-primary btn-round" formaction="/Admin/Category/Update">Update </button>--%>
<%--                    </div>--%>
<%--                    <div class="update ml-auto mr-auto">--%>

<%--                    </div>--%>
<%--                </div>--%>

<%--                </form:form>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
