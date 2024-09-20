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
    <link rel="stylesheet" type="text/css" href="/lib/datatables/datatables.net-responsive-bs4/css/responsive.bootstrap4.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="/css/app.css" type="text/css">
</head>

<style>
    .alert {
        position: fixed; /* Cố định vị trí */
        top: 80px; /* Cách đỉnh 20px */
        right: 20px; /* Cách phải 20px */
        z-index: 1050; /* Đảm bảo thông báo hiển thị trên các phần tử khác */
        width: auto; /* Điều chỉnh độ rộng phù hợp */
        max-width: 300px; /* Độ rộng tối đa của thông báo */
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Tạo hiệu ứng đổ bóng nhẹ */
    }

</style>
<body>
<jsp:include page="../layout/header.jsp" />
<jsp:include page="../layout/left-sidebar.jsp" />

<div class="be-content" style="margin-top: 60px;">
    <div class="main-content container-fluid">
        <div class="page-head">
            <h2 class="page-head-title">Product Management</h2>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb page-head-nav">
                    <li class="breadcrumb-item active">Product List</li>
                </ol>
            </nav>
        </div>

        <div class="form-group row p-4 justify-content-between">
            <div class="col-12 col-sm-5 col-lg-4">
                <form action="/admin/products" method="get" class="input-group input-search">
                    <input class="form-control" type="text" name="keyword" placeholder="Search" value="${keyword}">
                    <span class="input-group-btn">
                            <button class="btn btn-secondary" type="submit"><i class="icon mdi mdi-search"></i></button>
                        </span>
                </form>
            </div>
            <div class="col-12 col-sm-2 col-lg-5 d-flex justify-content-end">
                <a href="/admin/products/create" class="btn d-flex justify-content-center align-items-center" style="border-radius: 50%; border: 1px #000 solid;width: 40px; height: 40px">
                    <img src="/img/add-product.png" style="width: 20px; height: 20px" />
                </a>
            </div>
        </div>

        <div class="col-sm-12">
            <div class="card card-table">
                <div class="card-body">
                    <div id="table2_wrapper" class="dataTables_wrapper dt-bootstrap4 no-footer">
                        <div class="row be-datatable-body">
                            <div class="col-sm-12">
                                <table class="table table-striped table-hover table-fw-widget dataTable no-footer" id="table2" role="grid">
                                    <thead>
                                    <tr role="row">
                                        <th class="text-center" style="width: 100px;">Code</th>
                                        <th class="text-center" style="width: 120px;">Name</th>
                                        <th class="text-center" style="width: 120px;">Price</th>
                                        <th class="text-center" style="width: 120px;">Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${productPage.content}" var="product">
                                        <tr class="gradeA odd" role="row">
                                            <td class="text-center">${product.code}</td>
                                            <td class="text-center">${product.name}</td>
                                            <td class="text-center">
                                                <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="VND" />
                                            </td>
                                            <td class="text-center">
                                                <a href="/admin/products/detail/${product.id}" class="btn btn-secondary">Detail</a>
                                                <a href="/admin/products/update/${product.id}" class="btn btn-primary">Update</a>
                                                <a href="/admin/products/delete/${product.id}" class="btn btn-danger">Delete</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="row be-datatable-footer">
                            <div class="col-sm-7">
                                <div class="dataTables_paginate paging_simple_numbers" id="table2_paginate">
                                    <nav aria-label="Page navigation example">
                                        <ul class="pagination">
                                            <c:if test="${productPage.hasPrevious()}">
                                                <li class="page-item">
                                                    <a class="page-link" href="?page=${productPage.number - 1}&keyword=${keyword}" aria-label="Previous">
                                                        <span aria-hidden="true">&laquo;</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:if test="${!productPage.hasPrevious()}">
                                                <li class="page-item disabled">
                                                    <a class="page-link" href="#" aria-label="Previous">
                                                        <span aria-hidden="true">&laquo;</span>
                                                    </a>
                                                </li>
                                            </c:if>

                                            <c:forEach begin="1" end="${productPage.totalPages}" var="page">
                                                <li class="page-item ${productPage.number + 1 == page ? 'active' : ''}">
                                                    <a class="page-link" href="?page=${page - 1}&keyword=${keyword}">${page}</a>
                                                </li>
                                            </c:forEach>

                                            <c:if test="${productPage.hasNext()}">
                                                <li class="page-item">
                                                    <a class="page-link" href="?page=${productPage.number + 1}&keyword=${keyword}" aria-label="Next">
                                                        <span aria-hidden="true">&raquo;</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:if test="${!productPage.hasNext()}">
                                                <li class="page-item disabled">
                                                    <a class="page-link" href="#" aria-label="Next">
                                                        <span aria-hidden="true">&raquo;</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<c:if test="${not empty param.success}">
    <div class="alert alert-success alert-dismissible" role="alert" style="transition: opacity 0.5s ease;">
        <div class="message" style="padding-left: 20px;"><strong>Good!  </strong>${param.success}</div>
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