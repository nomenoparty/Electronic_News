package controller.admin;

import model.UserModel;
import service.admin.AdminService;
import service.admin.CheckRoleService;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/article" })
public class ArticleController extends HttpServlet {
    private AdminService adminService = new AdminService();
    private CheckRoleService checkRoleService = new CheckRoleService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel user = (UserModel) req.getAttribute("user");

        if(!checkRoleService.checkRoleUser(user.getUserID(), "quan_ly_he_thong") && !checkRoleService.checkRoleUser(user.getUserID(), "quan_ly_bai_viet")){
            resp.sendRedirect("/admin/dashboard");
            return;
        }

        RequestDispatcher dispactcher = req.getRequestDispatcher("/views/admin/pages/article/index.jsp");
        dispactcher.forward(req, resp);
    }
}
