package com.sweprj.API;

import com.sweprj.Class.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
}
