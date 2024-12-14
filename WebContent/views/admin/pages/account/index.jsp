<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Quản lý tài khoản</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/views/admin/assets/css/style.css">
</head>

<style>
    #createModal .modal-dialog {
    max-width: 580px;
    width: 100%;
    }
    #editModal .modal-dialog {
      max-width: 580px;
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
                    Tìm kiếm
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-6">
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
                                <tr user-id="${account.userID}">
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
                                        <i class="fa-solid fa-pen edit-btn" data-bs-toggle="modal" data-bs-target="#editModal" style="cursor: pointer; color: rgb(90,87,87); margin-left: 20px;"></i>
                                        <c:if test="${account.permission != 'Quản lý hệ thống'}">
                                            <i class="fa-solid fa-trash delete-btn" data-bs-toggle="modal" data-bs-target="#deleteModal" style="cursor: pointer; color: rgb(90,87,87); margin-left: 5px;"></i>
                                        </c:if>
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
                                    <input type="password" class="form-control" id="password" name="password">
                                </div>
                                <div class="form-group">
                                    <label for="role">Vai trò</label><br>
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
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary">Thêm</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editModalLabel">Cập nhật</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">X</button>
                        </div>
                        <div class="modal-body">
                            <form action="/admin/account/update" method="POST">
                                <div class="form-group">
                                    <label for="fullName">Họ và tên</label>
                                    <input type="text" class="form-control" id="fullName1" name="fullName">
                                </div>
                                <div class="form-group">
                                    <label for="username">Tài khoản</label>
                                    <input type="text" class="form-control" id="username1" name="username">
                                </div>
                                <div class="form-group">
                                    <label for="password">Mật khẩu</label>
                                    <input type="password" class="form-control" id="password1" name="password">
                                </div>
                                <div class="form-group">
                                    <label for="role">Vai trò</label><br>
                                    <div class="form-group form-check form-check-inline">
                                        <input type="radio" class="form-check-input" id="quan_ly_he_thong1" name="role" value="quan_ly_he_thong">
                                        <label for="quan_ly_he_thong" class="form-check-label">Quản lý hệ thống</label>
                                    </div>
                                    <div class="form-group form-check form-check-inline">
                                        <input type="radio" class="form-check-input" id="quan_ly_bai_viet1" name="role" value="quan_ly_bai_viet">
                                        <label for="quan_ly_bai_viet" class="form-check-label">Quản lý bài viết</label>
                                    </div>
                                    <div class="form-group form-check form-check-inline">
                                        <input type="radio" class="form-check-input" id="quan_ly_nguoi_dung1" name="role" value="quan_ly_nguoi_dung">
                                        <label for="quan_ly_nguoi_dung" class="form-check-label">Quản lý người dùng</label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="status">Trạng thái</label><br>
                                    <div class="form-group form-check form-check-inline">
                                        <input type="radio" class="form-check-input" id="active1" name="status" value="active">
                                        <label for="active" class="form-check-label">Hoạt động</label>
                                    </div>
                                    <div class="form-group form-check form-check-inline">
                                        <input type="radio" class="form-check-input" id="inactive1" name="status" value="inactive">
                                        <label for="inactive" class="form-check-label">Không hoạt động</label>
                                    </div>
                                </div>
                                <input type="text" hidden name="userID" id="userID">
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary">Cập nhật</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteModalLabel">Xác Nhận Xóa</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">X</button>
                        </div>
                        <div class="modal-body">
                            Bạn có chắc chắn muốn xóa?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                            <button type="button" class="btn btn-danger" onclick="confirmDelete()" data-bs-dismiss="modal">Xóa</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="/views/admin/assets/js/ActionAccount.js"></script>
    <script src="/views/admin/assets/js/DeleteAccountUser.js"></script>
</body>

</html>