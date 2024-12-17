package dao.impl;

import dao.DAOInterface;
import model.ArticleModel;
import model.CommentModel;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class CommentDAO implements DAOInterface<CommentModel> {
    @Override
    public int insert(CommentModel t) {
        int row = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "INSERT INTO comment(content, postAt, userID, articleID) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, t.getContent());
            Date date = new Date();
            pstm.setTimestamp(2, new Timestamp(date.getTime()));
            pstm.setInt(3, t.getUserID());
            pstm.setInt(4, t.getArticleID());

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
    public int update(CommentModel t) {
        int row = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "UPDATE comment SET content = ?, postAt = ? WHERE commentID = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, t.getContent());
            Date date = new Date();
            pstm.setTimestamp(2, new Timestamp(date.getTime()));
            pstm.setInt(3, t.getCommentID());

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
    public int delete(CommentModel t) {
        int row = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "DELETE FROM comment " +
                    " WHERE commentID = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, t.getCommentID());

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
    public ArrayList<CommentModel> selectAll() {
        return null;
    }

    @Override
    public CommentModel selectById(int id) {
        return null;
    }

    public ArrayList<CommentModel> selectByArticleId(int articleID) {
        ArrayList<CommentModel> commentList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM comment Join user on user.userID = comment.userID WHERE articleID = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, articleID);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String fullName = rs.getString("fullName");
                int commentID = rs.getInt("commentID");
                String content = rs.getString("content");
                Timestamp postAt = rs.getTimestamp("postAt");
                int userID = rs.getInt("userID");
                int articleId = rs.getInt("articleID");

                CommentModel commentModel = new CommentModel();

                commentModel.setFullName(fullName);
                commentModel.setCommentID(commentID);
                commentModel.setContent(content);
                commentModel.setPostAt(postAt);
                commentModel.setUserID(userID);
                commentModel.setArticleID(articleId);

                commentList.add(commentModel);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;
    }
}
