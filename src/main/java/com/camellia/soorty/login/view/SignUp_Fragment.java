package com.camellia.soorty.login.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.camellia.soorty.BR;
import com.camellia.soorty.R;

import com.camellia.soorty.databinding.SignUpFragmentBinding;

import com.camellia.soorty.login.model.GetOtpApiResponse;
import com.camellia.soorty.login.viewmodel.LoginViewModel;
import com.camellia.soorty.utills.BaseFragment;

import java.net.ConnectException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class SignUp_Fragment extends BaseFragment<SignUpFragmentBinding,LoginViewModel> implements View.OnClickListener {

    public enum STATUS {
        EMPTY, VALIDATED, INVALID, EMPTY_USER_TYPE, TERMnCON
    }


//    private static final int RC_SIGN_IN = 4104;

    @Inject

    private CompositeDisposable disposable = new CompositeDisposable();

    List<String> permissionNeeds = Arrays.asList("email");
    private LoginViewModel signUpFragementViewModel;
    private SignUpFragmentBinding signUpFragmentBinding;

    public String phoneNo = "";
    private String userType = "";
    private String otp = "";
    public String userName = "";
    public String userEmail = "";
    public String countryCode = "";
    private int userId;
    public boolean isSocial = false;
    public String termsNCon = "";

    @Inject
    public SignUp_Fragment()
    {


    }


    @Override
    public LoginViewModel getViewModel() {
        return signUpFragementViewModel;
    }

    @Override
    public int getLayoutid() {
        return R.layout.sign_up_fragment;
    }

    @Override
    public SignUpFragmentBinding getBindingLayout() {
        return super.getBindingLayout();
    }

    @Override
    public int getViewModelBindingVar() {
      return BR.viewModel;
    }

    @Override
    public void onRetryClicked() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signUpFragementViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(LoginViewModel.class);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signUpFragmentBinding = getBindingLayout();


        signUpFragmentBinding.signupSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 getOtp();
//               validateData();
            }
        });

    }

    private void validateData() {
       signUpFragementViewModel.validateData().observe(this, status -> {
            Log.e("str", "hj");
            switch (status) {
                case VALIDATED:

                    getOtp();
                    //if(signinFragmentBinding.textView4.isValidFullNumber()) getOtp();
                    // else Toast.makeText(getActivity(), "Please enter a valid mobile no.", Toast.LENGTH_SHORT).show();
                    break;
                case EMPTY:
                    Toast.makeText(getActivity(), "Field will not be empty", Toast.LENGTH_SHORT).show();
                    break;

                case INVALID_PASSWORD:
                    Toast.makeText(getActivity(), "Password will not be empty", Toast.LENGTH_SHORT).show();

                    break;
                case TERMnCON:
                    Toast.makeText(getActivity(), "Please select the terms and condition", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getOtp() {
        signUpFragementViewModel.getOtpButton().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<GetOtpApiResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                showProgress();

            }

            @Override
            public void onNext(GetOtpApiResponse getOtpApiResponse) {
                Log.e("msg", "otp : " + getOtpApiResponse.toString());
                if (getOtpApiResponse.isStatus()) {
//                    signInFragmentViewModel.isSocial = false;
                    signUpFragementViewModel.setOtp(getOtpApiResponse.getOtp());
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                    ft.replace(R.id.container, new VerifiOtpFragment(false));
//                    ft.addToBackStack(null);
//                    ft.commit();
                } else {
                    Toast.makeText(getActivity(), getOtpApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof HttpException) {
                    HttpException exception = (HttpException) e;
                    Log.e("error m ", "exception " + exception.code());
                } else if (e instanceof ConnectException) {
                    showNetworkError();
                }
                hideProgress();
            }

            @Override
            public void onComplete() {
                hideProgress();

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("test the on result", resultCode + " received");
       /* if (requestCode == RC_SIGN_IN && resultCode == Activity.RESULT_OK) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.

        }
        if (resultCode == Activity.RESULT_OK) {
//            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }*/


    }



    @Override
    public void onStop() {
        super.onStop();
       signUpFragementViewModel.setTermsNCon("");
    }

    @Override
    public void onClick(View view) {

    }
}
