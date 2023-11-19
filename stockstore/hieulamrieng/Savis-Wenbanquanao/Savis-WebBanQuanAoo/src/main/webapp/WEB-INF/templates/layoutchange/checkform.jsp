<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

<title>Checkout example for Bootstrap</title>

<link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/checkout/">

<!-- Bootstrap core CSS -->
<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="form-validation.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="../../assets/js/vendor/popper.min.js"></script>
<script src="../../dist/js/bootstrap.min.js"></script>
<script src="../../assets/js/vendor/holder.min.js"></script>
<div class="container">
    <div class="py-5 text-center">
    </div>

    <div class="row">
        <div class="col-md-4 order-md-2 mb-4">

            <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-muted">Your cart</span>
                <span class="badge badge-secondary badge-pill">3</span>
            </h4>

            <ul class="list-group mb-3">
                <c:forEach var="item" items="${items}">
                <li class="list-group-item d-flex justify-content-between lh-condensed">
                    <div>
                        <h6 class="my-0">${item.name}</h6>
                        <small class="text-muted">SL : ${item.quantity}</small>
                    </div>
                    <span class="text-muted">${item.price}</span>
                </li>

                <li class="list-group-item d-flex justify-content-between bg-light">
                    <div class="text-success">
                        <h6 class="my-0">Promo code</h6>
                        <small>EXAMPLECODE</small>
                    </div>
                    <span class="text-success">-${item.discount}%</span>
                </li>
                </c:forEach>
                <li class="list-group-item d-flex justify-content-between">
                    <span>Total (USD)</span>
                    <strong>$ ${total}</strong>
                </li>
            </ul>

            <form class="card p-2">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Promo code">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary">Redeem</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="col-md-8 order-md-1">
            <h4 class="mb-3">Billing address</h4>
            <form:form  action="/shoppingcart/save/order"  modelAttribute="item">
                <div class="row">
                    <div class="col-md-12 mb-6">
                        <label >Fullname</label>
                        <input type="text" class="form-control" id="fullname" placeholder="" value="${user.fullname}" />
                        <div class="invalid-feedback">
                            Valid first name is required.
                        </div>
                    </div>
                </div>
                <div class="mb-3">
                    <label >AccountID</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">@</span>
                        </div>
                        <form:input type="text" class="form-control"  placeholder="" value="${user.id}" path="account.id" />
                        <div class="invalid-feedback" style="width: 100%;">
                            Your username is required.
                        </div>
                    </div>
                </div>

                <div class="mb-3">
                    <label >Address <span class="text-muted">(Optional)</span></label>
                    <form:input type="text" class="form-control"   value="${user.address}" path="address"/>
                    <div class="invalid-feedback">
                        Please enter a valid email address for shipping updates.
                    </div>
                </div>
                <div class="mb-3">
                    <label >Username <span class="text-muted">(Optional)</span></label>
                    <form:input type="text" class="form-control"   value="${user.username}" path="username"/>
                    <div class="invalid-feedback">
                        Please enter a valid email address for shipping updates.
                    </div>
                </div>
                <div class="mb-3">
                    <label >Date</label>
                    <form:input type="text"  placeholder="Date" class="form-control" path="createDate" />
                    <div class="invalid-feedback">
                        Please enter your shipping address.
                    </div>
                </div>
                <div class="mb-3">
                    <label >Confirm</label>
                    <form:checkbox   placeholder="Date" class="form-control" path="available" />
                    <div class="invalid-feedback">
                        Please enter your shipping address.
                    </div>
                    <form:input  type="hidden"  placeholder="Date" class="form-control" path="confirm" value="false" />
                    <form:input  type="hidden"  placeholder="Date" class="form-control" path="price" value="${total}" />
                </div>
                <div class="row">
                    <div class="col-md-5 mb-3">

                    </div>
                    <div class="col-md-4 mb-3">

                    </div>
                    <div class="col-md-3 mb-3">

                    </div>
                </div>



                <h4>${message}</h4>
                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>
            </form:form>
        </div>
    </div>
</div>