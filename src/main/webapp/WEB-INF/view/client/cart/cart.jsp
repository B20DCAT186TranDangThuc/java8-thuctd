<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
    <title>Shopping</title>
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

<style>
    .icon-hover-primary:hover {
        border-color: #3b71ca !important;
        background-color: white !important;
    }

    .icon-hover-primary:hover i {
        color: #3b71ca !important;
    }
    .icon-hover-danger:hover {
        border-color: #dc4c64 !important;
        background-color: white !important;
    }

    .icon-hover-danger:hover i {
        color: #dc4c64 !important;
    }
</style>
<body>

<jsp:include page="../layout/header.jsp"/>

<div class="container py-5">
    <div class="row">
        <!-- cart -->
        <div class="col-lg-9">
            <div class="card border shadow-0">
                <div class="m-4">
                    <h4 class="card-title mb-4">Your shopping cart</h4>
                    <div class="row gy-3 mb-4">
                        <div class="col-lg-5">
                            <div class="me-lg-5">
                                <div class="d-flex">
                                    <img src="https://mdbootstrap.com/img/bootstrap-ecommerce/items/11.webp"
                                         class="border rounded me-3" style="width: 96px; height: 96px;"/>
                                    <div class="">
                                        <a href="#" class="nav-link">Winter jacket for men and lady</a>
                                        <p class="text-muted">Yellow, Jeans</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-2 col-sm-6 col-6 d-flex flex-row flex-lg-column flex-xl-row text-nowrap">
                            <div class="">
                                <input type="number" class="form-control me-4" style="width: 100px;" min="1" max="10" value="1">
                            </div>
                            <div class="">
                                <text class="h6">$1156.00</text>
                                <br/>
                                <small class="text-muted text-nowrap"> $460.00 / per item </small>
                            </div>
                        </div>
                        <div class="col-lg col-sm-6 d-flex justify-content-sm-center justify-content-md-start justify-content-lg-center justify-content-xl-end mb-2">
                            <div class="float-md-end">
                                
                                <a href="#" class="btn btn-light border text-danger icon-hover-danger"> Remove</a>
                            </div>
                        </div>
                    </div>

                    <div class="row gy-3 mb-4">
                        <div class="col-lg-5">
                            <div class="me-lg-5">
                                <div class="d-flex">
                                    <img src="https://mdbootstrap.com/img/bootstrap-ecommerce/items/12.webp"
                                         class="border rounded me-3" style="width: 96px; height: 96px;"/>
                                    <div class="">
                                        <a href="#" class="nav-link">Mens T-shirt Cotton Base</a>
                                        <p class="text-muted">Blue, Medium</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-2 col-sm-6 col-6 d-flex flex-row flex-lg-column flex-xl-row text-nowrap">
                            <div class="">
                                <input type="number" class="form-control me-4" style="width: 100px;" min="1" max="10" value="1">
                            </div>
                            <div class="">
                                <text class="h6">$44.80</text>
                                <br/>
                                <small class="text-muted text-nowrap"> $12.20 / per item </small>
                            </div>
                        </div>
                        <div class="col-lg col-sm-6 d-flex justify-content-sm-center justify-content-md-start justify-content-lg-center justify-content-xl-end mb-2">
                            <div class="float-md-end">
                                
                                <a href="#" class="btn btn-light border text-danger icon-hover-danger"> Remove</a>
                            </div>
                        </div>
                    </div>

                    <div class="row gy-3">
                        <div class="col-lg-5">
                            <div class="me-lg-5">
                                <div class="d-flex">
                                    <img src="https://mdbootstrap.com/img/bootstrap-ecommerce/items/13.webp"
                                         class="border rounded me-3" style="width: 96px; height: 96px;"/>
                                    <div class="">
                                        <a href="#" class="nav-link">Blazer Suit Dress Jacket for Men</a>
                                        <p class="text-muted">XL size, Jeans, Blue</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-2 col-sm-6 col-6 d-flex flex-row flex-lg-column flex-xl-row text-nowrap">
                            <div class="">
                                <input type="number" class="form-control me-4" style="width: 100px;" min="1" max="10" value="1">
                            </div>
                            <div class="">
                                <text class="h6">$1156.00</text>
                                <br/>
                                <small class="text-muted text-nowrap"> $460.00 / per item </small>
                            </div>
                        </div>
                        <div class="col-lg col-sm-6 d-flex justify-content-sm-center justify-content-md-start justify-content-lg-center justify-content-xl-end mb-2">
                            <div class="float-md-end">
                                
                                <a href="#" class="btn btn-light border text-danger icon-hover-danger"> Remove</a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- cart -->
        <!-- summary -->
        <div class="col-lg-3">
            <div class="card shadow-0 border">
                <div class="card-body">
                    <div class="d-flex justify-content-between">
                        <p class="mb-2">Total price:</p>
                        <p class="mb-2">$329.00</p>
                    </div>
                    <div class="d-flex justify-content-between">
                        <p class="mb-2">Discount:</p>
                        <p class="mb-2 text-success">-$60.00</p>
                    </div>
                    <div class="d-flex justify-content-between">
                        <p class="mb-2">TAX:</p>
                        <p class="mb-2">$14.00</p>
                    </div>
                    <hr/>
                    <div class="d-flex justify-content-between">
                        <p class="mb-2">Total price:</p>
                        <p class="mb-2 fw-bold">$283.00</p>
                    </div>

                    <div class="mt-3">
                        <a href="#" class="btn btn-success w-100 shadow-0 mb-2"> Make Purchase </a>
                        <a href="#" class="btn btn-light w-100 border mt-2"> Back to shop </a>
                    </div>
                </div>
            </div>
        </div>
        <!-- summary -->
    </div>
