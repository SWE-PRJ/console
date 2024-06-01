package com.sweprj.Class;

import lombok.Data;

@Data
public class User {
    private String identifier;
    private String password;
    private String username;
    private String role = "admin";
}
