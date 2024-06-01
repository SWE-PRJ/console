package com.sweprj.API;

import com.sweprj.Class.Comment;
import com.sweprj.Class.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IssueAPI {
    static ApiManager apiManager = ApiManager.getInstance();

    //프로젝트에 속한 이슈 검색
    public static List<Issue> requestListOfIssue(int projectId) throws InterruptedException {
        List<Issue> issues = new ArrayList<>();
        Map<String, Object> response;
        try {
            response = (Map<String, Object>) apiManager.get("/api/projects/" + projectId + "/issues", false).block();
        } catch (Exception e) {
            apiManager.handleException();
            throw new InterruptedException();
        }
        ((List<Map<String, Object>>) (response.get("issues"))).forEach(issue -> {
            Map<String, Object> issueMap = (Map<String, Object>) issue;
            Issue issueObj = new Issue();
            issueObj.setId((int) issueMap.get("id"));
            issueObj.setTitle((String) issueMap.get("title"));
            issueObj.setDescription((String) issueMap.get("description"));
            issueObj.setState((String) issueMap.get("state"));
            issueObj.setPriority((String) issueMap.get("priority"));
            ((List<Map<String, Object>>) (issueMap.get("comments"))).forEach(comment -> {
                Map<String, Object> commentMap = (Map<String, Object>) comment;
                Comment commentObj = new Comment();
                commentObj.setId((int) commentMap.get("id"));
                commentObj.setIssueId((int) commentMap.get("issueId"));
                commentObj.setContent((String) commentMap.get("content"));
                issueObj.getComments().add(commentObj);
            });
            issues.add(issueObj);
        });
        return issues;
    }

    //이슈에 속한 코멘트 조회
    public static List<Comment> requestListOfComment(int issueId) throws InterruptedException {
        List<Comment> comments = List.of();
        try {
            comments = (List<Comment>) apiManager.get("/api/issues/" + issueId + "/comments", false).block();
        } catch (Exception e) {
            apiManager.handleException();
            throw new InterruptedException();
        }
        return comments;
    }
}
