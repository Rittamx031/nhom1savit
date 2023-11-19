<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <div class="card-header">

                    <h4 class="card-title">ACCOUNTS LIST</h4>
                    <form action="/Admin/account/search-and-page" method="post">
                        <input class="col-3 form-control" name="keywords" value="${keywords}"/>
                        <button class="btn btn-primary">Search</button>
                    </form>
                </div>
                <div class="tab-content" id="pills-tabContent">
                    <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab"> <div class="card-body">

                        <div class="table-responsive">
                            <table class="table table-bordered">

                                <thead class=" text-primary">
                                <th>
                                    ID
                                </th>
                                <th>
                                    Username
                                </th>
                                <th>
                                    Password
                                </th>
                                <th>
                                    Fullname
                                </th>
                                <th>
                                    Email
                                </th>
                                <th>
                                    Address
                                </th>
                                <th>
                                    Images
                                </th>
                                <th>
                                    Phone
                                </th>
                                <th>
                                    Price
                                </th>
                                <th class="text-right">
                                    Edit
                                </th>
                                </thead>
                                <c:forEach var="item"  items="${page.content}" >
                                    <tbody>
                                    <td>
                                        <td>
                                                ${item.id}
                                        </td>
                                        <td>
                                                ${item.username}
                                        </td>

                                        <td>
                                                ${item.password}
                                        </td>
                                        <td>
                                                ${item.fullname}
                                        </td>
                                        <td>
                                                ${item.email}
                                        </td>
                                        <td>
                                                ${item.address}
                                        </td>
                                        <td>
                                                ${item.photo}
                                        </td>
                                        <td>
                                                ${item.phone}
                                        </td>
                                        <td >
                                                ${item.admin}
                                        </td>
                                        <td class="text-right">
                                            <a href="/Admin/Account/Edit/${item.id}">Edit</a>

                                        </td>
                                    <td>  <a href="/Admin/Account/Remove/${item.id}">Delete</a></td>
                                    </tr>
                                    </tbody>
                                </c:forEach>
                                <h6>${param.message}</h6>
                            </table>
                            <center>
                            <a href="/Admin/Account?p=0">First</a>
                            <a href="/Admin/Account?p=${page.number-1}">Previous</a>
                            <a href="/Admin/Account?p=${page.number+1}">Next</a>
                            <a href="/Admin/Account?p=${page.totalPages-1}">Last</a>
                            </center>

                        </div>
                    </div></div>
                    <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab"><div class="col">
                        <div class="card card-user">
                            <div class="card-header">
                                <h5 class="card-title">Edit ACCOUNT</h5>
                            </div>
                            <div class="card-body">
                                <form:form action="/Admin/Account/Save" modelAttribute="item"  method="post" enctype="multipart/form-data">
                                <div class="row">
                                    <div class="col-4">
                                        <div class="form-group">
                                            <label>Id</label>
                                            <form:input type="hidden"  class="form-control" placeholder="id" value="" path="id"/>
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div class="form-group">
                                            <label>username</label>
                                            <form:input type="text" class="form-control" name="username"  placeholder="Username"  path="username"/>
                                            <form:errors path="username"/>
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div class="form-group">
                                            <label> password</label>
                                            <form:input type="password" class="form-control" placeholder="password" value="" path="password"/>
                                            <form:errors path="password"/>
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div class="form-group">
                                            <label>fullname</label>
                                            <form:input type="text" class="form-control" placeholder="fullname" value="" path="fullname"/>

                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div class="form-group">
                                            <label>email</label>
                                            <form:input type="text" class="form-control" placeholder="email" value="" path="email"/>
                                            <form:errors path="email"/>
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div class="form-group">
                                            <label>address</label>
                                            <form:input type="text" class="form-control" placeholder="address" value="" path="address"/>

                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div class="form-group">
                                            <label>Number Phone</label>
                                            <form:input type="text" class="form-control" placeholder="phone" value="" path="phone"/>
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div class="form-group">
                                            <label>photo</label>
                                            <input type="file" class="form-control" name="image"
                                                   accept="image/png,image/jpeg" />
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div class="form-group">
                                            <form:label path = "activated">ROLE</form:label>
                                            <form:radiobutton path = "activated" value = "True" label = "True" />
                                            <form:radiobutton path = "activated" value = "False" label = "False" />
                                            <form:errors path="activated"/>

                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="create ml-auto mr-auto">
                                            <form:label path = "admin">ROLE</form:label>
                                                                <form:radiobutton path = "admin" value = "True" label = "Admin" />
                                                                <form:radiobutton path = "admin" value = "False" label = "User" />
                                                                <form:errors path="admin"/>
                                            <form:button type="submit" class="btn btn-primary btn-round ml-4" >Save</form:button>
                                        </div>
                                        <div class="update ml-auto mr-auto">

                                        </div>
                                    </div>
                                    </form:form>
                                    <h6>${param.mess}</h6>
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
