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

@WebServlet(urlPatterns = { "/admin/account/delete" })
public class DeleteAccountController extends HttpServlet {
    private CheckRoleService checkRoleService = new CheckRoleService();
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
            userService.deleteUser(Integer.parseInt(userID));

            resp.sendRedirect("/admin/account");
        }
    }
}