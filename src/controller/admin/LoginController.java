package controller.admin;

import model.UserModel;
import service.admin.AdminService;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/login" })
public class LoginController extends HttpServlet {
	private AdminService adminService = new AdminService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispactcher = req.getRequestDispatcher("/views/admin/pages/user/login.jsp");
		dispactcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

		UserModel userModel = new UserModel();

		userModel.setUsername(username);
		userModel.setPassword(password);

		UserModel admin = adminService.loginAdmin(userModel);

		String referer = req.getHeader("Referer");

		if (admin == null) {
			System.out.println("Dang nhap that bai");
			if (referer != null) {
				resp.sendRedirect(referer);
			} else {
				resp.sendRedirect("/admin/login");
			}
			return;
		}else{
			System.out.println("Dang nhap thanh cong");

			Cookie cookie = new Cookie("token", admin.getTokenUser());

			cookie.setPath("/admin");
			cookie.setMaxAge(1 * 60 * 60); // set coookie trong 1h

			resp.addCookie(cookie);

			resp.sendRedirect("/admin/dashboard");
		}
	}
}
