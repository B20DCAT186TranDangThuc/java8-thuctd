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
            <h2 class="page-head-title">Account Management</h2>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb page-head-nav">
                    <li class="breadcrumb-item"><a href="/admin/accounts">Account List</a></li>
                    <li class="breadcrumb-item active">Account Detail</li>
                </ol>
            </nav>
        </div>

        <section class="vh-100">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center">
                    <div class="col-md-12 col-xl-6">

                        <div class="card" style="border-radius: 15px;">
                            <div class="card-body text-center">
                                <div class="mt-3 mb-6">
                                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava2-bg.webp"
                                         class="rounded-circle img-fluid" style="width: 100px;"/>
                                </div>
                                <h4 class="mb-2">${accountInfo.username}</h4>
                                <p class="text-muted mb-4">@ROLE <span class="mx-2">|</span> <a
                                        href="#!">${accountInfo.role}</a></p>

                                <a type="button" href="/admin/accounts/delete/${accountInfo.id}"
                                   class="btn btn-danger btn-rounded btn-lg">
                                    Delete Now
                                </a>
                                <div class="d-flex justify-content-between text-center mt-5 mb-2">
                                    <div>
                                        <p class="mb-2 h5">Create At</p>
                                        <p class="text-muted mb-0">${accountInfo.createAt}</p>
                                    </div>
                                    <div>
                                        <p class="mb-2 h5">Static</p>
                                        <p class="text-muted mb-0">
                                            <c:choose>
                                                <c:when test="${accountInfo.isDeleted()}">
                                                    DELETED
                                                </c:when>
                                                <c:otherwise>
                                                    ACTIVE
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                    </div>
                                    <div class="px-3">
                                        <p class="mb-2 h5">Update At</p>
                                        <p class="text-muted mb-0">${accountInfo.updateAt}</p>
                                    </div>

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