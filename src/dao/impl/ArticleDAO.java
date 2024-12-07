package dao.impl;

import dao.DAOInterface;
import model.ArticleModel;
import model.UserModel;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class ArticleDAO implements DAOInterface<ArticleModel> {
    @Override
    public int insert(ArticleModel t) {
        int row = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "INSERT INTO article(title, content, slug, createdAt, categoryID) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, t.getTitle());
            pstm.setString(2, t.getContent());
            pstm.setString(3, t.getSlug());
            Date date = new Date();
            pstm.setTimestamp(4, new Timestamp(date.getTime()));
            pstm.setInt(5, t.getCategoryID());

            row = pstm.executeUpdate();

            if (row != 0) {
                System.out.println("Thêm thành công: " + row);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int update(ArticleModel t) {
        int row = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "UPDATE article SET title = ?, content = ?, slug = ?, categoryID = ? WHERE articleID = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, t.getTitle());
            pstm.setString(2, t.getContent());
            pstm.setString(3, t.getSlug());
            pstm.setInt(4, t.getCategoryID());
            pstm.setInt(5, t.getArticleID());

            row = pstm.executeUpdate();

            if (row != 0) {
                System.out.println("Cập nhật thành công: " + row);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int delete(ArticleModel articleModel) {
        int row = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "DELETE FROM article " +
                    " WHERE articleID = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, articleModel.getArticleID());

            row = pstm.executeUpdate();

            if (row != 0) {
                System.out.println("Xóa thành công: " + row);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public ArrayList<ArticleModel> selectAll() {
        ArrayList<ArticleModel> articleList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM article";

            PreparedStatement pstm = con.prepareStatement(query);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int articleID = rs.getInt("articleID");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String Slug = rs.getString("slug");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                int categoryID = rs.getInt("categoryID");

                ArticleModel articleModel = new ArticleModel();

                articleModel.setArticleID(articleID);
                articleModel.setTitle(title);
                articleModel.setContent(content);
                articleModel.setSlug(Slug);
                articleModel.setCreatedAt(createdAt);
                articleModel.setCategoryID(categoryID);

                articleList.add(articleModel);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    @Override
    public ArticleModel selectById(int id) {
        return null;
    }
    public ArticleModel selectBySlug(String slug) {
        ArticleModel articleModel = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM article WHERE slug = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, slug);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int articleID = rs.getInt("articleID");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String Slug = rs.getString("slug");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                int categoryID = rs.getInt("categoryID");

                articleModel = new ArticleModel();

                articleModel.setArticleID(articleID);
                articleModel.setTitle(title);
                articleModel.setContent(content);
                articleModel.setSlug(Slug);
                articleModel.setCreatedAt(createdAt);
                articleModel.setCategoryID(categoryID);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleModel;
    }
    public ArrayList<ArticleModel> selectAllByCategoryId(int categoryID) {
        ArrayList<ArticleModel> articleList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM article WHERE categoryID = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, categoryID);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int articleID = rs.getInt("articleID");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String Slug = rs.getString("slug");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                int categoryId = rs.getInt("categoryID");

                ArticleModel articleModel = new ArticleModel();

                articleModel.setArticleID(articleID);
                articleModel.setTitle(title);
                articleModel.setContent(content);
                articleModel.setSlug(Slug);
                articleModel.setCreatedAt(createdAt);
                articleModel.setCategoryID(categoryId);

                articleList.add(articleModel);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }
}
