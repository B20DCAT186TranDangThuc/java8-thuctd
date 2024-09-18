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
<div class="be-content">
    <div class="page-head" style="margin-top: 60px;" style="margin-top: 60px;">
        <h2 class="page-head-title">Account</h2>
        <nav aria-label="breadcrumb mt-2" role="navigation">
            <ol class="breadcrumb page-head-nav">
                <li class="breadcrumb-item"><a href="/admin/accounts">Account List</a></li>
                <li class="breadcrumb-item active">Update</li>
            </ol>
        </nav>
    </div>

    <div class="container">
        <form:form action="/admin/accounts/update" method="post" modelAttribute="accountForm">
            <form:hidden path="id" />
            <c:set var="errorUsername">
                <form:errors path="username" cssClass="text-danger"/>
            </c:set>

            <c:set var="errorPassword">
                <form:errors path="password" cssClass="text-danger"/>
            </c:set>

            <c:if test="${not empty param.error}">
                <div class="alert alert-danger" role="alert"
                     style="width: 50%;
                            margin: 0 auto;
                            margin-bottom: 20px;
                            text-align: center;"
                >
                        ${param.error}
                </div>
            </c:if>
            <div class="row justify-content-center">

                <div class="col-md-6">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <form:input type="text" class="form-control ${not empty errorUsername ? 'is-invalid' : ''}"
                                    id="username"
                                    placeholder="Enter Username" path="username"/>
                            ${errorUsername}
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <form:input type="password" class="form-control ${not empty errorPassword ? 'is-invalid' : ''}"
                                    id="password"
                                    placeholder="Enter Password" path="password"/>
                            ${errorPassword}
                    </div>
                    <div class="form-group">
                        <label for="role">Role:</label>
                        <form:select class="form-control" id="role" path="role">
                            <form:option value="ADMIN">Admin</form:option>
                            <form:option value="EMPLOYEE">EMPLOYEE</form:option>
                            <form:option value="USER">USER</form:option>
                        </form:select>
                    </div>

                </div>
            </div>


            <div class="row text-center">
                <div class="col-md-12">
                    <button type="submit" class="btn btn-primary">Submit</button>
                    <a type="button" class="btn btn-secondary" href="/admin/accounts">Cancel</a>
                </div>
            </div>
        </form:form>
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