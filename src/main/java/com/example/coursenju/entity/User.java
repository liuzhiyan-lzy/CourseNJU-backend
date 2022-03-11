package com.example.coursenju.entity;

public class User {

    private int id;

    private String userId;

    private String password;

    private int type;

    private String userName;

    private int userSex;

    private String identityId;

    private String college;

    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {}

    public User(String userId) {
        this.userId = userId;
    }

    public String toInfoString() {
        return "id: " +
                id +
                "\nuserId: " +
                userId +
                "\npassword: " +
                password +
                "\ntype: " +
                type +
                "\nuserName: " +
                userName +
                "\nuserSex: " +
                userSex +
                "\nidentityId: " +
                identityId +
                "\ncollege: " +
                college +
                "\nemail: " +
                email;
    }
}
