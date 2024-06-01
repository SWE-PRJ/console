package com.sweprj.Class;

import lombok.Data;

import java.util.List;

@Data
public class Project {
    public String Name;
    int id;
    List<Issue> issues;
}
