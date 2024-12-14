package controller.admin;

import com.google.gson.Gson;
import dao.impl.UserDAO;
import helper.GenerateToken;
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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/article/delete" })
public class DeleteArticleController extends HttpServlet {
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
            articleService.deleteArticle(Integer.parseInt(articleID));

            resp.sendRedirect("/admin/article");
        }
    }
}