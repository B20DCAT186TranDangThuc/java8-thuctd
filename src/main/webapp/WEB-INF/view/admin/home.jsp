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
<jsp:include page="layout/header.jsp" />
<jsp:include page="layout/left-sidebar.jsp" />

<div class="be-content" style="margin-top: 60px;">
    Day la trang admin
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