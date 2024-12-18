package controller.client;

import helper.CategoryTree;
import model.ArticleModel;
import model.CategoryModel;
import model.CommentModel;
import model.UserModel;
import service.admin.ArticleService;
import service.admin.CategoryService;
import service.user.CommentService;
import service.user.UserService;

import javax.servlet.AsyncContext;
import javax.servlet.HttpConstraintElement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(urlPatterns = { "/article/*"}, asyncSupported=true)
public class ArticleController extends HttpServlet {
    private ArticleService articleService = new ArticleService();
    private CategoryService categoryService = new CategoryService();
    private CategoryTree categoryTree = new CategoryTree();
    private CommentService commentService = new CommentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String slug = pathInfo.substring(1);

            AsyncContext asyncContext = req.startAsync();
            // Bắt đầu xử lý bất đồng bộ
            asyncContext.start(() -> {
                try {
                    ArticleModel articleModel = articleService.getArticleBySlug(slug);
                    int articleID = articleModel.getArticleID();
                    req.setAttribute("articleID", articleID);

                    req.setAttribute("article", articleModel);

                    ArrayList<CategoryModel> categories = categoryService.selectAllCategories();

                    Map<CategoryModel, ArrayList<CategoryModel>> map = categoryTree.getCategoryTree(categories);

                    req.setAttribute("categories", map);

                    ArrayList<CommentModel> comments = commentService.getCommentsByArticleId(articleModel.getArticleID());

                    req.setAttribute("comments", comments);

                    RequestDispatcher dispactcher = req.getRequestDispatcher("/views/client/pages/article/index.jsp");
                    dispactcher.forward(req, resp);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // Hoàn thành xử lý bất đồng bộ
                    asyncContext.complete();
                }
            });
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession(false);
        UserModel currentUser = (UserModel) session.getAttribute("user");


        if (currentUser == null) {

            req.setAttribute("errorMessage", "Please log in to post a comment.");
            resp.sendRedirect("/article/" + req.getPathInfo().substring(1));
            return;

        }

        String content = req.getParameter("comment");
        int articleID = Integer.parseInt(req.getParameter("articleID"));  // Lấy articleID từ form
        int userID = Integer.parseInt(req.getParameter("userID"));


        CommentModel newComment = new CommentModel();
        newComment.setContent(content);
        newComment.setArticleID(articleID);
        newComment.setUserID(userID);
        newComment.setPostAt(new Timestamp(System.currentTimeMillis()));


        commentService.addComment(newComment);


        resp.sendRedirect("/article/" + req.getPathInfo().substring(1));
    }

}
