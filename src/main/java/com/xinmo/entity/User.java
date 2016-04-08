package com.xinmo.entity;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -1640280890387664977L;
    private Long id;
    private String username;
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

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}