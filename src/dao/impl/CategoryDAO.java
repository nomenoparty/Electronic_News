package dao.impl;

import dao.DAOInterface;
import model.ArticleModel;
import model.CategoryModel;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class CategoryDAO implements DAOInterface<CategoryModel> {
    @Override
    public int insert(CategoryModel t) {
        int row = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "INSERT INTO category(title, slug, parentID) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, t.getTitle());
            pstm.setString(2, t.getSlug());
            pstm.setInt(3, t.getParentID());

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
    public int update(CategoryModel t) {
        int row = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "UPDATE category SET title = ?, slug = ?, parentID = ? WHERE categoryID = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, t.getTitle());
            pstm.setString(2, t.getSlug());
            pstm.setInt(3, t.getParentID());
            pstm.setInt(4, t.getCategoryID());

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
    public int delete(CategoryModel t) {
        int row = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "DELETE FROM category " +
                    " WHERE categoryID = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, t.getCategoryID());

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
    public ArrayList<CategoryModel> selectAll() {
        ArrayList<CategoryModel> categoryList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM category";

            PreparedStatement pstm = con.prepareStatement(query);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int categoryID = rs.getInt("categoryID");
                String title = rs.getString("title");
                String slug = rs.getString("slug");
                int parentID = rs.getInt("parentID");

                CategoryModel categoryModel = new CategoryModel();

                categoryModel.setCategoryID(categoryID);
                categoryModel.setTitle(title);
                categoryModel.setSlug(slug);
                categoryModel.setParentID(parentID);

                categoryList.add(categoryModel);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public CategoryModel selectById(int id) {
        return null;
    }

    public CategoryModel selectBySlug(String slug) {
        CategoryModel categoryModel = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM category WHERE slug = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, slug);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int categoryID = rs.getInt("categoryID");
                String title = rs.getString("title");
                String Slug = rs.getString("slug");
                int parentID = rs.getInt("parentID");

                categoryModel = new CategoryModel();

                categoryModel.setCategoryID(categoryID);
                categoryModel.setTitle(title);
                categoryModel.setSlug(slug);
                categoryModel.setParentID(parentID);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryModel;
    }
}
