package com.sweprj.Class;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Issue {
    private String title;
    private String description;
    private List<Comment> comments = new ArrayList<>();
    private String state;
    private String priority;
    private int id;
}
