package com.voicegov.ai.models;

public class User {
    public int id;
    public String name;
    public String mobile;
    public String password;
    public String email;
    public String language;

    public User() {}

    public User(String name, String email, String password, String mobile, String language) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.language = language;
    }
}