<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Quản lý danh mục</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
                Danh mục
            </h1>
            <div class="card mb-3">
                <div class="card-header">
                    Danh sách
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-8">
                        </div>
                        <div class="col-4">
                            <button class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#createModal" onclick="loadCategories()">Tạo danh mục</button>
                        </div>
                    </div>
                    <table class="table table-hover table-sm">
                        <thead>
                            <tr>
                                <th>Tiêu đề</th>
                                <th>Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="entry" items="${categories}">
                                <tr category-id="${entry.key.categoryID}">
                                    <td>${entry.key.title}</td>
                                    <td>
                                        <i class="fa-solid fa-pen edit-btn" data-bs-toggle="modal" data-bs-target="#editModal" style="cursor: pointer; color: rgb(90,87,87); margin-left: 20px;"></i>
                                        <i class="fa-solid fa-trash delete-btn" data-bs-toggle="modal" data-bs-target="#deleteModal" style="cursor: pointer; color: rgb(90,87,87); margin-left: 5px;"></i>
                                    </td>
                                </tr>
                                <c:forEach var="item" items="${entry.value}">
                                    <tr category-id="${item.categoryID}">
                                        <td>----${item.title}</td>
                                        <td>
                                            <i class="fa-solid fa-pen edit-btn" data-bs-toggle="modal" data-bs-target="#editModal" style="cursor: pointer; color: rgb(90,87,87); margin-left: 20px;"></i>
                                            <i class="fa-solid fa-trash delete-btn" data-bs-toggle="modal" data-bs-target="#deleteModal" style="cursor: pointer; color: rgb(90,87,87); margin-left: 5px;"></i>
                                        </td>
                                    </tr>
                            </c:forEach>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="createrModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="createrModalLabel">Tạo danh mục</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">X</button>
                        </div>
                        <div class="modal-body">
                            <form action="/admin/category" method="POST">
                                <div class="form-group">
                                    <label for="title">Tiêu đề</label>
                                    <input type="text" class="form-control" id="title" name="title">
                                </div>
                                <div class="form-group">
                                    <label for="parent_id">Danh mục cha</label>
                                    <select name="parent_id" id="parent_id" class="form-control">
                                        <option value="">-- Chọn danh mục cha --</option>
                                    </select>
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
                            <form action="/admin/category/update" method="POST">
                                <div class="form-group">
                                    <label for="title">Tiêu đề</label>
                                    <input type="text" class="form-control" id="title" name="title">
                                </div>
                                <div class="form-group">
                                    <label for="parent_id">Danh mục cha</label>
                                    <select name="parent_id" id="parent_id" class="form-control">
                                        <option value="">-- Chọn danh mục cha --</option>
                                    </select>
                                </div>
                                <input type="text" hidden name="categoryID" id="categoryID">
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
                            Có thể sẽ xóa cả danh mục con?
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
    <script src="/views/admin/assets/js/CreateCategory.js"></script>
    <script src="/views/admin/assets/js/UpdateCategory.js"></script>
    <script src="/views/admin/assets/js/DeleteCategory.js"></script>
</body>

</html>