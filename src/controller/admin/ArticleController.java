package controller.admin;

import model.ArticleModel;
import model.CategoryModel;
import model.UserModel;
import service.admin.AdminService;
import service.admin.ArticleService;
import service.admin.CheckRoleService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/article" })
@MultipartConfig
public class ArticleController extends HttpServlet {
    private CheckRoleService checkRoleService = new CheckRoleService();
    private ArticleService articleService = new ArticleService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel user = (UserModel) req.getAttribute("user");

        if(!checkRoleService.checkRoleUser(user.getUserID(), "quan_ly_he_thong") && !checkRoleService.checkRoleUser(user.getUserID(), "quan_ly_bai_viet")){
            resp.sendRedirect("/admin/dashboard");
            return;
        }

        String keyword = req.getParameter("keyword");

        ArrayList<ArticleModel> articles = new ArrayList<>();

        if(keyword == null || keyword.equals("")){
            articles.addAll(articleService.selectAllArticles());
        }else{
            articles.addAll(articleService.searchArticles(keyword));
        }

        req.setAttribute("articles", articles);

        RequestDispatcher dispactcher = req.getRequestDispatcher("/views/admin/pages/article/index.jsp");
        dispactcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String categoryID = req.getParameter("category_id");

        articleService.insertArticle(title, content, Integer.parseInt(categoryID));

        resp.sendRedirect("/admin/article");
    }
}
