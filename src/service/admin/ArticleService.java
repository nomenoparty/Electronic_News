package service.admin;

import dao.impl.ArticleDAO;
import dao.impl.CategoryDAO;
import helper.GenerateSlug;
import model.ArticleModel;
import model.CategoryModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ArticleService {
    private ArticleDAO articleDAO = new ArticleDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();
    public int insertArticle(String title, String content, int categoryID){
        ArticleModel articleModel = new ArticleModel();

        String slug = GenerateSlug.toSlug(title);

        articleModel.setTitle(title);
        articleModel.setContent(content);
        articleModel.setSlug(slug);
        articleModel.setCategoryID(categoryID);

        return articleDAO.insert(articleModel);
    }
    public ArrayList<ArticleModel> selectAllArticles(){
        return articleDAO.selectAll();
    }
    public ArticleModel selectById(int id){
        return articleDAO.selectById(id);
    }
    public int updateArticle(int articleID, String title, String content, int categoryID){
        ArticleModel articleModel = new ArticleModel();

        String slug = GenerateSlug.toSlug(title);

        articleModel.setArticleID(articleID);
        articleModel.setTitle(title);
        articleModel.setContent(content);
        articleModel.setSlug(slug);
        articleModel.setCategoryID(categoryID);

        return articleDAO.update(articleModel);
    }
    public int deleteArticle(int id){
        ArticleModel articleModel = new ArticleModel();

        articleModel.setArticleID(id);

        return articleDAO.delete(articleModel);
    }
    public int getArticleSize(){
        return articleDAO.countArticle();
    }
    public ArticleModel getArticleBySlug(String slug){
        return articleDAO.selectBySlug(slug);
    }
    public ArrayList<ArticleModel> getArticleWithCategory(String categorySlug){
        ArrayList<ArticleModel> articles = new ArrayList<>();

        CategoryModel category = categoryDAO.selectBySlug(categorySlug);

        articles.addAll(articleDAO.selectAllByCategoryId(category.getCategoryID()));

        ArrayList<CategoryModel> categoryChilds = categoryDAO.selectAllWithParentID(category.getCategoryID());

        categoryChilds.forEach(categoryChild -> {
            articles.addAll(articleDAO.selectAllByCategoryId(categoryChild.getCategoryID()));
        });

        return articles;
    }
}
