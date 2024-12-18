package controller.client;

import dao.impl.CommentDAO;
import model.CommentModel;
import model.UserModel;
import service.user.CommentService;

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

@WebServlet(urlPatterns = {"/comment"})
public class CommentController extends HttpServlet {
    private CommentService commentService;
    private CommentDAO commentDAO;
    public CommentController() {
        this.commentService = new CommentService();

        this.commentDAO = new CommentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String content = request.getParameter("comment");
        int articleID = Integer.parseInt(request.getParameter("articleID"));
//        int userID = Integer.parseInt(request.getParameter("userID"));
        UserModel userModel = (UserModel) request.getAttribute("userModel");

        String referer = request.getHeader("Referer");

        if(userModel == null){
            response.sendRedirect(referer);
            return;
        }

        if (content != null && !content.trim().isEmpty()) {
            CommentModel newComment = new CommentModel();
            newComment.setContent(content.trim());
            newComment.setUserID(userModel.getUserID());
            newComment.setArticleID(articleID);

            try {
                commentDAO.insert(newComment);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error posting comment.");
            }
        }

        response.sendRedirect(referer);
    }
}

