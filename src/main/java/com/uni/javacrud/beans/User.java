package com.uni.javacrud.beans;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String role;
    private String status;
    private float money;
    public User(int id, String username, String email, String password, String role, String status, float money) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        if (role == null || role.equals(""))
        {
            this.role="USER";
        }
        else {
            this.role = role;
        }
        this.status = status;
        this.money = money;
    }

    public User(String username, String email, String password, String role, String status, float money) {
        this.username = username;
        this.email = email;
        this.password = password;
        if (role == null || role.equals(""))
        {
            this.role="USER";
        }
        else {
            this.role = role;
        }
        this.status = status;
        this.money = money;
    }

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