</div>

<jsp:include page="../layout/footer.jsp"/>

</body>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const form = document.querySelector("form");
        const amountDisplay = document.querySelector('strong[id="amount-display-0"]');
        const amount = amountDisplay.getAttribute('data-set');

        // Cập nhật hiển thị amount nếu cần
        if (amountDisplay) {
            amountDisplay.textContent = new Intl.NumberFormat('vi-VN', {
                style: 'currency',
                currency: 'VND'
            }).format(amount);
        }
    })

    function decrementQuantity(element) {
        const quantityInput = element.parentNode.querySelector('input[type=number]');
        if (quantityInput) {
            quantityInput.stepDown();
            updateAmount(quantityInput); // Cập nhật amount nếu cần
        }
    }

    function incrementQuantity(element) {
        const quantityInput = element.parentNode.querySelector('input[type=number]');
        if (quantityInput) {
            quantityInput.stepUp();
            updateAmount(quantityInput); // Cập nhật amount nếu cần
        }
    }

    function updateAmount(quantityInput) {
        const quantity = parseInt(quantityInput.value, 10);
        const card = quantityInput.closest('.card'); // Tìm phần tử card chứa quantityInput

        // Lấy giá trị price từ thẻ input hidden
        const priceInput = card.querySelector('input[type=hidden][id="orderDetailInfos0.price"]');
        const amountInput = card.querySelector('input[type=hidden][id="orderDetailInfos0.amount"]'); // Lấy giá trị amount

        const amountDisplay = card.querySelector('strong[id="amount-display-0"]')

        if (priceInput && amountInput) {
            const price = parseFloat(priceInput.value); // Chuyển đổi giá trị price thành số
            const amount = price * quantity; // Tính toán giá trị amount
            amountInput.value = amount; // Cập nhật giá trị amount

            // Cập nhật hiển thị amount nếu cần
            if (amountDisplay) {
                amountDisplay.textContent = new Intl.NumberFormat('vi-VN', {
                    style: 'currency',
                    currency: 'VND'
                }).format(amount);
            }
        }
    }
</script>
<script src="/client/assets/js/jquery-1.11.0.min.js"></script>
<script src="/client/assets/js/jquery-migrate-1.2.1.min.js"></script>
<script src="/client/assets/js/bootstrap.bundle.min.js"></script>
<script src="/client/assets/js/templatemo.js"></script>
<script src="/client/assets/js/custom.js"></script>


</html>