<%@page pageEncoding="UTF-8" contentType="text/html; ISO-8859-1" language="java" %>
<%--mã hóa dạng utf8--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark" aria-label="Eighth navbar example">
    <div class="container">
        <a class="navbar-brand" href="/product/customer">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07"
                aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExample07">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/home">Product</a>
                </li>
                <%--                <li class="nav-item">--%>
                <%--                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>--%>
                <%--                </li>--%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="dropdown07" data-bs-toggle="dropdown"
                       aria-expanded="false">Category</a>
                    <ul class="dropdown-menu" aria-labelledby="dropdown07">
                        <li><a class="dropdown-item" href="/product/customerPage/1?category=Công sở">Office</a></li>
                        <li><a class="dropdown-item" href="/product/customerPage/1?category=Thể thao">Sport</a></li>
                    </ul>
                </li>
                <sec:authorize access="hasRole('ADMIN')">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="/user" id="admin" data-bs-toggle="dropdown"
                           aria-expanded="false">Admin</a>
                        <ul class="dropdown-menu" aria-labelledby="admin">
                            <li><a class="dropdown-item" href="/user">List User</a></li>
                            <li><a class="dropdown-item" href="/product/admin">List Product</a></li>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>

            <div class="mb-4 mx-4">
            <sec:authorize access="isAuthenticated()">
                <a href="<c:url value="/logout" />">Logout</a>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <a href="<c:url value="/login" />">Login</a>
            </sec:authorize>
            </div>
            <form>
                <input class="form-control" type="text" placeholder="Tìm kiếm" aria-label="Tìm kiếm">
            </form>
        </div>
    </div>
</nav>
