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
<body>

<jsp:include page="../layout/header.jsp"/>

<div class="container py-5">
    <!-- Thay đổi modelAttribute để binding với OrderForm -->

    <c:choose>

        <c:when test="${orderForm.orderDetailInfos.size() > 0}">
            <form:form action="/carts/checkout" method="post" modelAttribute="orderForm"
                       class="row d-flex justify-content-center my-4">
                <div class="col-md-8">
                    <c:forEach items="${orderForm.orderDetailInfos}" var="orderDetailInfo" varStatus="status">
                        <form:input type="hidden" path="orderDetailInfos[${status.index}].productId"/>

                        <div class="card mb-4">
                            <div class="row" style="align-items: center; padding: 10px;">
                                <div class="col-lg-3 col-md-12 mb-4 mb-lg-0">
                                    <div class="bg-image hover-overlay hover-zoom ripple rounded"
                                         data-mdb-ripple-color="light">
                                        <img src="${pageContext.request.contextPath}/uploads/${orderDetailInfo.productId}"
                                             class="w-100" alt="Product Image"/>
                                        <a href="#!">
                                            <div class="mask" style="background-color: rgba(251, 251, 251, 0.2)"></div>
                                        </a>
                                    </div>
                                </div>

                                <div class="col-lg-5 col-md-6 mb-4 mb-lg-0">
                                    <p><strong>${orderDetailInfo.nameProduct}</strong></p>
                                    <form:input path="orderDetailInfos[${status.index}].price" type="hidden"/>
                                    <fmt:formatNumber value="${orderDetailInfo.price}" type="currency"
                                                      currencySymbol="VND"/>
                                </div>

                                <div class="col-lg-4 col-md-6 mb-4 mb-lg-0">
                                    <div class="d-flex mb-4" style="max-width: 300px">
                                        <a data-mdb-button-init data-mdb-ripple-init style="height: 41px;"
                                           class="btn btn-primary px-3 me-2"
                                           onclick="decrementQuantity(this, ${status.index})">
                                            <i class="fas fa-minus"></i>
                                        </a>

                                        <div data-mdb-input-init class="form-outline">
                                            <form:input id="form1" min="1"
                                                        name="orderDetailInfos[${status.index}].quantity"
                                                        type="number" class="form-control"
                                                        path="orderDetailInfos[${status.index}].quantity"
                                                        oninput="updateAmount(this, '${status.index}')"/>
                                            <label class="form-label" for="form1">Quantity</label>
                                            <!-- Hiển thị thông báo lỗi cho trường quantity -->
                                            <form:errors path="orderDetailInfos[${status.index}].quantity"
                                                         cssClass="text-danger"/>
                                        </div>

                                        <a data-mdb-button-init data-mdb-ripple-init style="height: 41px;"
                                           class="btn btn-primary px-3 ms-2"
                                           onclick="incrementQuantity(this, ${status.index})">
                                            <i class="fas fa-plus"></i>
                                        </a>
                                    </div>

                                    <p class="text-start text-md-center">
                                        <strong class="amount-display" id="amount-display-${status.index}"
                                                data-set="${orderDetailInfo.amount}">
                                            <fmt:formatNumber value="${orderDetailInfo.amount}" type="currency"
                                                              currencySymbol="VND"/>
                                        </strong>
                                        <form:hidden path="orderDetailInfos[${status.index}].amount"/>
                                    </p>

                                    <!-- Bootstrap delete button -->
                                    <div class="text-end mt-2">
                                        <a href="/carts/deleteItem/${orderDetailInfo.id}" class="btn btn-danger">
                                            <i class="fas fa-trash-alt"></i> Delete
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="col-md-4">
                    <div class="card mb-4">
                        <div class="card-header py-3">
                            <h5 class="mb-0">Customer Information</h5>
                        </div>
                        <div class="card-body">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
                                    Name
                                    <span>
                            <form:input class="form-control" type="text" path="customerName"/>
                            <form:errors path="customerName" cssClass="text-danger"/>
                        </span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                                    Email
                                    <span>
                            <form:input class="form-control" type="text" path="customerEmail"/>
                            <form:errors path="customerEmail" cssClass="text-danger"/>
                        </span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                                    Phone
                                    <span>
                            <form:input class="form-control" type="text" path="customerPhone"/>
                            <form:errors path="customerPhone" cssClass="text-danger"/>
                        </span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                                    Address
                                    <span>
                            <form:input class="form-control" type="text" path="customerAddress"/>
                            <form:errors path="customerAddress" cssClass="text-danger"/>
                        </span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                                    Total
                                    <span>
                            <strong class="total-amount" data-set="${orderForm.orderAmount}">
                                <fmt:formatNumber value="${orderForm.orderAmount}" type="currency"
                                                  currencySymbol="VND"/>
                            </strong>
                            <form:input class="form-control orderAmount" type="hidden" path="orderAmount"/>
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
        </c:when>

        <c:otherwise>
            <div class="alert alert-warning text-center" role="alert">
                <h4 class="alert-heading">Giỏ hàng trống!</h4>
                <p>Bạn hiện không có sản phẩm nào trong giỏ hàng. Vui lòng quay lại cửa hàng để tiếp tục mua sắm.</p>
                <hr>
                <a href="/products" class="btn btn-primary">
                    Quay lại cửa hàng
                </a>
            </div>
        </c:otherwise>
    </c:choose>


