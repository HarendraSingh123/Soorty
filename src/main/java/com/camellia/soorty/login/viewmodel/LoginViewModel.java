package com.camellia.soorty.login.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;


import com.camellia.soorty.R;
import com.camellia.soorty.login.model.GetOtpApiResponse;
import com.camellia.soorty.login.model.SignInModel;
import com.camellia.soorty.login.view.SignInFragment;
import com.camellia.soorty.utills.BaseViewModel;

import java.util.regex.Pattern;

import io.reactivex.Observable;


public class LoginViewModel extends BaseViewModel {

    public enum STATUS {
        EMPTY, VALIDATED, INVALID,INVALID_PASSWORD,TERMnCON
    }


    private SignInModel signInModel;
    public String phoneNo = "";
    private String userType = "";
    private String otp = "";
    public String userfirstname="";
    public String userlastName="";
    public String userEmail = "";
    public String countryCode = "";
    private int userId;
    public boolean isSocial = false;
    public String termsNCon = "";
    public String password="";

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserfirstname() {
        return userfirstname;
    }

    public void setUserfirstname(String userfirstname) {
        this.userfirstname = userfirstname;
    }

    public String getUserlastName() {
        return userlastName;
    }

    public void setUserlastName(String userlastName) {
        this.userlastName = userlastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        notifyChange();
    }

    public String getTermsNCon() {
        return termsNCon;
    }

    public void setTermsNCon(String termsNCon) {
        this.termsNCon = termsNCon;
    }

    public void addFragment(FragmentManager fragmentManager) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
       ft.add(R.id.container, new SignInFragment());
        ft.commit();
    }

    public Observable<GetOtpApiResponse> getOtpButton() {
        return signInModel.getOtp(phoneNo, getCountryCode());
    }

    public Observable<GetOtpApiResponse> verifyOtpButton(String otp) {
        return signInModel.verifyOtp(phoneNo, otp, userType, getCountryCode());
    }

//    public Observable<GetOtpApiResponse> sendExtraDetails() {
//        return signInModel.saveExtraDetails(getUserId(), userName, userEmail);
//    }

    public MutableLiveData<STATUS> validateData() {
        MutableLiveData<STATUS> statusMutableLiveData = new MutableLiveData<>();


        if (getUserEmail()== null ||getUserEmail().isEmpty()) {
            statusMutableLiveData.setValue(STATUS.EMPTY);

            }
        else if(getUserEmail()!=null && !getUserEmail().isEmpty())
        {
            validateEmail(userEmail);
            }


        /*else if (phoneNo.length() < 9) {
            statusMutableLiveData.setValue(STATUS.INVALID);
        } else if (!validatePhone()) {
            statusMutableLiveData.setValue(STATUS.INVALID);
        } *//* else if (getUserfirstname() == null ) {
            statusMutableLiveData.setValue(STATUS.EMPTY);
        }
        else if(getUserlastName()==null)
        {
            statusMutableLiveData.setValue(STATUS.EMPTY);

            }
            else if (getTermsNCon() == null || getTermsNCon().isEmpty()) {
            statusMutableLiveData.setValue(STATUS.TERMnCON);
        }*/
        else if(getPassword()==null&&getPassword().isEmpty())
        {
            statusMutableLiveData.setValue(STATUS.INVALID_PASSWORD);

        }


        else {
            statusMutableLiveData.setValue(STATUS.VALIDATED);

        }
        return statusMutableLiveData;
    }

    private boolean validatePhone() {
        return Pattern.matches("5\\d{8}", phoneNo);
    }


    public MutableLiveData<STATUS> validateOtp(String enteredOtp) {
        MutableLiveData<STATUS> liveData = new MutableLiveData<>();
        if (enteredOtp == null || enteredOtp.isEmpty()) {
            liveData.setValue(STATUS.EMPTY);
        } else if (enteredOtp.length() < 4 || !enteredOtp.equals(getOtp())) {
            liveData.setValue(STATUS.INVALID);
        } else {
            liveData.setValue(STATUS.VALIDATED);
        }
        return liveData;
    }

//    public MutableLiveData<ExtraDetailsFragment.Status> validateExtraData() {
//        MutableLiveData<ExtraDetailsFragment.Status> liveData = new MutableLiveData<>();
//        if (userName.trim().isEmpty() || userEmail.trim().isEmpty()) {
//            liveData.setValue(ExtraDetailsFragment.Status.EMPTY);
//        } else if (!validateEmail(userEmail)) {
//            liveData.setValue(ExtraDetailsFragment.Status.INVALID_EMAIL);
//        } else {
//            liveData.setValue(ExtraDetailsFragment.Status.VALIDATED);
//        }
//        return liveData;
//    }

    private boolean validateEmail(String email) {
        return Pattern.matches("(\\S)+@(\\S)+.(\\S)+", email);
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setSignInModel(SignInModel signInModel) {
        this.signInModel = signInModel;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
