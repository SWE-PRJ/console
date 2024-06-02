package com.sweprj.API;

import com.sweprj.Class.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentAPI {
    static ApiManager apiManager = ApiManager.getInstance();

    List<Comment> requestListOfComment(String projectName, String issueName) throws InterruptedException {
        List<Comment> comments = new ArrayList<>();
//        Comment comment1 = new Comment(0, 0, "This is comment1");
//        Comment comment2 = new Comment(0, 0, "This is comment2");
//        comments.add(comment1);
//        comments.add(comment2);
        return comments;
    }

    public static void createComment(int issueId, String content) {
        try {
            apiManager
                    .post(String.format("/api/issues/%d/comments?content=%s", issueId, content), new HashMap<>(), false)
                    .block();
        } catch (Exception e) {
            apiManager.handleException();
        }
    }

    public static void editComment(int issueId,int commentId,String content) {
        Map<String,String> body=Map.of(
                "content", content
        );
        try {
            apiManager
                    .patch(String.format("/api/issues/%d/comments/%d",issueId, commentId), body)
                    .block();
        } catch (Exception e) {
            apiManager.handleException();
        }
    }

    public static void deleteComment(int issueId,int commentId) {
        try {
            apiManager
                    .delete(String.format("/api/issues/%d/comments/%d",issueId, commentId))
                    .block();
        } catch (Exception e) {
            apiManager.handleException();
        }
    }
}
