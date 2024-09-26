<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="/img/logo-fav.png">
    <title>Beagle</title>
    <link rel="stylesheet" type="text/css" href="/lib/perfect-scrollbar/css/perfect-scrollbar.css">
    <link rel="stylesheet" type="text/css" href="/lib/material-design-icons/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" type="text/css" href="/lib/datatables/datatables.net-bs4/css/dataTables.bootstrap4.css">
    <link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
    <link rel="stylesheet" type="text/css"
          href="/lib/datatables/datatables.net-responsive-bs4/css/responsive.bootstrap4.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="/css/app.css" type="text/css">


</head>

<style>
    @import url('https://fonts.googleapis.com/css?family=Open+Sans&display=swap');

    body {
        background-color: #eeeeee;
        font-family: 'Open Sans', serif
    }


    .card {
        position: relative;
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-orient: vertical;
        -webkit-box-direction: normal;
        -ms-flex-direction: column;
        flex-direction: column;
        min-width: 0;
        word-wrap: break-word;
        background-color: #fff;
        background-clip: border-box;
        border: 1px solid rgba(0, 0, 0, 0.1);
        border-radius: 0.10rem
    }

    .card-header:first-child {
        border-radius: calc(0.37rem - 1px) calc(0.37rem - 1px) 0 0
    }

    .card-header {
        padding: 0.75rem 1.25rem;
        margin-bottom: 0;
        background-color: #fff;
        border-bottom: 1px solid rgba(0, 0, 0, 0.1)
    }

    .track {
        position: relative;
        background-color: #ddd;
        height: 7px;
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        margin-bottom: 60px;
        margin-top: 50px
    }

    .track .step {
        -webkit-box-flex: 1;
        -ms-flex-positive: 1;
        flex-grow: 1;
        width: 25%;
        margin-top: -18px;
        text-align: center;
        position: relative
    }

    .track .step.active:before {
        background: #FF5722
    }

    .track .step::before {
        height: 7px;
        position: absolute;
        content: "";
        width: 100%;
        left: 0;
        top: 18px
    }

    .track .step.active .icon {
        background: #ee5435;
        color: #fff
    }

    .track .icon {
        display: inline-block;
        width: 40px;
        height: 40px;
        line-height: 40px;
        position: relative;
        border-radius: 100%;
        background: #ddd
    }

    .track .step.active .text {
        font-weight: 400;
        color: #000
    }

    .track .text {
        display: block;
        margin-top: 7px
    }

    .itemside {
        position: relative;
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        width: 100%
    }

    .itemside .aside {
        position: relative;
        -ms-flex-negative: 0;
        flex-shrink: 0
    }

    .img-sm {
        width: 80px;
        height: 80px;
        padding: 7px
    }

    ul.row, ul.row-sm {
        list-style: none;
        padding: 0
    }

    .itemside .info {
        padding-left: 15px;
        padding-right: 7px
    }

    .itemside .title {
        display: block;
        margin-bottom: 5px;
        color: #212529
    }

    p {
        margin-top: 0;
        margin-bottom: 1rem
    }

    .btn-warning {
        color: #ffffff;
        background-color: #ee5435;
        border-color: #ee5435;
        border-radius: 1px
    }

    .btn-warning:hover {
        color: #ffffff;
        background-color: #ff2b00;
        border-color: #ff2b00;
        border-radius: 1px
    }

</style>
<body>
<jsp:include page="../layout/header.jsp"/>
<jsp:include page="../layout/left-sidebar.jsp"/>

<div class="be-content" style="margin-top: 60px;">
    <div class="main-content container-fluid">
        <div class="page-head">
            <h2 class="page-head-title">Order Management</h2>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb page-head-nav">
                    <li class="breadcrumb-item"><a href="/admin/orders">Order List</a></li>
                    <li class="breadcrumb-item active">Detail</li>
                </ol>
            </nav>
        </div>

        <div class="container">
            <article class="card">
                <div class="card-body">
                    <h6>Order ID: ${orderDetail.orderId}</h6>
                    <article class="card">
                        <div class="card-body row">
                            <div class="col"><strong>Order date:</strong> <br>${formatter.format(orderDetail.orderDate)}
                            </div>
                            <div class="col"><strong>Customer Infomation:</strong> <br> ${orderDetail.customerName}, |
                                <i
                                        class="fa fa-phone"></i> ${orderDetail.customerPhone}
                            </div>
                            <div class="col"><strong>Status:</strong> <br> ${orderDetail.status}</div>
                            <div class="col"><strong>Amount:</strong> <br> <fmt:formatNumber
                                    value="${orderDetail.amount}" type="currency" currencySymbol="VND"/></div>
                        </div>
                    </article>
                    <div class="track">
                        <!-- Pending Step -->
                        <div class="step ${orderDetail.status == 'PENDING' || orderDetail.status == 'CONFIRMED' || orderDetail.status == 'SHIPPED' || orderDetail.status == 'DELIVERED' ? 'active' : ''}">
                            <span class="icon"><i class="fa fa-user"></i></span>
                            <span class="text">Pending</span>
                        </div>

                        <!-- Confirmed Step -->
                        <div class="step ${orderDetail.status == 'CONFIRMED' || orderDetail.status == 'SHIPPED' || orderDetail.status == 'DELIVERED' ? 'active' : ''}">
                            <span class="icon"><i class="fa fa-check"></i></span>
                            <span class="text">Confirmed</span>
                        </div>

                        <!-- Shipped Step -->
                        <div class="step ${orderDetail.status == 'SHIPPED' || orderDetail.status == 'DELIVERED' ? 'active' : ''}">
                            <span class="icon"><i class="fa fa-truck"></i></span>
                            <span class="text">Shipped</span>
                        </div>

                        <!-- Delivered Step -->
                        <div class="step ${orderDetail.status == 'DELIVERED' ? 'active' : ''}">
                            <span class="icon"><i class="fa fa-box"></i></span>
                            <span class="text">Delivered</span>
                        </div>
                    </div>
                    <hr>
                    <ul class="row">
                        <ul class="row">
                            <c:forEach var="item" items="${orderDetail.orderDetailList}">
                                <li class="col-md-4">
                                    <figure class="itemside mb-3">
                                        <div class="aside">
                                            <img src="${pageContext.request.contextPath}/uploads/${item.product.id}" class="img-sm border">
                                        </div>
                                        <figcaption class="info align-self-center">
                                            <p class="title">${item.product.name}</p>
                                            <p class="tile-value">${item.quantity}</p>
                                            <span class="text-muted"><fmt:formatNumber
                                                    value="${item.amount}" type="currency"
                                                    currencySymbol="VND"/></span>
                                        </figcaption>
                                    </figure>
                                </li>
                            </c:forEach>
                        </ul>
                    </ul>
                    <hr>
                    <a href="/admin/orders" class="btn btn-warning" data-abc="true"> <i class="fa fa-chevron-left"></i>
                        Back to
                        orders</a>
                </div>
            </article>
        </div>
    </div>

    <c:if test="${not empty param.success}">
    <div class="alert alert-success alert-dismissible" role="alert" style="transition: opacity 0.5s ease;">
        <div class="message" style="padding-left: 20px;"><strong>Good! </strong>${param.success}</div>
    </div>
    </c:if>


</body>
<script src="/lib/jquery/jquery.min.js" type="text/javascript"></script>
<script src="/lib/perfect-scrollbar/js/perfect-scrollbar.min.js" type="text/javascript"></script>
<script src="/lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
<script src="/js/app.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        //-initialize the javascript
        App.init();
        App.dashboard();

    });

</script>
</body>
</html>