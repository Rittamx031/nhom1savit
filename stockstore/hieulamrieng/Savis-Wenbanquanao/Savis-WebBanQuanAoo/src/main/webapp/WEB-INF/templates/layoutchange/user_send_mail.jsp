
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<main class="container" style="margin-top: 100px;">
    <section class="row">
        <div class="col-6 offset-3 mt-4 ">
            <form action="/user/passwordMail" method="POST">
                <div class="card">
                    <div class="card-header">

                            <h1>Quên mật khẩu</h1>
                    </div>
                    <div class="card-body">
                        <div>Please enter your email</div>
                        <div class="form-group mt-3">
                            <label for="email">Email</label> <input type="text"
                                                                    class="form-control" name="email" id="email" required="required"
                                                                    aria-describedby="email" placeholder="email"> <small
                                id="emailHid" class="form-text text-muted">&ensp;</small>
                        </div>
                        <div class="card-footer text-muted">
                            <button class="btn btn-primary">Gửi email</button>
                        </div>
                    </div>
                </div>
            </form>
            <div class=" mt-2 mb-5">
                <c:if test="${!empty message}">
                    <div class="alert alert-danger" role="alert">
                        <b>${message}</b>
                    </div>
                </c:if>
            </div>
        </div>
    </section>
    <footer class="row"></footer>
</main>