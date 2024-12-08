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

<style>
  #createModal .modal-dialog {
    max-width: 580px; /* Thay đổi độ rộng */
    width: 100%;
  }
</style>

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
                Tài khoản
            </h1>
            <div class="card mb-3">
                <div class="card-header">
                    Bộ lọc và Tìm kiếm
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-6">
                            <button class="btn btn-sm ml-1 btn-outline-success active">Tất cả</button>
                            <button class="btn btn-sm ml-1 btn-outline-success">Hoạt động</button>
                            <button class="btn btn-sm ml-1 btn-outline-success">Không hoạt động</button>
                        </div>
                        <div class="col-6">
                            <form id="form-search">
                                <div class="input-group">
                                    <input type="text" placeholder="Nhập từ khóa" name="keyword" class="form-control">
                                    <div class="input-group-append">
                                        <button class="btn btn-success" type="submit">Tìm</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card mb-3">
                <div class="card-header">
                    Danh sách
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-8">
                        </div>
                        <div class="col-4">
                            <c:if test="${role.permission == 'quan_ly_he_thong'}">
                                <button class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#createModal">Tạo tài khoản</button>
                            </c:if>
                        </div>
                    </div>
                    <table class="table table-hover table-sm">
                        <thead>
                            <tr>
                                <th>Tài khoản</th>
                                <th>Họ và tên</th>
                                <th>Trạng thái</th>
                                <th>Vai trò</th>
                                <th>Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="account" items="${accounts}">
                                <tr>
                                    <td>${account.username}</td>
                                    <td>${account.fullName}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${account.status == 'active'}">
                                                <button class="badge badge-success" disable>Hoạt động</button>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="badge badge-danger" disable>Dừng hoạt động</button>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${account.permission}</td>
                                    <td>
                                        <a href="#" class="btn btn-warning btn-sm ml-1">Sửa</a>
                                        <a href="#" class="btn btn-danger btn-sm ml-1">Xóa</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="createrModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="createrModalLabel">Tạo tài khoản</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">X</button>
                        </div>
                        <div class="modal-body">
                            <form action="/admin/account" method="POST">
                                <div class="form-group">
                                    <label for="fullName">Họ và tên</label>
                                    <input type="text" class="form-control" id="fullName" name="fullName">
                                </div>
                                <div class="form-group">
                                    <label for="username">Tài khoản</label>
                                    <input type="text" class="form-control" id="username" name="username">
                                </div>
                                <div class="form-group">
                                    <label for="password">Mật khẩu</label>
                                    <input type="text" class="form-control" id="password" name="password">
                                </div>
                                <div class="form-group form-check form-check-inline">
                                    <input type="radio" class="form-check-input" id="quan_ly_he_thong" name="role" value="quan_ly_he_thong" checked>
                                    <label for="quan_ly_he_thong" class="form-check-label">Quản lý hệ thống</label>
                                </div>
                                <div class="form-group form-check form-check-inline">
                                    <input type="radio" class="form-check-input" id="quan_ly_bai_viet" name="role" value="quan_ly_bai_viet">
                                    <label for="quan_ly_bai_viet" class="form-check-label">Quản lý bài viết</label>
                                </div>
                                <div class="form-group form-check form-check-inline">
                                    <input type="radio" class="form-check-input" id="quan_ly_nguoi_dung" name="role" value="quan_ly_nguoi_dung">
                                    <label for="quan_ly_nguoi_dung" class="form-check-label">Quản lý người dùng</label>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary">Thêm</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>