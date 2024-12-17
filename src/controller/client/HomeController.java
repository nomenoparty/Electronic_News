package controller.client;

import helper.CategoryTree;
import helper.GetUrl;
import model.ArticleModel;
import model.CategoryModel;
import model.UserModel;
import service.admin.ArticleService;
import service.admin.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(urlPatterns = { "/home" })
public class HomeController extends HttpServlet {
	private ArticleService articleService = new ArticleService();
	private CategoryService categoryService = new CategoryService();
	private CategoryTree categoryTree = new CategoryTree();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Kiểm tra session và trạng thái đăng nhập
		HttpSession session = req.getSession(false);
		boolean isLoggedIn = false;
		UserModel currentUser = null;

		if (session != null && session.getAttribute("userModel") != null) {
			currentUser = (UserModel) session.getAttribute("userModel");
			isLoggedIn = true; // Đánh dấu đã đăng nhập
		}

		req.setAttribute("isLoggedIn", isLoggedIn);
		req.setAttribute("currentUser", currentUser);






		ArrayList<ArticleModel> articles = articleService.selectAllArticles();

		articles.forEach(i -> {
			i.setTitleCategory(GetUrl.getUrlFromHTML(i.getContent()));
			i.setContent(GetUrl.getDescriptionFromHTML(i.getContent()));
		});

		req.setAttribute("articles", articles);

		ArrayList<CategoryModel> categories = categoryService.selectAllCategories();

		Map<CategoryModel, ArrayList<CategoryModel>> map = categoryTree.getCategoryTree(categories);

		req.setAttribute("categories", map);

		RequestDispatcher dispactcher = req.getRequestDispatcher("/views/client/pages/home/index.jsp");
		dispactcher.forward(req, resp);
	}
}
