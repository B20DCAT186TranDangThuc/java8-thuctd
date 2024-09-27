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
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/7.2.96/css/materialdesignicons.min.css"
          integrity="sha512-LX0YV/MWBEn2dwXCYgQHrpa9HJkwB+S+bnBpifSOTO1No27TqNMKYoAn6ff2FBh03THAzAiiCwQ+aPX+/Qt/Ow=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <!-- Load fonts style after rendering the layout styles -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
    <link rel="stylesheet" href="/client/assets/css/fontawesome.min.css">
</head>
<style>
    body {
        margin-top: 20px;
        background: #f7f8fa
    }

    .avatar-xxl {
        height: 7rem;
        width: 7rem;
    }

    .card {
        margin-bottom: 20px;
        -webkit-box-shadow: 0 2px 3px #eaedf2;
        box-shadow: 0 2px 3px #eaedf2;
    }

    .pb-0 {
        padding-bottom: 0 !important;
    }

    .font-size-16 {
        font-size: 16px !important;
    }

    .avatar-title {
        -webkit-box-align: center;
        -ms-flex-align: center;
        align-items: center;
        background-color: #038edc;
        color: #fff;
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        font-weight: 500;
        height: 100%;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        justify-content: center;
        width: 100%;
    }

    .bg-soft-primary {
        background-color: rgba(3, 142, 220, .15) !important;
    }

    .rounded-circle {
        border-radius: 50% !important;
    }

    .nav-tabs-custom .nav-item .nav-link.active {
        color: #038edc;
    }

    .nav-tabs-custom .nav-item .nav-link {
        border: none;
    }

    .nav-tabs-custom .nav-item .nav-link.active {
        color: #038edc;
    }

    .avatar-group {
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -ms-flex-wrap: wrap;
        flex-wrap: wrap;
        padding-left: 12px;
    }

    .border-end {
        border-right: 1px solid #eff0f2 !important;
    }

    .d-inline-block {
        display: inline-block !important;
    }

    .badge-soft-danger {
        color: #f34e4e;
        background-color: rgba(243, 78, 78, .1);
    }

    .badge-soft-warning {
        color: #f7cc53;
        background-color: rgba(247, 204, 83, .1);
    }

    .badge-soft-success {
        color: #51d28c;
        background-color: rgba(81, 210, 140, .1);
    }

    .avatar-group .avatar-group-item {
        margin-left: -14px;
        border: 2px solid #fff;
        border-radius: 50%;
        -webkit-transition: all .2s;
        transition: all .2s;
    }

    .avatar-sm {
        height: 2rem;
        width: 2rem;
    }

    .nav-tabs-custom .nav-item {
        position: relative;
        color: #343a40;
    }

    .nav-tabs-custom .nav-item .nav-link.active:after {
        -webkit-transform: scale(1);
        transform: scale(1);
    }

    .nav-tabs-custom .nav-item .nav-link::after {
        content: "";
        background: #038edc;
        height: 2px;
        position: absolute;
        width: 100%;
        left: 0;
        bottom: -2px;
        -webkit-transition: all 250ms ease 0s;
        transition: all 250ms ease 0s;
        -webkit-transform: scale(0);
        transform: scale(0);
    }

    .badge-soft-secondary {
        color: #74788d;
        background-color: rgba(116, 120, 141, .1);
    }

    .badge-soft-secondary {
        color: #74788d;
    }

    .work-activity {
        position: relative;
        color: #74788d;
        padding-left: 5.5rem
    }

    .work-activity::before {
        content: "";
        position: absolute;
        height: 100%;
        top: 0;
        left: 66px;
        border-left: 1px solid rgba(3, 142, 220, .25)
    }

    .work-activity .work-item {
        position: relative;
        border-bottom: 2px dashed #eff0f2;
        margin-bottom: 14px
    }

    .work-activity .work-item:last-of-type {
        padding-bottom: 0;
        margin-bottom: 0;
        border: none
    }

    .work-activity .work-item::after, .work-activity .work-item::before {
        position: absolute;
        display: block
    }

    .work-activity .work-item::before {
        content: attr(data-date);
        left: -157px;
        top: -3px;
        text-align: right;
        font-weight: 500;
        color: #74788d;
        font-size: 12px;
        min-width: 120px
    }

    .work-activity .work-item::after {
        content: "";
        width: 10px;
        height: 10px;
        border-radius: 50%;
        left: -26px;
        top: 3px;
        background-color: #fff;
        border: 2px solid #038edc
    }

</style>
<body>

<jsp:include page="../layout/header.jsp"/>

