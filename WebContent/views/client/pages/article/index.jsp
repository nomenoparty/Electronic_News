<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

    <div class="content-main">
        <div class="article-title" id="article-title">
            ${article.title}
        </div>

        <div class="article-content" id="article-content">
            ${article.content}
        </div>


        <div class="comment-main">
            <div class="comments-header">
                Ý kiến
                <span id="comment-count">
                    (20)
                </span>
            </div>
            <div class="comment-input">
                <input id="comment-input" placeholder="Chia sẻ ý kiến của bạn" type="text" />
                <i class="far fa-smile" id="emoji-picker"></i>
            </div>


            <div class="tabs">

                <div class="tab" data-tab="newest" id="tab-newest">
                    Mới nhất
                </div>
            </div>

            <div class="comment" id="comment-1" data-comment-id="1">
                <img alt="User avatar" class="user-avatar" height="40"
                    src="img/huydomic.jfif"
                    width="40" />

                <div class="comment-content">
                    <div class="username" id="username-anzman005">
                        anzman005
                    </div>
                    <div class="text" id="comment-text-1">
                        Vui quá mừng cho người lao động... và hi vọng mình cũng sẽ có thưởng cao...
                    </div>
                    <div class="actions">
                        <i class="far fa-thumbs-up" id="like-button-1" onclick="incrementLike(this)"></i>
                        <span class="like-count" id="like-count-1">
                            54
                        </span>

                    </div>

                </div>
            </div>
            <div class="comment">
                <img alt="User avatar" height="40"
                    src="img/huydomic.jfif"
                    width="40" />
                <div class="comment-content">
                    <div class="username">
                        lvcty1
                    </div>
                    <div class="text">
                        Chúc mừng doanh nghiệp và người lao động
                    </div>
                    <div class="actions">
                        <i class="far fa-thumbs-up" onclick="incrementLike(this)">
                        </i>
                        <span class="like-count">
                            54
                        </span>



                    </div>

                </div>
            </div>
            <div class="comment">
                <img alt="User avatar" height="40"
                    src="img/huydomic.jfif"
                    width="40" />
                <div class="comment-content">
                    <div class="username">
                        Namhai Nguyen
                    </div>
                    <div class="text">
                        Trung bình mỗi lao động được thưởng tết hơn 15 triệu khá cao so với mặt bằng chung
                    </div>
                    <div class="actions">
                        <i class="far fa-thumbs-up" onclick="incrementLike(this)">
                        </i>
                        <span class="like-count">
                            33
                        </span>

                    </div>

                </div>

            </div>
        </div>

    </div>

    <script type="module" src="/views/client/assets/js/home.js"></script>
</body>

</html>