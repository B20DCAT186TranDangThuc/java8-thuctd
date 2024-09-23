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
    <link rel="shortcut icon" type="image/x-icon" href="client/assets/img/favicon.ico">

    <link rel="stylesheet" href="/client/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/client/assets/css/templatemo.css">
    <link rel="stylesheet" href="/client/assets/css/custom.css">

    <!-- Load fonts style after rendering the layout styles -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
    <link rel="stylesheet" href="/client/assets/css/fontawesome.min.css">
</head>
<style>
    .product-name {
        white-space: nowrap; /* Prevent line breaks */
        overflow: hidden; /* Hide overflow content */
        text-overflow: ellipsis; /* Show ellipsis for truncated content */
        display: block; /* Make it block to apply width */
        width: 100%; /* Ensure it takes full width */
    }
</style>
<body>

<jsp:include page="../layout/header.jsp"/>

<!-- Start Content -->
<div class="container py-5">
    <div class="row">
        <!-- Product Loop -->
        <div class="row">
            <c:forEach var="product" items="${productPage.content}">
                <div class="col-md-3">
                    <div class="card mb-4 product-wap rounded-0">
                        <div class="card rounded-0">
                            <img class="card-img rounded-0 img-fluid"
                                 src="${pageContext.request.contextPath}/uploads/${product.id}" alt="${product.name}"
                            style="min-height: 320px;">
                            <div class="card-img-overlay rounded-0 product-overlay d-flex align-items-center justify-content-center">
                                <ul class="list-unstyled">
                                    <li><a class="btn btn-success text-white mt-2"
                                           href="/products/detail/${product.id}"><i
                                            class="far fa-eye"></i></a></li>
                                    <li>
                                        <a class="btn btn-success text-white mt-2"
                                           href="/cart/view/${product.id}"><i class="fas fa-cart-plus"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="card-body">
                            <a href="shop-single.html?id=${product.id}"
                               class="h3 text-decoration-none">${product.name}</a>
                            <p class="text-center mb-0">
                                <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="VND"/>
                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Pagination -->
        <div class="row">
            <ul class="pagination pagination-lg justify-content-end">
                <c:forEach var="i" begin="1" end="${productPage.totalPages}">
                    <li class="page-item ${productPage.number + 1 == i ? 'active' : ''}">
                        <a class="page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark"
                           href="?page=${i-1}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<!-- End Content -->
<div class="modal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Modal title</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Modal body text goes here.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../layout/footer.jsp"/>

</body>
<script src="/client/assets/js/jquery-1.11.0.min.js"></script>
<script src="/client/assets/js/jquery-migrate-1.2.1.min.js"></script>
<script src="/client/assets/js/bootstrap.bundle.min.js"></script>
<script src="/client/assets/js/templatemo.js"></script>
<script src="/client/assets/js/custom.js"></script>

<script>

</script>
</html>