<!-- Start Content -->
<div class="container mt-5">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-body pb-0">
                    <div class="row align-items-center">
                        <div class="col-md-3">
                            <div class="text-center border-end">
                                <img src="https://bootdey.com/img/Content/avatar/avatar1.png"
                                     class="img-fluid avatar-xxl rounded-circle" alt="">
                                <h4 class="text-primary font-size-20 mt-3 mb-2">${accountInfo.username}</h4>
                                <h5 class="text-muted font-size-13 mb-0">${accountInfo.role}</h5>
                            </div>
                        </div><!-- end col -->
                        <div class="col-md-9">
                            <div class="ms-3">
                                <div>
                                    <h4 class="card-title mb-2">Biography</h4>
                                    <p class="mb-0 text-muted">Hi I'm Jansh,has been the industry's standard
                                        dummy text To an English person alteration text.</p>
                                </div>
                                <div class="row my-4">
                                    <div class="col-md-12">
                                        <div>
                                            <p class="text-muted mb-2 fw-medium"><i
                                                    class="mdi mdi-email-outline me-2"></i>Janshwells@probic.com
                                            </p>
                                            <p class="text-muted fw-medium mb-0"><i
                                                    class="mdi mdi-phone-in-talk-outline me-2"></i>418-955-4703
                                            </p>
                                        </div>
                                    </div><!-- end col -->
                                </div><!-- end row -->
                            </div>
                        </div><!-- end col -->
                    </div><!-- end row -->
                </div><!-- end card body -->
            </div><!-- end card -->

            <div class="card">
                <div class="tab-content p-4">

                    <div class="tab-pane active show" id="tasks-tab" role="tabpanel">
                        <h4 class="card-title mb-4">Orders</h4>

                        <div class="row">
                            <div class="col-xl-12">
                                <c:forEach var="order" items="${listOrder}">
                                    <div class="task-list-box" id="order-task-${order.id}">
                                        <div class="card task-box rounded-3">
                                            <div class="card-body">
                                                <div class="row align-items-center">
                                                    <div class="col-xl-6 col-sm-5">
                                                        <div class="checklist form-check font-size-15">
                                                            <label class="form-check-label ms-1 task-title">
                                                                    ${order.customerName}
                                                                <!-- Hoặc sử dụng thuộc tính thích hợp trong Order -->
                                                            </label>
                                                        </div>
                                                    </div><!-- end col -->
                                                    <div class="col-xl-6 col-sm-7">
                                                        <div class="row align-items-center">
                                                            <div class="col-xl-5 col-md-6 col-sm-5">
                                                                <div class="avatar-group mt-3 mt-xl-0 task-assigne">
                                                                    <c:forEach var="orderDetail"
                                                                               items="${order.orderDetails}">
                                                                        <div class="avatar-group-item">
                                                                            <a href="javascript: void(0);"
                                                                               class="d-inline-block"
                                                                               data-bs-toggle="tooltip"
                                                                               data-bs-placement="top"
                                                                               title="${orderDetail.product.name}">
                                                                                <img src="${pageContext.request.contextPath}/uploads/${orderDetail.product.id}"
                                                                                     alt=""
                                                                                     class="rounded-circle avatar-sm">
                                                                            </a>
                                                                        </div>
                                                                    </c:forEach>
                                                                </div><!-- end avatar group -->
                                                            </div><!-- end col -->
                                                            <div class="col-xl-7 col-md-6 col-sm-7">
                                                                <div class="d-flex flex-wrap gap-3 mt-3 mt-xl-0 justify-content-md-end">
                                                                    <div>
                                                                        <c:choose>
                                                                            <c:when test="${order.status == 'PENDING'}">
                                                                                <span class="badge rounded-pill badge-soft-danger font-size-11 task-status">Pending</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status == 'CONFIRMED'}">
                                                                                <span class="badge rounded-pill badge-soft-warning font-size-11 task-status">Confirmed</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status == 'SHIPPED'}">
                                                                                <span class="badge rounded-pill badge-soft-success font-size-11 task-status">Shipped</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status == 'DELIVERED'}">
                                                                                <span class="badge rounded-pill badge-soft-success font-size-11 task-status">Delivered</span>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <span class="badge rounded-pill badge-soft-secondary font-size-11 task-status">Unknown Status</span>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </div>
                                                                    <div>
                                                                        <a href="#" class="mb-0 text-muted fw-medium"
                                                                           data-bs-toggle="modal"
                                                                           data-bs-target=".bs-example-new-task">
                                                                            <i class="mdi mdi-square-edit-outline font-size-16 align-middle"
                                                                               onclick="editTask('order-task-${order.id}')"></i>
                                                                        </a>
                                                                    </div>
                                                                    <div>
                                                                        <a href="#" class="delete-item"
                                                                           onclick="deleteProjects('order-task-${order.id}')">
                                                                            <i class="mdi mdi-trash-can-outline font-size-16 align-middle text-danger"></i>
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                            </div><!-- end col -->
                                                        </div><!-- end row -->
                                                    </div><!-- end col -->
                                                </div><!-- end row -->
                                            </div><!-- end card body -->
                                        </div><!-- end card -->
                                    </div>
                                    <!-- end task list box -->
                                </c:forEach>
                            </div><!-- end col -->
                        </div><!-- end row -->
                    </div><!-- end tab pane -->

                </div>
            </div><!-- end card -->
        </div><!-- end col -->
    </div>
</div>
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