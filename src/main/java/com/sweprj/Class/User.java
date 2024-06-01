package com.sweprj.Class;

public class User {
    private String identifier;
    private String password;
    private String username;
    private String role = "admin";


    public User(String identifier, String password) {
        this.identifier = identifier;
        this.password = password;
    }
    public User(String username, String password, String identifier) {
        this.username = username;
        this.password = password;
        this.identifier = identifier;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIdentifier() {
        return identifier;
    }
}
