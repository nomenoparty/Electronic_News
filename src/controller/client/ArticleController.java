package controller.client;

import helper.CategoryTree;
import model.ArticleModel;
import model.CategoryModel;
import service.admin.ArticleService;
import service.admin.CategoryService;

import javax.servlet.HttpConstraintElement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(urlPatterns = { "/article/*" })
public class ArticleController extends HttpServlet {
    private ArticleService articleService = new ArticleService();
    private CategoryService categoryService = new CategoryService();
    private CategoryTree categoryTree = new CategoryTree();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String slug = pathInfo.substring(1);

            ArticleModel articleModel = articleService.getArticleBySlug(slug);

            req.setAttribute("article", articleModel);

            ArrayList<CategoryModel> categories = categoryService.selectAllCategories();

            Map<CategoryModel, ArrayList<CategoryModel>> map = categoryTree.getCategoryTree(categories);

            req.setAttribute("categories", map);

            RequestDispatcher dispactcher = req.getRequestDispatcher("/views/client/pages/article/index.jsp");
            dispactcher.forward(req, resp);
        }
    }
}
