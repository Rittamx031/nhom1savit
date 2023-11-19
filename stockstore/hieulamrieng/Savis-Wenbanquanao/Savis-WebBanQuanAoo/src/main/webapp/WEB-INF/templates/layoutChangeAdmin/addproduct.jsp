<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form action="/Admin/Table/SaveSomething" modelAttribute="pd" method="post" enctype="multipart/form-data" >
    <div class="form-group">
        <label>UserName</label>
        <form:input type="text" class="form-control" path="id"
                    placeholder="Enter UserName" />
    </div>
    <div class="form-group">
        <label>Password:</label>
        <form:input type="password" class="form-control" path="name"
                    placeholder="Enter password" />

    </div>
    <label>Photo</label>
    <input type="file" class="form-control" name="photo"
           accept="image/png,image/jpeg" />

    <div class="form-group">
        <label>FullName</label>
        <form:input type="text" class="form-control" path="quantity"
                    placeholder="Enter fullname" />
    </div>
    <div class="form-group">
        <label>Email</label>
        <form:input type="text" class="form-control" path="price"
                    placeholder="Enter email" />
    </div>
    <div class="form-group">
        <label>Email</label>
        <form:input type="text" class="form-control" path="about"
                    placeholder="Enter email" />
    </div>

    <div class="form-group">
        <label>Email</label>
        <form:input type="text" class="form-control" path="discount"
                    placeholder="Enter email" />
    </div>
    <div class="form-group">
        <label>Email</label>
        <form:input type="text" class="form-control" path="createDate"
                    placeholder="Date" />
    </div>


    <div class="form-group">
        <label>Photo</label>
        <form:input type="text" class="form-control"
                    accept="image/png,image/jpeg" path="available"/>
    </div>
    <div class="form-group">
        <label>Photo</label>
        <form:input type="text" class="form-control"
                    accept="image/png,image/jpeg" path="category.id"/>
    </div>

    <form:button type="submit" class="btn btn-primary">Submit</form:button>
    <form:button type="reset" class="btn btn-primary">Cancel</form:button>
    <a href="/Admin/Table/SaveSomething" class="btn btn-primary">Display Account</a>
</form:form>