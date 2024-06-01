package com.sweprj.Class;

import lombok.Data;

import java.util.List;

@Data
public class Issue {
    public String title;
    public String description;
    public List<Comment> comments;
    public String state;
    public String priority;
    public int id;
}
