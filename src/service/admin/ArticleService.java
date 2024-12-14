package service.admin;

import dao.impl.ArticleDAO;
import helper.GenerateSlug;
import model.ArticleModel;
import model.CategoryModel;

import java.util.ArrayList;

public class ArticleService {
    private ArticleDAO articleDAO = new ArticleDAO();
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
}
