package com.camellia.soorty.login.view;

import android.app.Activity;
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
import com.camellia.soorty.databinding.SignInFragmentBinding;
import com.camellia.soorty.login.model.GetOtpApiResponse;
import com.camellia.soorty.login.model.SignInModel;
import com.camellia.soorty.login.viewmodel.LoginViewModel;
import com.camellia.soorty.utills.BaseFragment;

import org.json.JSONException;

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

public class SignInFragment extends BaseFragment<SignInFragmentBinding, LoginViewModel> {
    private static final int RC_SIGN_IN = 4104;
    private LoginViewModel signInFragmentViewModel;
    private SignInFragmentBinding signinFragmentBinding;
    @Inject
    public SignInModel signInModel;
    private CompositeDisposable disposable = new CompositeDisposable();

    List<String> permissionNeeds = Arrays.asList("email");
//    CallbackManager mCallbackManager;

    @Override
    public int getLayoutid() {
        return R.layout.signin_fragment;
    }

    @Override
    public int getViewModelBindingVar() {
        return BR.viewModel;
    }

    @Override
    public LoginViewModel getViewModel() {
        return signInFragmentViewModel;
    }

    @Override
    public void onRetryClicked() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signInFragmentViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(LoginViewModel.class);
//        mCallbackManager = CallbackManager.Factory.create();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signinFragmentBinding = getBindingLayout();
        signInFragmentViewModel.setSignInModel(signInModel);
/*
        signinFragmentBinding.radioButton3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
                signInFragmentViewModel.setTermsNCon("TermsNCon");
            else
                signInFragmentViewModel.setTermsNCon("");
        });*/

signinFragmentBinding.loginLoginButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        validateData();
    }
});

signinFragmentBinding.tvSignUpSignIn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        replaceFragmentForSignUp();

    }
});

      /*  signinFragmentBinding.login_loginButton.setOnClickListener(view1 -> {
            validateData();
        });
        signinFragmentBinding.textView4.registerCarrierNumberEditText(signinFragmentBinding.editText);
        signinFragmentBinding.textView4.setNumberAutoFormattingEnabled(true);
        signInFragmentViewModel.countryCode = signinFragmentBinding.textView4.getSelectedCountryCode();
        signinFragmentBinding.textView4.setOnCountryChangeListener(() -> {
            signInFragmentViewModel.countryCode = signinFragmentBinding.textView4.getSelectedCountryCode();
        });*/
//        signinFragmentBinding.materialButton.setOnClickListener(view12 -> {
//            if (signInFragmentViewModel.getUserType() == null || signInFragmentViewModel.getUserType().isEmpty()) {
//                Toast.makeText(getActivity(), "Please select user type first", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile", "user_friends"));
//        });

//        signinFragmentBinding.materialButton2.setOnClickListener(view13 -> {
//            if (signInFragmentViewModel.getUserType() == null || signInFragmentViewModel.getUserType().isEmpty()) {
//                Toast.makeText(getActivity(), "Please select user type first", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//        });

    }



    private void replaceFragmentForSignUp() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, new SignUp_Fragment());
        ft.commit();
    }

    private void validateData() {
        signInFragmentViewModel.validateData().observe(this, status -> {
            Log.e("str", "hj");
            switch (status) {
                case VALIDATED:

//                    getOtp();
                    //if(signinFragmentBinding.textView4.isValidFullNumber()) getOtp();
                    // else Toast.makeText(getActivity(), "Please enter a valid mobile no.", Toast.LENGTH_SHORT).show();
                    break;
                case EMPTY:
                    Toast.makeText(getActivity(), "Field will not be empty", Toast.LENGTH_SHORT).show();
                    break;

                case INVALID_PASSWORD:
                    Toast.makeText(getActivity(), "Password will not be", Toast.LENGTH_SHORT).show();

                    break;

                    case TERMnCON:
                    Toast.makeText(getActivity(), "Please select the terms and condition", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*private void getOtp() {
        signInFragmentViewModel.getOtpButton().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<GetOtpApiResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                showProgress();

            }

            @Override
            public void onNext(GetOtpApiResponse getOtpApiResponse) {
                Log.e("msg", "otp : " + getOtpApiResponse.toString());
                if (getOtpApiResponse.isStatus()) {
                    signInFragmentViewModel.isSocial = false;
                    signInFragmentViewModel.setOtp(getOtpApiResponse.getOtp());
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                    ft.replace(R.id.container, new VerifiOtpFragment(false));
                    ft.addToBackStack(null);
                    ft.commit();
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
        if (requestCode == RC_SIGN_IN && resultCode == Activity.RESULT_OK) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.

        }
        if (resultCode == Activity.RESULT_OK) {
//            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }


    }



    @Override
    public void onStop() {
        super.onStop();
        signInFragmentViewModel.setTermsNCon("");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())

        {

            case R.id.login_loginButton:
                validateData();

                break;
                default:
                    break;


        }

    }*/
}
