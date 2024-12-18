package controller.admin;

import com.google.gson.Gson;
import model.CategoryModel;
import model.UserModel;
import org.json.JSONObject;
import service.admin.CategoryService;
import service.admin.CheckRoleService;
import service.user.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/category/load" })
public class LoadCategory extends HttpServlet {
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

        resp.setCharacterEncoding("utf8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        JSONObject obj = new JSONObject();

        obj.put("code", 200);

        ArrayList<CategoryModel> categories = categoryService.selectAllCategoriesWithParentID(0);

        Gson gson = new Gson();
        obj.put("categories", gson.toJson(categories));

        out.print(obj);
    }
}