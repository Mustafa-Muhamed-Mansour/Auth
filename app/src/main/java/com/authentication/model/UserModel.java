package com.authentication.model;

public class UserModel
{
    String userID;
    String userImage;
    String userName;
    String userEmail;
    String userPHone;

    public UserModel()
    {
    }

    public UserModel(String userID, String userImage, String userName, String userEmail, String userPHone)
    {
        this.userID = userID;
        this.userImage = userImage;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPHone = userPHone;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserImage() {
        return userImage;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPHone() {
        return userPHone;
    }
}
