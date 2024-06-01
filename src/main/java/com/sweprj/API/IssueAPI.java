package com.sweprj.API;

import com.sweprj.Class.Issue;

import java.util.ArrayList;
import java.util.List;

public class IssueAPI {
    public static List<Issue> requestListOfIssue(String projectName) throws InterruptedException {
//        List<Comment> comments = new ArrayList<>();
//        comments = requestListOfComment(projectName, "Issue1");
        List<Issue> issues = new ArrayList<>();
        Issue issue1 = new Issue("Issue1", "This is issue1", new ArrayList<>(), "Open", "High");
        Issue issue2 = new Issue("Issue2", "This is issue2", new ArrayList<>(), "Open", "High");
        issues.add(issue1);
        issues.add(issue2);
        return issues;
    }
}
