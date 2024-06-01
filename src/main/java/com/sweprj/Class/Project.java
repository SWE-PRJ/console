package com.sweprj.Class;

import java.util.Date;
import java.util.List;

public class Project {
    public String Name;
    int id;
    List<Issue> issues;

    public Project(int id,String Name) {
        this.id = id;
        this.Name = Name;
    }
}
