<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light shadow">
    <div class="container d-flex justify-content-between align-items-center">

        <a class="navbar-brand text-success logo h1 align-self-center" href="/">
            SHOPPING
        </a>

        <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse"
                data-bs-target="#templatemo_main_nav" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="align-self-center collapse navbar-collapse flex-fill  d-lg-flex justify-content-lg-between"
             id="templatemo_main_nav">
            <div class="flex-fill">
                <ul class="nav navbar-nav d-flex justify-content-between mx-lg-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/">
                            <h5>Home</h5>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/products">
                            <h5>Shop</h5>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/carts" id="cartLink">
                            <h5>Cart</h5>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin">
                            <h5>Management</h5>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="navbar align-self-center d-flex justify-content-between">
                <!-- Search Input for Mobile -->
                <div class="d-lg-none flex-fill mt-3 mb-4 col-7 col-sm-auto pr-3">
                    <div class="input-group">
                        <input type="text" class="form-control" id="inputMobileSearch" placeholder="Search ...">
                        <div class="input-group-text">
                            <i class="fa fa-fw fa-search"></i>
                        </div>
                    </div>
                </div>

                <!-- Search Icon for Desktop -->
                <a class="nav-icon d-none d-lg-inline" href="#" data-bs-toggle="modal"
                   data-bs-target="#templatemo_search">
                    <i class="fa fa-fw fa-search text-dark mr-2"></i>
                </a>

                <!-- Cart Icon -->
                <a class="nav-icon position-relative text-decoration-none" href="/carts">
                    <i class="fa fa-fw fa-cart-arrow-down text-dark mr-1"></i>
                    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-light text-dark">
                        ${sessionScope.numberCartItem}
                    </span>
                </a>

                <!-- User Info Icon -->
                <!-- User Info Icon with Dropdown -->
                <div class="nav-icon position-relative dropdown">
                    <a class="text-decoration-none" href="#" id="userDropdown" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <i class="fa fa-fw fa-user text-dark mr-3"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end p-2" aria-labelledby="userDropdown" style="margin-top: 16px;">
                        <li>
                            <a class="dropdown-item d-flex align-items-center" href="/accounts/info">
                                <span class="icon mdi mdi-face me-2"></span>Account
                            </a>
                        </li>
                        <li>
                            <form class="w-100" method="post" action="/logout">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button class="btn btn-primary w-100" type="submit">
                                    <span class="icon mdi mdi-power me-2"></span> Logout
                                </button>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

    </div>
</nav>