</div>

<jsp:include page="../layout/footer.jsp"/>

</body>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const form = document.querySelector("form");
        const amountDisplay = document.querySelectorAll('strong[class="amount-display"]');


        amountDisplay.forEach(a => {
            const amount = a.getAttribute('data-set');
            if (a) {
                a.textContent = new Intl.NumberFormat('vi-VN', {
                    style: 'currency',
                    currency: 'VND'
                }).format(amount);
            }
        })

        const totalElm = document.querySelector('.total-amount');
        totalElm.textContent = new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND'
        }).format(totalElm.getAttribute('data-set'));


    })

    function decrementQuantity(element, index) {
        const quantityInput = element.parentNode.querySelector('input[type=number]');
        if (quantityInput) {
            quantityInput.stepDown();
            updateAmount(quantityInput, index); // Cập nhật amount nếu cần
        }
    }

    function incrementQuantity(element, index) {
        const quantityInput = element.parentNode.querySelector('input[type=number]');
        if (quantityInput) {
            quantityInput.stepUp();
            updateAmount(quantityInput, index); // Cập nhật amount nếu cần
        }
    }

    function updateAmount(quantityInput, index) {
        const quantity = parseInt(quantityInput.value, 10);
        const card = quantityInput.closest('.card'); // Tìm phần tử card chứa quantityInput
        const a = 'input[type=hidden][id="orderDetailInfos' + index + '.price"]';
        const b = 'input[type=hidden][id="orderDetailInfos' + index + '.amount"]';
        console.log("ab", a, b)
        // Lấy giá trị price từ thẻ input hidden
        const priceInput = card.querySelector(a);
        const amountInput = card.querySelector(b); // Lấy giá trị amount

        const c = 'strong[id="amount-display-' + index + '"]'
        const amountDisplay = card.querySelector(c)

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

            const amountDisplays = document.querySelectorAll('strong[class="amount-display"]');
            const total = Array.from(amountDisplays).reduce((sum, cur) => sum += +cur.textContent.replace(' ₫', '').replace(/\./g, ''), 0);

            document.querySelector('.total-amount').textContent = new Intl.NumberFormat('vi-VN', {
                style: 'currency',
                currency: 'VND'
            }).format(total);
            document.querySelector('.orderAmount').value = total;
            console.log("total", total)
        }
    }
</script>
<script src="/client/assets/js/jquery-1.11.0.min.js"></script>
<script src="/client/assets/js/jquery-migrate-1.2.1.min.js"></script>
<script src="/client/assets/js/bootstrap.bundle.min.js"></script>
<script src="/client/assets/js/templatemo.js"></script>
<script src="/client/assets/js/custom.js"></script>


</html>