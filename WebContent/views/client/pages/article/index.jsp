<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.CommentModel" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${article.title}</title>
    <link rel="stylesheet" href="/views/client/assets/css/home.css">
    <link rel="stylesheet" href="/views/client/assets/css/content.css">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet" />
</head>
<style>
    .article-content {
        display: flex;
        flex-direction: column;
    }
    figure {
        text-align: center;
        margin: 20px 0;
    }
    img {
        width: 100%; /* Đảm bảo hình ảnh không vượt quá chiều rộng của container */
        height: auto; /* Giữ tỉ lệ hình ảnh */
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
            <div class="overlay" id="overlay"></div>
            <div class="modal" id="modal">
                <div class="container1 login">
                    <div class="toggle">
                        <div class="form-container">
                            <form action="/login" method="POST" class="form" id="form-1">
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
                        <form action="/register" method="POST" id="form-2">
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

    <div class="content-main">
        <div class="article-title" id="article-title">
            ${article.title}
        </div>

        <div class="article-content" id="article-content">
            ${article.content}
        </div>

    </div>


<hr>
    <form action="/article/${article.slug}" method="post">
            <textarea name="comment" rows="3" cols="100" placeholder="Add a comment" style= "margin-left:50px;"></textarea><br>
            <input type="hidden" name="articleID" value="${article.articleID}"">
            <input type="hidden" name="userID" value="${currentUser.userID}"> <!-- Example userID -->
            <button type="submit" style= "margin-left:50px;">Bình luận</button>
        </form>




        <hr>

          <ul>
            <%
            ArrayList<CommentModel> comments = (ArrayList<CommentModel>) request.getAttribute("comments");
            if (comments != null) {
                for (CommentModel comment : comments) {
        %>

            <div class="view_comment" style = " display:flex;">
              <div class="header-left">
                          <img alt="Digital Newspaper" src="/views/client/assets/img/newspaper.jpg" style="border-radius:50px; heigh:50px; width:50px;" />
                      </div>
                <p style= "margin-left:10px;"><strong><%= comment.getFullName() %></strong></p>
                <p style= "margin-left:10px;"><%= comment.getContent() %></p>

            </div>
             <p style="margin-left:1000px;"><i> <%= comment.getPostAt() %> </i></p>
                            <hr>
        <%
                }
            }
        %>
        </ul>


    <script type="module" src="/views/client/assets/js/home.js"></script>
</body>

</html>