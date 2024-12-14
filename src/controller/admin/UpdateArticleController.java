package controller.admin;

import com.google.gson.Gson;
import dao.impl.UserDAO;
import helper.GenerateToken;
import model.ArticleModel;
import model.CategoryModel;
import model.RoleModel;
import model.UserModel;
import org.json.JSONObject;
import service.admin.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.management.relation.Role;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/article/update" })
@MultipartConfig
public class UpdateArticleController extends HttpServlet {
    private CheckRoleService checkRoleService = new CheckRoleService();
    private ArticleService articleService = new ArticleService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel user = (UserModel) req.getAttribute("user");

        if(!checkRoleService.checkRoleUser(user.getUserID(), "quan_ly_he_thong") && !checkRoleService.checkRoleUser(user.getUserID(), "quan_ly_bai_viet")){
            resp.sendRedirect("/admin/dashboard");
            return;
        }

        String articleID = req.getParameter("articleID");

        if(articleID != null){
            resp.setCharacterEncoding("utf8");
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();

            JSONObject obj = new JSONObject();

            obj.put("code", 200);

            ArticleModel articleModel = articleService.selectById(Integer.parseInt(articleID));

            Gson gson = new Gson();
            obj.put("article", gson.toJson(articleModel));

            out.print(obj);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String categoryID = req.getParameter("category_id");
        String articleID = req.getParameter("articleID");

        articleService.updateArticle(Integer.parseInt(articleID), title, content, Integer.parseInt(categoryID));

        resp.sendRedirect("/admin/article");
    }
}