package com.example.itubeapp.Util;

public class LogInInfoStore {
    String Username,FullName,Password;

    public LogInInfoStore(String username, String fullName, String password) {
        Username = username;
        FullName = fullName;
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
