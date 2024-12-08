package controller.admin;

import dao.impl.UserDAO;
import helper.GenerateToken;
import model.RoleModel;
import model.UserModel;
import service.admin.AdminService;
import service.admin.CheckRoleService;

import java.io.IOException;
import java.util.ArrayList;

import javax.management.relation.Role;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/account" })
public class AccountController extends HttpServlet {
    private AdminService adminService = new AdminService();
    private CheckRoleService checkRoleService = new CheckRoleService();
    private UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel user = (UserModel) req.getAttribute("user");

        if(!checkRoleService.checkRoleUser(user.getUserID(), "quan_ly_he_thong") && !checkRoleService.checkRoleUser(user.getUserID(), "quan_ly_nguoi_dung")){
            resp.sendRedirect("/admin/dashboard");
            return;
        }

        RoleModel role = (RoleModel) req.getAttribute("role");

        ArrayList<UserModel> listAccount = new ArrayList<>();

        if(role.getPermission().equals("quan_ly_he_thong")){
            listAccount.addAll(userDAO.selectAllExceptUserID(user.getUserID()));
        }else if(role.getPermission().equals("quan_ly_nguoi_dung")){
            listAccount.addAll(userDAO.selectAllWithRoleIDExceptUserID(1, user.getUserID()));
        }

        req.setAttribute("accounts", listAccount);

        RequestDispatcher dispactcher = req.getRequestDispatcher("/views/admin/pages/account/index.jsp");
        dispactcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String fullname = req.getParameter("fullName");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        UserModel admin = new UserModel();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setFullName(fullname);
        admin.setStatus("active");

        adminService.insertAdmin(admin, role);

        resp.sendRedirect("/admin/account");
    }
}
