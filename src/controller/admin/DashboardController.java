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

@WebServlet(urlPatterns = { "/admin/dashboard" })
public class DashboardController extends HttpServlet {
    private AdminService adminService = new AdminService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispactcher = req.getRequestDispatcher("/views/admin/pages/dashboard/index.jsp");
        dispactcher.forward(req, resp);
    }
}
