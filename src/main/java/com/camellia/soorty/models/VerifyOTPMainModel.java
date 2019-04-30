package com.camellia.soorty.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyOTPMainModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user")
    @Expose
    private VerifyOtpUserModel verifyOtpUserModel;
    @SerializedName("status")
    @Expose
    private String status;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    @SerializedName("Authorization")
    @Expose

    private String authorization;


    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @SerializedName("otp")
    @Expose

    private String otp;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public VerifyOtpUserModel getVerifyOtpUserModel() {
        return verifyOtpUserModel;
    }

    public void setVerifyOtpUserModel(VerifyOtpUserModel verifyOtpUserModel) {
        this.verifyOtpUserModel = verifyOtpUserModel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
