package com.camellia.soorty.models;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyOtpUserModel {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_role")
    @Expose
    private String userRole;
    @SerializedName("user_firstname")
    @Expose
    private String userFirstname;
    @SerializedName("user_lastname")
    @Expose
    private String userLastname;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("user_image")
    @Expose
    private String userImage;
    @SerializedName("user_address")
    @Expose
    private Object userAddress;
    @SerializedName("user_dlfront")
    @Expose
    private Object userDlfront;
    @SerializedName("user_dlback")
    @Expose
    private Object userDlback;
    @SerializedName("user_mobile")
    @Expose
    private String userMobile;
    @SerializedName("user_status")
    @Expose
    private String userStatus;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("fcm_token")
    @Expose
    private String fcmToken;
    @SerializedName("device_type")
    @Expose
    private String deviceType;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    @SerializedName("access_token")
    @Expose
    private String access_token;

    public VerifyOtpUserModel()
    {

        }


    public VerifyOtpUserModel(String user_Id, String user_Firstname, String user_Lastname, String user_email, String  user_mobile,String access_token, String fcm_token)
    {

        this.userId=user_Id;
        this.userFirstname=user_Firstname;
        this.userLastname=user_Lastname;
        this.userEmail=user_email;
        this.userMobile=user_mobile;
        this.access_token=access_token;
        this.fcmToken=fcm_token;

        }

        public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public Object getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(Object userAddress) {
        this.userAddress = userAddress;
    }

    public Object getUserDlfront() {
        return userDlfront;
    }

    public void setUserDlfront(Object userDlfront) {
        this.userDlfront = userDlfront;
    }

    public Object getUserDlback() {
        return userDlback;
    }

    public void setUserDlback(Object userDlback) {
        this.userDlback = userDlback;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
