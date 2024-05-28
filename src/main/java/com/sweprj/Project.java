package com.sweprj;

import java.util.Date;
import java.util.List;

public class Project {
    String Name;
    Date CreatedDate;
    String Description;
    List<Issue> issues;

    Project(String Name, Date CreatedDate, String Description) {
        this.Name = Name;
        this.CreatedDate = CreatedDate;
        this.Description = Description;
    }
}
