package controller.admin;

import com.google.gson.Gson;
import dao.impl.UserDAO;
import helper.GenerateToken;
import model.CategoryModel;
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

@WebServlet(urlPatterns = { "/admin/category/update" })
public class UpdateCategoryController extends HttpServlet {
    private CheckRoleService checkRoleService = new CheckRoleService();
    private CategoryService categoryService = new CategoryService();
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel user = (UserModel) req.getAttribute("user");

        if(!checkRoleService.checkRoleUser(user.getUserID(), "quan_ly_he_thong") && !checkRoleService.checkRoleUser(user.getUserID(), "quan_ly_bai_viet")){
            resp.sendRedirect("/admin/dashboard");
            return;
        }

        String categoryID = req.getParameter("categoryID");

        if(categoryID != null){
            resp.setCharacterEncoding("utf8");
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();

            JSONObject obj = new JSONObject();

            obj.put("code", 200);

            CategoryModel categoryModel = categoryService.selectById(Integer.parseInt(categoryID));

            Gson gson = new Gson();
            obj.put("category", gson.toJson(categoryModel));

            out.print(obj);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String title = req.getParameter("title");
        String parentID = req.getParameter("parent_id");
        String categoryID = req.getParameter("categoryID");

        categoryService.updateCategory(Integer.parseInt(categoryID), title, parentID.equals("") ? 0 : Integer.parseInt(parentID));

        resp.sendRedirect("/admin/category");
    }
}