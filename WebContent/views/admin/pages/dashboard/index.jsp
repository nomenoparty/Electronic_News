<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Tổng quan</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/views/admin/assets/css/style.css">
</head>

<body>
    <header class="header">
        <div class="container-fluid">
            <div class="row align-items-center">
                <div class="col-3">
                    <div class="inner-logo">
                        <a href="/admin/dashboard">ADMIN</a>
                    </div>
                </div>
                <div class="col-9">
                    <div class="text-right">
                        <a href="/admin/my-account" class="btn btn-primary btn-sm mr-2">${user.fullName}</a>
                        <a href="/admin/logout" class="btn btn-danger btn-sm">Đăng xuất</a>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <div class="body">
        <div class="sider">
            <div class="inner-menu">
                <ul>
                    <li>
                        <a href="/admin/dashboard">Tổng quan</a>
                        <c:if test="${role.permission == 'quan_ly_he_thong' || role.permission == 'quan_ly_bai_viet'}">
                            <a href="/admin/category">Danh mục</a>
                            <a href="/admin/article">Bài viết</a>
                        </c:if>
                        <c:if test="${role.permission == 'quan_ly_he_thong' || role.permission == 'quan_ly_nguoi_dung'}">
                            <a href="/admin/account">Tài khoản</a>
                        </c:if>
                    </li>
                </ul>
            </div>
        </div>

        <div class="main">
            <h1 class="mb-4">
                Tổng quan
            </h1>
            <div class="row mt-4">
                <div class="col-6">
                    <div class="card mb-4">
                        <div class="card-header">
                            Danh mục
                        </div>
                        <div class="card-body">
                            <p>Số lượng: <b>${categorySize}</b></p>
                        </div>
                    </div>
                </div>
                <div class="col-6">
                    <div class="card mb-4">
                        <div class="card-header">
                            Bài viết
                        </div>
                        <div class="card-body">
                            <p>Số lượng: <b>${articleSize}</b></p>
                        </div>
                    </div>
                </div>
                <div class="col-6">
                    <div class="card mb-4">
                        <div class="card-header">
                            Tài khoản admin
                        </div>
                        <div class="card-body">
                            <p>Số lượng: <b>${adminActiveSize + adminInactiveSize}</b></p>
                            <p>Hoạt động: <b>${adminActiveSize}</b></p>
                            <p>Dừng hoạt động: <b>${adminInactiveSize}</b></p>
                        </div>
                    </div>
                </div>
                <div class="col-6">
                    <div class="card mb-4">
                        <div class="card-header">
                            Tài khoản client
                        </div>
                        <div class="card-body">
                            <p>Số lượng: <b>${clientActiveSize + clientInactiveSize}</b></p>
                            <p>Hoạt động: <b>${clientActiveSize}</b></p>
                            <p>Dừng hoạt động: <b>${clientInactiveSize}</b></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>