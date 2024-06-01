package com.sweprj.API;

import com.sweprj.Class.Project;
import com.sweprj.Class.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectAPI {
    public static boolean requestCreateProject(User user) throws InterruptedException {
        return true;
    }

    public static boolean requestInviteMember(String memberName) throws InterruptedException {
        return false;
    }

    public static List<Project> requestListOfProject() throws InterruptedException {
        List<Project> projects = new ArrayList<>();
        Project project1 = new Project("Project1", new Date(), "This is project1");
        Project project2 = new Project("Project2", new Date(), "This is project2");
        projects.add(project1);
        projects.add(project2);
        return projects;
    }
}
