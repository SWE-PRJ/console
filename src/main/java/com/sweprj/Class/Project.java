package com.sweprj.Class;

import java.util.Date;
import java.util.List;

public class Project {
    public String Name;
    public Date CreatedDate;
    public String Description;
    List<Issue> issues;

    public Project(String Name, Date CreatedDate, String Description) {
        this.Name = Name;
        this.CreatedDate = CreatedDate;
        this.Description = Description;
    }
}
