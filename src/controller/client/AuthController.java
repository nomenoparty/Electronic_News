package controller.client;

import model.UserModel;
import service.user.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/register","/logout"})
public class AuthController extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        if (path.equals("/logout")) {
            System.out.println("out");
            handleLogout(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String path = req.getServletPath();

        if (path.equals("/login")) {
            handleLogin(req, resp);
        } else if (path.equals("/register")) {
            handleRegister(req, resp);
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserModel userModel = userService.login(username, password);
        String referer = req.getHeader("Referer");

        if (userModel == null) {
            System.out.println("Dang nhap that bai");
            if (referer != null) {
                resp.sendRedirect(referer);
            } else {
                resp.sendRedirect("/admin/login");
            }
            return;
        }

        Cookie cookie = new Cookie("userToken", userModel.getTokenUser());
        cookie.setPath("/");
        cookie.setMaxAge(1 * 60 * 60); // 1 giờ
        resp.addCookie(cookie);

        resp.sendRedirect(referer);
    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fullname = req.getParameter("fullname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm-password");

        String referer = req.getHeader("Referer");

        if (!password.equals(confirmPassword)) {
            req.getSession().setAttribute("registerError", "Mật khẩu không khớp");
            resp.sendRedirect(referer);
            return;
        }

        UserModel userModel = new UserModel();
        userModel.setFullName(fullname);
        userModel.setUsername(username);
        userModel.setPassword(password);
        userModel.setPermission("user");

        boolean registered = userService.register(userModel);

        if (!registered) {
            req.getSession().setAttribute("registerError", "Tên đăng nhập đã tồn tại");
            resp.sendRedirect(referer);
            return;
        }

        Cookie cookie = new Cookie("userToken", userModel.getTokenUser());
        cookie.setPath("/");
        cookie.setMaxAge(1 * 60 * 60); // 1 giờ
        resp.addCookie(cookie);

        resp.sendRedirect(referer);
    }

    private void handleLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userToken")) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);

                    String referer = req.getHeader("Referer");

                    System.out.println("Dang xuat thanh cong");
                    resp.sendRedirect(referer);

                    return;
                }
            }
        }
    }
}