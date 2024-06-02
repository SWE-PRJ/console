package com.sweprj.API;

import com.sweprj.Class.Comment;
import com.sweprj.Class.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IssueAPI {
    static ApiManager apiManager = ApiManager.getInstance();

    //이슈 생성
    public static void requestCreateIssue(int projectId, Issue issue) throws InterruptedException {
        Map<String, String> body = Map.of(
                "title", issue.getTitle(),
                "description", issue.getDescription(),
                "priority", issue.getPriority());
        try {
            apiManager.post("/api/projects/" + projectId + "/issues", body, false).block();
        } catch (Exception e) {
            ApiManager.handleException();
            throw new InterruptedException();
        }
    }

    //이슈 상태 변경
    public static void requestChangeState(int issueId, String state) throws InterruptedException {
        Map<String, String> body = Map.of("state", state);
        try {
            apiManager.patch("/api/issues/" + issueId, body).block();
        } catch (Exception e) {
            ApiManager.handleException();
            throw new InterruptedException();
        }
    }

    //프로젝트에 속한 이슈 검색
    public static List<Issue> requestListOfIssue(int projectId) throws InterruptedException {
        List<Issue> issues = new ArrayList<>();
        Map<String, Object> response;
        try {
            response = (Map<String, Object>) apiManager.get("/api/projects/" + projectId + "/issues", false).block();
        } catch (Exception e) {
            ApiManager.handleException();
            throw new InterruptedException();
        }
        ((List<Map<String, Object>>) (response.get("issues"))).forEach(issue -> {
            Map<String, Object> issueMap = issue;
            Issue issueObj = new Issue();
            issueObj.setId((int) issueMap.get("id"));
            issueObj.setTitle((String) issueMap.get("title"));
            issueObj.setDescription((String) issueMap.get("description"));
            issueObj.setState((String) issueMap.get("state"));
            issueObj.setPriority((String) issueMap.get("priority"));
            ((List<Map<String, Object>>) (issueMap.get("comments"))).forEach(comment -> {
                Map<String, Object> commentMap = comment;
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
}
