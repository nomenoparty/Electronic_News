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

        if (path.equals("/login")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/home");
            dispatcher.forward(req, resp);
        } else if (path.equals("/register")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/home");
            dispatcher.forward(req, resp);
        } else if (path.equals("/logout")) {
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

            req.getSession().setAttribute("loginError", "Tài khoản hoặc mật khẩu không đúng");
            resp.sendRedirect(referer != null ? referer : "/login");
            return;
        }


        req.getSession().setAttribute("user", userModel);
        HttpSession session = req.getSession();
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("userModel", userModel);


        Cookie cookie = new Cookie("userToken", userModel.getTokenUser());
        cookie.setPath("/");
        cookie.setMaxAge(1 * 60 * 60); // 1 giờ
        resp.addCookie(cookie);


        if (userModel.getRoleID() == 1) {
            resp.sendRedirect(referer != null ? referer : "/login");
//            resp.sendRedirect("/login");
        } else {

            resp.sendRedirect("/dashboard");
        }
    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fullname = req.getParameter("fullname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm-password");


        if (!password.equals(confirmPassword)) {
            req.getSession().setAttribute("registerError", "Mật khẩu không khớp");
            resp.sendRedirect("/register");
            return;
        }

        UserModel userModel = new UserModel();
        userModel.setFullName(fullname);
        userModel.setUsername(username);
        userModel.setPassword(password);
        userModel.setPermission("client");

        boolean registered = userService.register(userModel);

        if (!registered) {

            req.getSession().setAttribute("registerError", "Tên đăng nhập đã tồn tại");
            resp.sendRedirect("/register");
            return;
        }


        req.getSession().setAttribute("registerSuccess", "Đăng ký thành công. Vui lòng đăng nhập.");
        HttpSession session = req.getSession();
        session.setAttribute("fullname", fullname);
        resp.sendRedirect("/login");
    }


    private void handleLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession(false); // Lấy session hiện tại, không tạo mới
        if (session != null) {

            session.invalidate();
        }

        String referer = req.getHeader("Referer");
            resp.sendRedirect(referer != null ? referer : "/home");


        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userToken".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                }
            }
        }


//        resp.sendRedirect("/home");
    }
}