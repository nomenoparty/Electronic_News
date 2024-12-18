package service.user;

import dao.impl.CommentDAO;
import model.CommentModel;

import java.util.ArrayList;

public class CommentService {

    private CommentDAO commentDAO;

    public CommentService() {
        this.commentDAO = new CommentDAO();
    }


    public int addComment(CommentModel comment) {

        if (comment.getContent() == null || comment.getContent().isEmpty()) {
            System.out.println("Nội dung bình luận không hợp lệ");
            return 0;
        }
        return commentDAO.insert(comment);
    }

    public int updateComment(CommentModel comment) {

        if (comment.getContent() == null || comment.getContent().isEmpty()) {
            System.out.println("Nội dung bình luận không hợp lệ");
            return 0;
        }
        return commentDAO.update(comment);
    }

    public ArrayList<CommentModel> getCommentsByArticleId(int articleID) {
        return commentDAO.selectByArticleId(articleID);
    }
}
