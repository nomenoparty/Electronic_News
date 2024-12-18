package controller.admin;

import service.admin.AdminService;
import service.admin.ArticleService;
import service.admin.CategoryService;
import service.admin.UserService;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/dashboard" })
public class DashboardController extends HttpServlet {
    private CategoryService categoryService = new CategoryService();
    private ArticleService articleService = new ArticleService();
    private AdminService adminService = new AdminService();
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categorySize = categoryService.getCategorySize();
        int articleSize = articleService.getArticleSize();
        int adminActiveSize = adminService.getAdminActiveSize();
        int adminInactiveSize = adminService.getAdminInactiveSize();
        int clientActiveSize = userService.getClientActiveSize();
        int clientInactiveSize = userService.getClientInactiveSize();

        req.setAttribute("categorySize", categorySize);
        req.setAttribute("articleSize", articleSize);
        req.setAttribute("adminActiveSize", adminActiveSize);
        req.setAttribute("adminInactiveSize", adminInactiveSize);
        req.setAttribute("clientActiveSize", clientActiveSize);
        req.setAttribute("clientInactiveSize", clientInactiveSize);


        RequestDispatcher dispactcher = req.getRequestDispatcher("/views/admin/pages/dashboard/index.jsp");
        dispactcher.forward(req, resp);
    }
}
