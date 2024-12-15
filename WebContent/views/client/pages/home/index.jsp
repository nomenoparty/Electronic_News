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
            <div class="search">
                <input placeholder="Tìm kiếm thông tin" type="text" id="search" />
                <i class="fas fa-search">
                </i>
            </div>
            <button class="btn_login" id="btn_login">Đăng nhập</button>
            <div class="overlay" id="overlay"></div>
            <div class="modal" id="modal">
                <div class="container1 login">
                    <div class="toggle">
                        <div class="form-container">
                            <form action="" method="POST" class="form" id="form-1">
                                <i class="fa-solid fa-delete-left" id="close-btn" style="cursor: pointer;">X</i>
                                <h2>Đăng nhập</h2>
                                <div id="message"></div>
                                <div class="form-group">
                                    <input id="username" name="username" type="text" placeholder="Tài khoản"
                                        class="form-control" required>
                                    <span class="form-message"></span>
                                </div>
                                <div class="form-group">
                                    <input id="password" name="password" type="password" placeholder="Mật khẩu"
                                        class="form-control" required>
                                    <span class="form-message"></span>
                                </div>
                                <button class="form-submit" type="submit">Đăng nhập</button>
                                <div class="btn-signup" role="button">Đăng ký</div>
                            </form>
                        </div>
                    </div>

                </div>
                <div class="container1 signup" style="display: none;">
                    <div class="form-container-signup">
                        <form action="" method="POST" id="form-2">
                            <i class="fa-solid fa-delete-left1" id="close-btn1" style="cursor: pointer;">X</i>
                            <h2>Đăng ký</h2>
                            <div id="message2"></div>
                            <div class="form-group">
                                <input id="fullname" name="fullname" type="fullname" placeholder="fullname"
                                    class="form-control" required>
                                <span class="form-message"></span>
                            </div>
                            <div class="form-group">
                                <input id="username2" name="username" type="text" placeholder="Tài khoản"
                                    class="form-control" required>
                                <span class="form-message"></span>
                            </div>
                            <div class="form-group">
                                <input id="password2" name="password" type="password" placeholder="Mật khẩu"
                                    class="form-control" required>
                                <span class="form-message"></span>
                            </div>
                            <div class="form-group">
                                <input id="confirm-password" name="confirm-password" type="password"
                                    placeholder="Nhập lại mật khẩu" class="form-control" required>
                                <span class="form-message"></span>
                            </div>
                            <button class="form-submit" type="submit">Đăng ký</button>
                            <div class="btn-login" role="button">Đăng nhập</div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <div class="nav">
        <div class="nav-left">
            <a href="/" id="home"><i class="fas fa-home">
                </i></a>
            <c:forEach var="entry" items="${categories}">
                <div class="dropdown">
                    <a href="/category/${entry.key.slug}">
                        ${entry.key.title}
                    </a>
                    <div class="dropdown-content">
                        <c:forEach var="item" items="${entry.value}">
                            <a href="/category/${item.slug}">${item.title}</a>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="container">
        <c:forEach var="article" items="${articles}" varStatus="status">
            <c:choose>
                <c:when test="${status.index == 0}">
                    <div class="news-item-main">
                        <a href="/article/${article.slug}">
                            <img alt="${article.title}"
                                height="200" src="${article.titleCategory}" id="img-main" width="300" />
                        </a>
                        <div class="news-content" id="news-content-main">
                            <a href="/article/${article.slug}" class="link-text">
                                <div class="news-title" id="news-title-main">
                                    ${article.title}
                                </div>
                            </a>
                            <a href="/article/${article.slug}" class="link-text">
                                <div class="news-description" id="news-description-main">
                                    ${article.content}
                                </div>
                            </a>
                            <%--<div class="news-footer">
                                <i class="fas fa-comment">
                                </i>
                                <span id="post-time">
                                    ${article.createdAt}
                                </span>
                            </div>--%>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="news-item-sub">
                        <a href="/article/${article.slug}">
                            <img alt="${article.title}" height="200" src="${article.titleCategory}" id="img-sub"
                                width="300" />
                        </a>
                        <div class="news-content" id="news-content-sub">
                            <a href="/article/${article.slug}" class="link-text">
                                <div class="news-title" id="news-title-sub">
                                    ${article.title}
                                </div>
                            </a>
                            <a href="/article/${article.slug}" class="link-text">
                                <div class="news-description" id="news-description-sub">
                                    ${article.content}
                                </div>
                            </a>
                        </div>
                        <%--<div class="news-footer">
                            <i class="fas fa-comment">
                            </i>
                            <span id="post-time">
                                ${article.createdAt}
                            </span>
                        </div>--%>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>

    <script type="module" src="/views/client/assets/js/home.js"></script>
</body>

</html>