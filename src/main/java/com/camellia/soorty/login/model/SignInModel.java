package com.camellia.soorty.login.model;



import com.camellia.soorty.Repos.MyAppPref;
import com.camellia.soorty.REST.ApiInterface;

import io.reactivex.Observable;

public class SignInModel {
    private ApiInterface apiInterface;
    private MyAppPref myAppPref;

    SignInModel(ApiInterface apiInterface,MyAppPref myAppPref) {
        this.apiInterface = apiInterface;
        this.myAppPref = myAppPref;
    }

    public Observable<GetOtpApiResponse> getOtp(String phoneNo,String countryCode) {
        return apiInterface.getOtp(phoneNo,countryCode);
    }
    public Observable<GetOtpApiResponse> verifyOtp(String phoneNo,String otp,String role,String countryCode) {
        return apiInterface.verifyOtp(phoneNo,otp,role,myAppPref.getFcmToken(),"android",countryCode);
    }

//    public Observable<GetOtpApiResponse> saveExtraDetails(int userId,String userName,String email) {
//       return apiInterface.saveExtraDetails(userId,userName,email);
//    }

}
