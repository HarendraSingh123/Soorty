package com.camellia.soorty.login.model;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("user_first_name")
     String UserfirstName;
    @SerializedName("user_last_name")
    String userLastName;


    public String getUserfirstName() {
        return UserfirstName;
    }

    public void setUserfirstName(String userfirstName) {
        UserfirstName = userfirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    @SerializedName("user_id")
    int userId;
    @SerializedName("user_role")
    String userRole;
    @SerializedName("user_name")
    String userName;
    @SerializedName("user_email")
    String userEmail;
    @SerializedName("user_mobile")
    String userMobile;

    @SerializedName("user_password")
    String user_password;

    @SerializedName("image_url")
    String profileImage;

    @SerializedName("user_address")
    String userAddress;


    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId=" + userId +
                ", userRole='" + userRole + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userMobile='" + userMobile + '\'' +
                '}';
    }
}
