package controller.admin;

import com.google.gson.Gson;
import dao.impl.UserDAO;
import helper.GenerateToken;
import model.RoleModel;
import model.UserModel;
import org.json.JSONObject;
import service.admin.AdminService;
import service.admin.CategoryService;
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

@WebServlet(urlPatterns = { "/admin/category/delete" })
public class DeleteCategoryController extends HttpServlet {
    private CheckRoleService checkRoleService = new CheckRoleService();
    private CategoryService categoryService = new CategoryService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel user = (UserModel) req.getAttribute("user");

        if(!checkRoleService.checkRoleUser(user.getUserID(), "quan_ly_he_thong") && !checkRoleService.checkRoleUser(user.getUserID(), "quan_ly_bai_viet")){
            resp.sendRedirect("/admin/dashboard");
            return;
        }

        String categoryID = req.getParameter("categoryID");

        if(categoryID != null){
            categoryService.deleteCategory(Integer.parseInt(categoryID));

            resp.sendRedirect("/admin/category");
        }
    }
}