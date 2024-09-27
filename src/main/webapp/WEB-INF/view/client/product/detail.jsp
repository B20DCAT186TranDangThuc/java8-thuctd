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
    .be-comment-block {
        margin-bottom: 50px !important;
        border: 1px solid #edeff2;
        border-radius: 2px;
        padding: 50px 70px;
        border: 1px solid #ffffff;
    }

    .comments-title {
        font-size: 16px;
        color: #262626;
        margin-bottom: 15px;
        font-family: 'Conv_helveticaneuecyr-bold';
    }

    .be-img-comment {
        width: 60px;
        height: 60px;
        float: left;
        margin-bottom: 15px;
    }

    .be-ava-comment {
        width: 60px;
        height: 60px;
        border-radius: 50%;
    }

    .be-comment-content {
        margin-left: 80px;
    }

    .be-comment-content span {
        display: inline-block;
        width: 49%;
        margin-bottom: 15px;
    }

    .be-comment-name {
        font-size: 13px;
        font-family: 'Conv_helveticaneuecyr-bold';
    }

    .be-comment-content a {
        color: #383b43;
    }

    .be-comment-content span {
        display: inline-block;
        width: 49%;
        margin-bottom: 15px;
    }

    .be-comment-time {
        text-align: right;
    }

    .be-comment-time {
        font-size: 11px;
        color: #b4b7c1;
    }

    .be-comment-text {
        font-size: 13px;
        line-height: 18px;
        color: #7a8192;
        display: block;
        background: #f6f6f7;
        border: 1px solid #edeff2;
        padding: 15px 20px 20px 20px;
    }

    .form-group.fl_icon .icon {
        position: absolute;
        top: 1px;
        left: 16px;
        width: 48px;
        height: 48px;
        background: #f6f6f7;
        color: #b5b8c2;
        text-align: center;
        line-height: 50px;
        -webkit-border-top-left-radius: 2px;
        -webkit-border-bottom-left-radius: 2px;
        -moz-border-radius-topleft: 2px;
        -moz-border-radius-bottomleft: 2px;
        border-top-left-radius: 2px;
        border-bottom-left-radius: 2px;
    }

    .form-group .form-input {
        font-size: 13px;
        line-height: 50px;
        font-weight: 400;
        color: #b4b7c1;
        width: 100%;
        height: 50px;
        padding-left: 20px;
        padding-right: 20px;
        border: 1px solid #edeff2;
        border-radius: 3px;
    }

    .form-group.fl_icon .form-input {
        padding-left: 70px;
    }

    .form-group textarea.form-input {
        height: 150px;
    }

    .custom-alert {
        position: fixed;
        top: 20px; /* Điều chỉnh khoảng cách từ phía trên */
        right: 20px; /* Điều chỉnh khoảng cách từ bên phải */
        width: 300px; /* Đặt chiều rộng cho thông báo */
        z-index: 9999; /* Đảm bảo thông báo nằm trên cùng */
        margin-bottom: 10px; /* Khoảng cách giữa các thông báo */
    }

</style>
<body>

<jsp:include page="../layout/header.jsp"/>

