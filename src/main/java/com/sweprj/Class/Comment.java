package com.sweprj.Class;

public class Comment {
    int IssueID;
    int userID;
    public String comment;


    public Comment(int issueID, int userID, String comment) {
        IssueID = issueID;
        this.userID = userID;
        this.comment = comment;
    }
}
