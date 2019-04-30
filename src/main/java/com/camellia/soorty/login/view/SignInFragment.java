package com.camellia.soorty.login.view;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.camellia.soorty.BR;

import com.camellia.soorty.R;
import com.camellia.soorty.REST.ApiClient;
import com.camellia.soorty.REST.ApiInterface;
import com.camellia.soorty.Repos.MyAppPref;
import com.camellia.soorty.activities.MainActivity;
import com.camellia.soorty.activities.SignUpActivity;
import com.camellia.soorty.databinding.SignInFragmentBinding;
import com.camellia.soorty.login.model.SignInModel;
import com.camellia.soorty.login.viewmodel.LoginViewModel;
import com.camellia.soorty.models.Data;
import com.camellia.soorty.models.HomePageData;
import com.camellia.soorty.utills.BaseFragment;
import com.camellia.soorty.utills.CheckNetwork;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInFragment extends BaseFragment<SignInFragmentBinding, LoginViewModel>  {

    private static final int RC_SIGN_IN = 4104;
    private LoginViewModel signInFragmentViewModel;
    private SignInFragmentBinding signinFragmentBinding;
    @Inject
    public SignInModel signInModel;
    private CompositeDisposable disposable = new CompositeDisposable();
    MyAppPref myAppPref;
    ApiInterface apiInterface;
    ApiClient apiClient;
    Context mContext;
    HomePageData HomePageData;
    EditText editTextEmail, editTextPassword;
    TextView tvshowpassword;

    String email, _mobile_no, password, access_token;


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
        myAppPref = new MyAppPref(getActivity());
        mContext = getContext();
        access_token = null;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signinFragmentBinding = getBindingLayout();
        signInFragmentViewModel.setSignInModel(signInModel);

        editTextEmail = view.findViewById(R.id.login_useremailTextField);
        editTextPassword = view.findViewById(R.id.login_passwordTextField);
        tvshowpassword = view.findViewById(R.id.tv_show_password_sign_in);


        tvshowpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (tvshowpassword.getText().equals("Show")) {
                    editTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    password = editTextPassword.getText().toString();
                    isPasswordValid(password);
                    if (isPasswordValid(password)) {
                        tvshowpassword.setText("Hide");

                    }

                } else if (tvshowpassword.getText().equals("Hide") && isPasswordValid(password)) {

                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    tvshowpassword.setText("Show");


                }

            }
        });

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
//                validateData();

                attemptLogin();
            }
        });

        signinFragmentBinding.tvSignUpSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//        replaceFragmentForSignUp();

                Context context = view.getContext();
                Intent intent = new Intent(context, SignUpActivity.class);
                context.startActivity(intent);

            }
        });


    }


    public void loginUser() {

//        final ProgressDialog loading = ProgressDialog.show(this, "", "Please wait...", false, false);


        showProgress();

        apiInterface =
                apiClient.getClient().create(ApiInterface.class);

        Call<HomePageData> call = apiInterface.loginuser(email, password);

//        Call<VerifyOTPMainModel> call = apiInterface.getOTP(Constants.STATIC_HEADER, strMobileNo, Constants.LOGIN_TYPE);
        call.enqueue(new Callback<HomePageData>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<HomePageData> call, Response<HomePageData> response) {
                Log.e("response status GET OTP API ", response.code() + "");

                if (response.code() == 200) {
                    {
                        if (response.body() != null) {

                            HomePageData homePageData = response.body();

                            Data data = homePageData.getData();

                              /*  Log.e("the response body",HomePageData.toString());
                                String access_token=HomePageData.getAccess_token();
                                Log.e("the access_token",access_token);*/

                            myAppPref.setAccessToken(data.getAccessToken());
                            myAppPref.setUserId(data.getUserId().toString());
                            Log.d("the access_token", data.getAccessToken());
                            Log.d("the user_id", data.getUserId().toString());

                            Intent intent = new Intent(mContext, MainActivity.class);

                            startActivity(intent);
                            Toast.makeText(mContext, homePageData.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                } else if (response.code() == 401) {
                    Intent intent = new Intent(mContext, Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
//                    mySharedPrefManagerInstance.clearPref();
                } else if (response.code() == 404) {
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.something_went_wrong_try_again) + "", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 500) {
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.internal_server_error) + "", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.something_went_wrong_try_again) + "", Toast.LENGTH_SHORT).show();
                }

                hideProgress();
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<HomePageData> call, Throwable t) {
               /* if (loading.isShowing()) {
                    loading.dismiss();
                }*/

                hideProgress();
                Log.e("on failure Sign in Fragment ***************", t.toString());
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

//                    HomePageData=myAppPref.getUser();
//                    email=HomePageData.getUserEmail();
//                    _mobile_no=HomePageData.getUserMobile();
//                    Log.d("the user values are",email+""+_mobile_no);
//                    loginUser();


//                    getOtp();
                    //if(signinFragmentBinding.textView4.isValidFullNumber()) getOtp();
                    // else Toast.makeText(getActivity(), "Please enter a valid mobile no.", Toast.LENGTH_SHORT).show();
                    break;
                case EMPTY:
                    Toast.makeText(getActivity(), "Field will not be empty", Toast.LENGTH_SHORT).show();
                    break;

                case INVALID_PASSWORD:
                    Toast.makeText(getActivity(), "Password will not be empty", Toast.LENGTH_SHORT).show();

                    break;


                default:
                    break;
            }
        });
    }

    private void attemptLogin() {
        // Reset errors.
        try {
            editTextEmail.setError(null);
            editTextPassword.setError(null);
            // Store values at the time of the login attempt.
            email = editTextEmail.getText().toString();
            password = editTextPassword.getText().toString();

            boolean cancel = false;
            View focusView = null;
            // Check for a valid email address.
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getContext(), "Please enter email address", Toast.LENGTH_LONG).show();

            } else {
                if (!TextUtils.isEmpty(email)) {
                    email = editTextEmail.getText().toString();
                    isEmailValid(email);
                }
                {
                    if (TextUtils.isEmpty(password)) {
                        Toast.makeText(getContext(), "Password can not be empty", Toast.LENGTH_LONG).show();
                        Log.d("  when ++++++", "password empty");

                    } else {
                        if (!TextUtils.isEmpty(password)) {
                            Log.d("when ++++", "password not empty");
                            password = editTextPassword.getText().toString();
                            isPasswordValid(password);

                        }
                        if (CheckNetwork.isInternetAvailable(getContext())) {
                            loginUser();
                        } else {
                            Toast.makeText(getContext(), "No Internet connection found !!", Toast.LENGTH_LONG).show();
                        }


                    }

                }

            }

        }


        // perform the user login attempt.

        catch (Exception e) {
            e.printStackTrace();

        }
    }




    public  boolean isEmailValid(String email)
    {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
        {
            return true;
        }
        else{
            Toast.makeText(getContext(), "Please enter valid email address", Toast.LENGTH_LONG).show();
            return false;
        }
    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        if (password.length() == 0) {
            Toast.makeText(getContext(), "Password can not be empty", Toast.LENGTH_LONG).show();

            return false;
        } else if (password.length() < 4) {
            Toast.makeText(getContext(), "Please enter at least 4 characters,", Toast.LENGTH_LONG).show();

            return false;

        } else {

            return true;

        }

    }




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

