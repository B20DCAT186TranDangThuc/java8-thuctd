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

    <link rel="stylesheet" href="/css/app.css" type="text/css">

</head>

<body>
<jsp:include page="../layout/header.jsp"/>

<jsp:include page="../layout/left-sidebar.jsp"/>
<div class="be-content">
    <div class="page-head" style="margin-top: 60px;" style="margin-top: 60px;">
        <h2 class="page-head-title">Product Management</h2>
        <nav aria-label="breadcrumb mt-2" role="navigation">
            <ol class="breadcrumb page-head-nav">
                <li class="breadcrumb-item"><a href="/admin/products">Product List</a></li>
                <li class="breadcrumb-item active">Update</li>
            </ol>
        </nav>
    </div>

    <div class="container">
        <form:form action="/admin/products/update" method="post"
                   enctype="multipart/form-data" modelAttribute="newProduct">
        <form:input type="hidden" path="id"/>
        <c:set var="errorCode">
            <form:errors path="code" cssClass="text-danger"/>
        </c:set>
        <c:set var="errorName">
            <form:errors path="name" cssClass="text-danger"/>
        </c:set>
        <c:set var="errorPrice">
            <form:errors path="price" cssClass="text-danger"/>
        </c:set>
        <c:if test="${not empty param.error}">
            <div class="alert alert-danger" role="alert"
                 style="width: 50%;
                    margin: 0 auto;
                    margin-bottom: 20px;
                    text-align: center;">
                    ${param.error}
            </div>
        </c:if>

        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="code">Code</label>
                    <form:input type="text" class="form-control ${not empty errorCode ? 'is-invalid' : ''}"
                                id="code"
                                placeholder="Enter Code" path="code" readonly="true"/>
                        ${errorCode}
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="name">Name</label>
                    <form:input type="text" class="form-control ${not empty errorName ? 'is-invalid' : ''}"
                                id="name"
                                placeholder="Enter Name" path="name"/>
                        ${errorName}
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="price">Price</label>
                    <form:input type="number" class="form-control ${not empty errorPrice ? 'is-invalid' : ''}"
                                id="price"
                                placeholder="Enter Price" path="price"/>
                        ${errorPrice}
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <div class="form-group mb-3">
                        <label for="avatarFile" class="form-label">Image:</label>
                        <form:input class="form-control" type="file" id="avatarFile"
                                    accept=".png, .jpg, .jpeg" onchange="previewImage(event)" path="image"/>
                    </div>
                    <div class="col-12 mb-3">
                        <img style="max-height: 250px; max-width: 250px;" alt="avatar preview"
                             id="avatarPreview" src="${pageContext.request.contextPath}/uploads/${newProduct.id}"/>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="description">Description</label>
                    <form:textarea class="form-control"
                                   id="description"
                                   placeholder="Enter Description" path="description"></form:textarea>
                </div>
            </div>

        </div>
    </div>

    <div class="row text-center">
        <div class="col-md-12">
            <button type="submit" class="btn btn-primary">Submit</button>
            <a type="button" class="btn btn-secondary" href="/admin/products">Cancel</a>
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

    function previewImage(event) {
        const preview = document.getElementById('avatarPreview');
        const file = event.target.files[0];
        const reader = new FileReader();

        reader.onload = function (e) {
            preview.src = e.target.result;
            preview.style.display = 'block';
        };

        if (file) {
            reader.readAsDataURL(file);
        }
    }
</script>

</html>