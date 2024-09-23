<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="/img/logo-fav.png">
    <title>Beagle</title>
    <link rel="stylesheet" type="text/css" href="/lib/perfect-scrollbar/css/perfect-scrollbar.css">
    <link rel="stylesheet" type="text/css"
          href="/lib/material-design-icons/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" type="text/css"
          href="/lib/datatables/datatables.net-bs4/css/dataTables.bootstrap4.css">
    <link rel="stylesheet" type="text/css"
          href="/lib/datatables/datatables.net-responsive-bs4/css/responsive.bootstrap4.min.css">
    <link rel="stylesheet" href="/css/app.css" type="text/css">
</head>
<body>
<jsp:include page="../layout/header.jsp"/>

<jsp:include page="../layout/left-sidebar.jsp"/>

<div class="be-content" style="margin-top: 60px;">
    <div class="main-content container-fluid">
        <div class="page-head">
            <h2 class="page-head-title">Product Management</h2>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb page-head-nav">
                    <li class="breadcrumb-item"><a href="/admin/products">Product List</a></li>
                    <li class="breadcrumb-item active">Product Detail</li>
                </ol>
            </nav>
        </div>

        <section style="background-color: #eee;">
            <div class="row">
                <div class="col-lg-4">
                    <div class="card mb-4">
                        <div class="card-body text-center">
                            <img src="${pageContext.request.contextPath}/uploads/${product.id}"
                                 alt="avatar"
                                 class="rounded-circle img-fluid" style="width: 150px;">
                            <h5 class="my-3">
                                <p>${product.name}</p>
                            </h5>
                            <div class="d-flex justify-content-between text-center mt-4">
                                <div>
                                    <p class="mb-2 h5">8471</p>
                                    <p class="text-muted mb-0">Đã bán</p>
                                </div>
                                <div class="px-3">
                                    <p class="mb-2 h5">${product.quantity}</p>
                                    <p class="text-muted mb-0">Còn</p>
                                </div>
                                <div>
                                    <p class="mb-2 h5">4751</p>
                                    <p class="text-muted mb-0">Trong Order</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8">
                    <div class="card mb-4">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Name</p>
                                </div>
                                <div class="col-sm-9">
                                    <p class="text-muted mb-0">${product.name}</p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Code</p>
                                </div>
                                <div class="col-sm-9">
                                    <p class="text-muted mb-0">${product.code}</p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Price</p>
                                </div>
                                <div class="col-sm-9">
                                    <p class="text-muted mb-0">
                                        <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="VND" />
                                    </p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Description</p>
                                </div>
                                <div class="col-sm-9">
                                    <p class="text-muted mb-0">${product.description}</p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Create At</p>
                                </div>
                                <div class="col-sm-9">
                                    <p class="text-muted mb-0">${product.createAt}</p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Update At</p>
                                </div>
                                <div class="col-sm-9">
                                    <p class="text-muted mb-0">${product.updateAt}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </div>
</div>


</body>
<script src="\lib\jquery\jquery.min.js" type="text/javascript"></script>
<script src="\lib\perfect-scrollbar\js\perfect-scrollbar.min.js" type="text/javascript"></script>
<script src="\lib\bootstrap\dist\js\bootstrap.bundle.min.js" type="text/javascript"></script>
<script src="\js\app.js" type="text/javascript"></script>
<script src="\lib\jquery.sparkline\jquery.sparkline.min.js" type="text/javascript"></script>
<script src="\lib\countup\countUp.min.js" type="text/javascript"></script>
<script src="\lib\jquery-ui\jquery-ui.min.js" type="text/javascript"></script>
<script src="\lib\jqvmap\jquery.vmap.min.js" type="text/javascript"></script>
<script src="\lib\jqvmap\maps\jquery.vmap.world.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        //-initialize the javascript
        App.init();
        App.dashboard();

    });

</script>

</html>