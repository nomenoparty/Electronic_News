package controller.admin;

import com.google.gson.Gson;
import dao.impl.UserDAO;
import helper.GenerateToken;
import model.RoleModel;
import model.UserModel;
import org.json.JSONObject;
import service.admin.AdminService;
import service.admin.CheckRoleService;
import service.admin.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.management.relation.Role;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/account/update" })
public class UpdateAccountController extends HttpServlet {
    private AdminService adminService = new AdminService();
    private CheckRoleService checkRoleService = new CheckRoleService();
    private UserDAO userDAO = new UserDAO();
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel user = (UserModel) req.getAttribute("user");

        if(!checkRoleService.checkRoleUser(user.getUserID(), "quan_ly_he_thong") && !checkRoleService.checkRoleUser(user.getUserID(), "quan_ly_nguoi_dung")){
            resp.sendRedirect("/admin/dashboard");
            return;
        }

        String userID = req.getParameter("userID");

        if(userID != null){
            resp.setCharacterEncoding("utf8");
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();

            JSONObject obj = new JSONObject();

            obj.put("code", 200);

            UserModel userModel = userDAO.selectById(Integer.parseInt(userID));
            String permission = userModel.getPermission();

            if(permission.equals("Quản lý bài viết")) permission = "quan_ly_bai_viet";
            else if(permission.equals("Quản lý người dùng")) permission = "quan_ly_nguoi_dung";
            else if(permission.equals("Quản lý hệ thống")) permission = "quan_ly_he_thong";
            else if(permission.equals("Người dùng")) permission = "user";

            userModel.setPermission(permission);

            Gson gson = new Gson();
            obj.put("user", gson.toJson(userModel));

            out.print(obj);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String userID = req.getParameter("userID");
        String fullName = req.getParameter("fullName");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String status = req.getParameter("status");

        UserModel userModel = new UserModel();
        userModel.setUserID(Integer.parseInt(userID));
        userModel.setFullName(fullName);
        userModel.setUsername(username);
        userModel.setPassword(password);
        userModel.setStatus(status);
        userModel.setPermission(role);

        userService.updateUser(userModel);

        resp.sendRedirect("/admin/account");
    }
}