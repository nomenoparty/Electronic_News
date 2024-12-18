//package controller.client;
//
//import dao.impl.CommentDAO;
//import model.CommentModel;
//import model.UserModel;
//import service.user.CommentService;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//
//
//@WebServlet(urlPatterns = {"/views/client/pages/article/CommentServlet"})
//public class CommentController extends HttpServlet {
//    private CommentService commentService;
//    private CommentDAO commentDAO;
//    public CommentController() {
//        this.commentService = new CommentService();
//
//        this.commentDAO = new CommentDAO();
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String content = request.getParameter("comment");
//        int articleID = Integer.parseInt(request.getParameter("articleID"));
//        int userID = Integer.parseInt(request.getParameter("userID")); // Assuming the userID is passed
//
//
//
//
//        if (content != null && !content.trim().isEmpty()) {
//            CommentModel newComment = new CommentModel();
//            newComment.setContent(content.trim());
//            newComment.setPostAt(new Timestamp(System.currentTimeMillis()));
//            newComment.setUserID(userID);
//            newComment.setArticleID(articleID);
//
//            try {
//                commentDAO.insert(newComment);
//            } catch (Exception e) {
//                e.printStackTrace();
//                request.setAttribute("errorMessage", "Error posting comment.");
//            }
//        }
//
//        // Redirect to doGet to refresh the page
//        response.sendRedirect("CommentServlet");
//
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int articleID = Integer.parseInt("2");
//
//        // Retrieve comments from the database
//        try {
//            ArrayList<CommentModel> comments = commentDAO.selectByArticleId(articleID);
//            request.setAttribute("comments", comments);
//        } catch (Exception e) {
//            e.printStackTrace();
//            request.setAttribute("errorMessage", "Error loading comments.");
//        }
//
//        // Forward to JSP page
//        request.getRequestDispatcher("/views/client/pages/article/index.jsp").forward(request, response);
//    }
//
//
//}
//
