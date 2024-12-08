<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Đăng nhập hệ thống</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/views/admin/assets/css/login.css">
</head>

<body>

    <div class="container1 login">
        <div class="toggle">
            <div class="form-container">
                <form method="POST" class="form" id="form-1">
                    <i class="fa-solid fa-delete-left">X</i>
                    <h2>Đăng nhập</h2>
                    <div id="message"></div>
                    <div class="form-group">
                        <input id="username" name="username" type="text" placeholder="Tài khoản" class="form-control"
                            required>
                        <span class="form-message"></span>
                    </div>
                    <div class="form-group">
                        <input id="password" name="password" type="password" placeholder="Mật khẩu" class="form-control"
                            required>
                        <span class="form-message"></span>
                    </div>
                    <button class="form-submit" type="submit">Đăng nhập</button>
                </form>

            </div>
        </div>
    </div>
    <div class="container1 signup" style="display: none;">
        <div class="form-container-signup">
            <form method="POST" id="form-2">
                <i class="fa-solid fa-delete-left1">X</i>
                <h2>Đăng ký</h2>
                <div id="message2"></div>
                <div class="form-group">
                    <input id="email2" name="email" type="email" placeholder="Email" class="form-control" required>
                    <span class="form-message"></span>
                </div>
                <div class="form-group">
                    <input id="username2" name="username" type="text" placeholder="Tài khoản" class="form-control"
                        required>
                    <span class="form-message"></span>
                </div>
                <div class="form-group">
                    <input id="password2" name="password" type="password" placeholder="Mật khẩu" class="form-control"
                        required>
                    <span class="form-message"></span>
                </div>
                <div class="form-group">
                    <input id="confirm-password" name="confirm-password" type="password" placeholder="Nhập lại mật khẩu"
                        class="form-control" required>
                    <span class="form-message"></span>
                </div>
                <button class="form-submit" type="submit">Đăng ký</button>
                <div class="btn-login" role="button">Đăng nhập</div>
            </form>

        </div>
    </div>
</body>

</html>