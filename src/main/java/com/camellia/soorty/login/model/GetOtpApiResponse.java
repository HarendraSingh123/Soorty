package com.camellia.soorty.login.model;

import com.google.gson.annotations.SerializedName;

public class GetOtpApiResponse {
    @SerializedName("otp")
    private String otp;
    @SerializedName("status")
    private boolean status;

    public String message;

    @SerializedName("user")
    UserModel userModel;

    @SerializedName("access_token")
    String accesstoken;


    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "GetOtpApiResponse{" +
                "otp='" + otp + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", userModel=" + (userModel!=null?userModel.toString():userModel) +
                ", accesstoken='" + accesstoken + '\'' +
                '}';
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
