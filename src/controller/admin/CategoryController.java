package controller.admin;

import helper.CategoryTree;
import model.CategoryModel;
import model.UserModel;
import service.admin.AdminService;
import service.admin.CategoryService;
import service.admin.CheckRoleService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/category" })
public class CategoryController extends HttpServlet {
    private CategoryService categoryService = new CategoryService();
    private CheckRoleService checkRoleService = new CheckRoleService();
    private CategoryTree categoryTree = new CategoryTree();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel user = (UserModel) req.getAttribute("user");

        if(!checkRoleService.checkRoleUser(user.getUserID(), "quan_ly_he_thong") && !checkRoleService.checkRoleUser(user.getUserID(), "quan_ly_bai_viet")){
            resp.sendRedirect("/admin/dashboard");
            return;
        }

        ArrayList<CategoryModel> categories = categoryService.selectAllCategories();

        Map<CategoryModel, ArrayList<CategoryModel>> map = categoryTree.getCategoryTree(categories);

        req.setAttribute("categories", map);

        RequestDispatcher dispactcher = req.getRequestDispatcher("/views/admin/pages/category/index.jsp");
        dispactcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String title = req.getParameter("title");
        String parentID = req.getParameter("parent_id");

        categoryService.insertCategory(title, parentID.equals("") ? 0 : Integer.parseInt(parentID));

        resp.sendRedirect("/admin/category");
    }
}
