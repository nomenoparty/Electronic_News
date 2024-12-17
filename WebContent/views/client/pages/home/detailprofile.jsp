<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <title>
        VNExpress
    </title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="/views/client/assets/css/home.css">
    <link rel="stylesheet" href="/views/client/assets/css/profile.css">
</head>

<style>
    .link-text {
        text-decoration: none;
        color: black;
    }
</style>

<body>

    <div class="header">
            <div class="header-left">
                <img alt="Digital Newspaper" src="/views/client/assets/img/newspaper.jpg" />
                <h1>Digital Newspaper</h1>
            </div>
            <div class="header-right">
                <div class="search" style = >
                    <input placeholder="Tìm kiếm thông tin" type="text" id="search" />
                    <i class="fas fa-search" >
                    </i>
                </div>
                <c:choose>

                    <c:when test="${not empty user}">



                        <div class="dropdown">
                            <i class="fas fa-user" id="person" style= "margin-right:120px;" ></i>
                            <div class="dropdown-content">
                                <a href="/views/client/pages/home/detailprofile.jsp" id="thongtinchung">Thông tin chung</a>

                                 <a href="/logout">Đăng xuất</a>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>

                        <button class="btn_login" id="btn_login"
                         style="background-color: black; color: white; border: none; padding: 8px 15px; text-align: center; font-size: 16px; border-radius: 5px; cursor: pointer; margin-right:30px; "

                        >Đăng nhập</button>
                    </c:otherwise>
                </c:choose>





                </div>

            </div>
        </div>



    <div class="container-profile">
            <div class="sidebar">
                <img alt="User profile picture" height="80"
                    src="/views/client/assets/img/newspaper.jpg""
                    width="80" />

                <ul>
                    <li><a href="/views/client/pages/home/detailprofile.jsp"> <i class="fas fa-info-circle"></i> Thông tin chung</a></li>
                    <li><a href="/"> <i class="fas fa-sign-out-alt"></i> Thoát</a></li>
                </ul>
            </div>
            <div class="content-profile">
                <h1>
                    Thông tin tài khoản
                </h1>



                <div class="info-section">
                    <p>
                       Username
                    </p>
                    <p id="email">
                          <%= session.getAttribute("username") %>
                    </p>

                </div>
                <div class="info-section">
                    <p>
                        Mật khẩu
                    </p>
                    <p id="password">
                       <%= session.getAttribute("password") %>
                    </p>

                </div>

            </div>


        </div>



    <script type="module" src="/views/client/assets/js/home.js"></script>
</body>

</html>