package com.sweprj.Class;

import java.util.List;

public class Issue {
    public String title;
    public String description;
    public List<Comment> comments;
    public String status;
    public String priority;

    public Issue(String title, String description, List<Comment> comments, String status, String priority) {
        this.title = title;
        this.description = description;
        this.comments = comments;
        this.status = status;
        this.priority = priority;
    }
}
