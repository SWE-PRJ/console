package com.sweprj;

public class Comment {
    int IssueID;
    int userID;
    String comment;


    public Comment(int issueID, int userID, String comment) {
        IssueID = issueID;
        this.userID = userID;
        this.comment = comment;
    }
}
