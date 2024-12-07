package model;

import java.sql.Timestamp;

public class CommentModel {
    private int commentID;
    private String content;
    private Timestamp postAt;
    private int userID;
    private int articleID;

    public CommentModel(){}

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPostAt() {
        return postAt;
    }

    public void setPostAt(Timestamp postAt) {
        this.postAt = postAt;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }
}