<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
    <title>Zay Shop eCommerce HTML CSS Template</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="/client/assets/img/apple-icon.png">
    <link rel="shortcut icon" type="image/x-icon" href="/client/assets/img/favicon.ico">

    <link rel="stylesheet" href="/client/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/client/assets/css/templatemo.css">
    <link rel="stylesheet" href="/client/assets/css/custom.css">

    <!-- Load fonts style after rendering the layout styles -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
    <link rel="stylesheet" href="/client/assets/css/fontawesome.min.css">
</head>
<body>

<jsp:include page="../layout/header.jsp"/>

<div class="container py-5">
    <form:form action="/cart/checkout" method="post" modelAttribute="orderDetailInfo"  class="row d-flex justify-content-center my-4">
        <form:input type="hidden" path="productId"></form:input>
        <form:input type="hidden" path="amount"></form:input>
        <form:input type="hidden" path="price"></form:input>
        <div class="col-md-8">
            <div class="card mb-4">
                <div class="row" style="align-items: center; padding: 10px;">
                    <div class="col-lg-3 col-md-12 mb-4 mb-lg-0">
                        <!-- Image -->
                        <div class="bg-image hover-overlay hover-zoom ripple rounded"
                             data-mdb-ripple-color="light">
                            <img src="${pageContext.request.contextPath}/uploads/${orderDetailInfo.productId}"
                                 class="w-100" alt="Blue Jeans Jacket"/>
                            <a href="#!">
                                <div class="mask" style="background-color: rgba(251, 251, 251, 0.2)"></div>
                            </a>
                        </div>
                        <!-- Image -->
                    </div>

                    <div class="col-lg-5 col-md-6 mb-4 mb-lg-0">
                        <!-- Data -->
                        <p><strong>${orderDetailInfo.nameProduct}</strong></p>
                        <!-- Data -->
                    </div>

                    <div class="col-lg-4 col-md-6 mb-4 mb-lg-0">
                        <!-- Quantity -->
                        <div class="d-flex mb-4" style="max-width: 300px">
                            <a data-mdb-button-init data-mdb-ripple-init style="height: 41px;"
                                    class="btn btn-primary px-3 me-2"
                                    onclick="this.parentNode.querySelector('input[type=number]').stepDown()"
                                    disabled="disabled">
                                <i class="fas fa-minus"></i>
                            </a>

                            <div data-mdb-input-init class="form-outline">
                                <form:input id="form1" min="0" name="quantity" value="1" type="number"
                                       class="form-control" path="quantity"/>
                                <label class="form-label" for="form1">Quantity</label>
                            </div>

                            <a data-mdb-button-init data-mdb-ripple-init style="height: 41px;"
                                    class="btn btn-primary px-3 ms-2"
                                    onclick="this.parentNode.querySelector('input[type=number]').stepUp()"
                                    disabled="disabled">
                                <i class="fas fa-plus"></i>
                            </a>
                        </div>
                        <!-- Quantity -->

                        <!-- Price -->
                        <p class="text-start text-md-center">
                            <strong>
                                <fmt:formatNumber value="${orderDetailInfo.price}" type="currency" currencySymbol="VND"/>
                            </strong>
                        </p>
                        <!-- Price -->
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card mb-4">
                <div class="card-header py-3">
                    <h5 class="mb-0">Customer Information</h5>
                </div>
                <div class="card-body">
                    <ul class="list-group list-group-flush">
                        <li
                                class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
                            Name
                            <span>
                                <form:input class="form-control" type="text" path="customerName"/>
                            </span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                            Email
                            <span>
                                <form:input class="form-control" type="text" path="customerEmail"/>
                            </span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                            Phone
                            <span>
                                <form:input class="form-control" type="text" path="customerPhone"/>
                            </span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                            Address
                            <span>
                                <form:input class="form-control" type="text" path="customerAddress"/>
                            </span>
                        </li>
                    </ul>

                    <button type="submit" data-mdb-button-init data-mdb-ripple-init
                            class="btn btn-primary btn-lg">
                        Go to checkout
                    </button>
                </div>
            </div>
        </div>
    </form:form>
</div>

<jsp:include page="../layout/footer.jsp"/>

</body>
<script src="/client/assets/js/jquery-1.11.0.min.js"></script>
<script src="/client/assets/js/jquery-migrate-1.2.1.min.js"></script>
<script src="/client/assets/js/bootstrap.bundle.min.js"></script>
<script src="/client/assets/js/templatemo.js"></script>
<script src="/client/assets/js/custom.js"></script>

</html>