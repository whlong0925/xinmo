package com.xinmo.entity;

public class User {
    private Long id;
    private String username;
    private String usercode;
    private String password;
    private int status;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsercode() {
        return this.usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}