<!-- Start Content -->
<section class="bg-light">
    <div class="container pb-5">
        <c:if test="${not empty success}">
            <div class="alert alert-success" role="alert" style="width: 40%; margin: 0 auto;">
                    ${success}
            </div>
        </c:if>

        <c:if test="${not empty failComment}">
            <div class="alert alert-danger" role="alert" style="width: 40%; margin: 0 auto;">
                    ${failComment}
            </div>
        </c:if>
        <div class="row">
            <div class="col-lg-5 mt-5">
                <div class="card mb-3">
                    <img class="card-img img-fluid" src="${pageContext.request.contextPath}/uploads/${product.id}"
                         alt="${product.name}" alt="Card image cap"
                         id="product-detail">
                </div>

            </div>
            <!-- col end -->
            <div class="col-lg-7 mt-5">
                <div class="card">
                    <div class="card-body">
                        <h1 class="h2">${product.name}</h1>
                        <p class="h3 py-2">
                            <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="VND"/>
                        </p>
                        <p class="py-2">
                            <span class="list-inline-item text-dark">Quantity: ${product.quantity} | ${listComment.size()} Comments</span>
                        </p>


                        <h6>Description:</h6>
                        <p>${product.description}</p>


                        <form:form action="/products/switch" method="post" modelAttribute="orderItem">
                            <form:input type="hidden" name="product-title" path="productId" value="${product.id}"/>
                            <div class="row">

                                <div class="col-auto">
                                    <ul class="list-inline pb-3">
                                        <li class="list-inline-item text-right">
                                            Quantity
                                            <form:input type="hidden" name="product-quanity" id="product-quanity"
                                                        value="1" path="quantity"/>
                                        </li>
                                        <li class="list-inline-item"><span class="btn btn-success"
                                                                           id="btn-minus">-</span></li>
                                        <li class="list-inline-item"><span class="badge bg-secondary"
                                                                           id="var-value">1</span></li>
                                        <li class="list-inline-item"><span class="btn btn-success"
                                                                           id="btn-plus">+</span></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="row pb-3">
                                <div class="col d-grid">
                                    <button type="submit" class="btn btn-success btn-lg" name="submit" value="buy">Buy
                                    </button>
                                </div>
                                <div class="col d-grid">
                                    <button type="submit" class="btn btn-success btn-lg" name="submit"
                                            value="addtocart">Add To Cart
                                    </button>
                                </div>
                            </div>

                        </form:form>

                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="container">
                <div class="be-comment-block">
                    <h1 class="comments-title">Comments (${listComment.size()})</h1>
                    <c:forEach var="comment" items="${listComment}">
                        <div class="be-comment">
                            <div class="be-img-comment">
                                <a href="blog-detail-2.html">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar3.png" alt=""
                                         class="be-ava-comment">
                                </a>
                            </div>
                            <div class="be-comment-content">
                <span class="be-comment-name">
                    <a href="blog-detail-2.html">${comment.authorName}</a>
                </span>
                                <span class="be-comment-time">
                    <i class="fa fa-clock-o"></i>
                    ${comment.createAt.format(formater)}
                </span>
                                <p class="be-comment-text">
                                        ${comment.content}
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                    <form:form action="/products/comment" method="post" class="form-block" modelAttribute="comment">
                        <form:input type="hidden" path="productId" value="${product.id}"/>
                        <c:set var="errorName">
                            <form:errors path="authorName"
                                         cssClass="invalid-feedback" />
                        </c:set>
                        <c:set var="errorEmail">
                            <form:errors path="authorEmail"
                                         cssClass="invalid-feedback" />
                        </c:set>
                        <c:set var="errorContent">
                            <form:errors path="content"
                                         cssClass="invalid-feedback" />
                        </c:set>

                        <div class="row">
                            <div class="col-xs-12 col-sm-6 mb-2">
                                <div class="form-group fl_icon">
                                    <div class="icon"><i class="fa fa-user"></i></div>
                                    <form:input class="form-input ${not empty errorName ? 'is-invalid' : ''}" type="text" path="authorName"
                                                placeholder="Your name"/>
                                    <form:errors path="authorName"
                                                 cssClass="invalid-feedback" />
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6 fl_icon mb-2">
                                <div class="form-group fl_icon">
                                    <div class="icon"><i class="fa fa-envelope-o"></i></div>
                                    <form:input class="form-input ${not empty errorEmail ? 'is-invalid' : ''}" path="authorEmail" type="text"
                                                placeholder="Your email"/>
                                    ${errorEmail}
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <form:textarea class="form-input ${not empty errorContent ? 'is-invalid' : ''}" path="content" required=""
                                                   placeholder="Your text"></form:textarea>
                                    ${errorContent}
                                </div>
                            </div>

                        </div>
                        <button type="submit" class="btn btn-success mt-4">Submit</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- End Content -->


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