package com.sweprj.API;

import java.util.HashMap;
import java.util.Map;

public class CommentAPI {
    static ApiManager apiManager = ApiManager.getInstance();

    public static void createComment(int issueId, String content) {
        try {
            apiManager
                    .post(String.format("/api/issues/%d/comments?content=%s", issueId, content), new HashMap<>(), false)
                    .block();
        } catch (Exception e) {
            ApiManager.handleException();
        }
    }

    public static void editComment(int issueId, int commentId, String content) {
        Map<String, String> body = Map.of(
                "content", content
        );
        try {
            apiManager
                    .patch(String.format("/api/issues/%d/comments/%d", issueId, commentId), body)
                    .block();
        } catch (Exception e) {
            ApiManager.handleException();
        }
    }

    public static void deleteComment(int issueId, int commentId) {
        try {
            apiManager
                    .delete(String.format("/api/issues/%d/comments/%d", issueId, commentId))
                    .block();
        } catch (Exception e) {
            ApiManager.handleException();
        }
    }
}
