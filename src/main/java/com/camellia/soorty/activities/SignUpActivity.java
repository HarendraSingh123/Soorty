package com.camellia.soorty.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.camellia.soorty.R;
import com.camellia.soorty.REST.ApiClient;
import com.camellia.soorty.REST.ApiInterface;
import com.camellia.soorty.Repos.MyAppPref;
import com.camellia.soorty.login.view.Login;
import com.camellia.soorty.models.VerifyOTPMainModel;
import com.camellia.soorty.models.VerifyOtpUserModel;
import com.camellia.soorty.tnc.view.Termsandconditions;
import com.camellia.soorty.utills.CheckNetwork;
import com.camellia.soorty.utills.CustomProgress;
import com.camellia.soorty.utills.MyApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG=SignUpActivity.class.getSimpleName();
    ApiInterface apiInterface;
    ApiClient apiClient;
    private Context mContext;
    private String strMobileNo;
    private String strServerOTP;
    private String  emailId,firstName,lastName,password;
    EditText et_first_name,et_last_name,et_email_address,et_mobile_number,et_password;
    CheckBox checktnc;
    Button btn_sign_up;
    MaterialButton materialButton_google,materialButton_facebook;
    TextView tv_sign_in,tvtnc,tvback_signup,tv_show_password,tvshowpassword;
    ImageView img_back;
    CustomProgress customProgress;
    VerifyOtpUserModel verifyOtpUserModel;
    MyAppPref myAppPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mContext=SignUpActivity.this;
        customProgress=new CustomProgress(mContext);
        verifyOtpUserModel=new VerifyOtpUserModel();
        myAppPref=new MyAppPref(getApplicationContext());
        findviewsbyId();
        String tncstring=tvtnc.getText().toString();
        SpannableString content = new SpannableString(tncstring);
        content.setSpan(new UnderlineSpan(), 0, tncstring.length(), 0);
        tvtnc.setText(content);
        checktnc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    checktnc.setBackgroundResource(R.drawable.checked_icon);

                    }
                    else
                        {
                    checktnc.setBackgroundResource(R.drawable.circle);

                    }
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,Login.class));
                finish();
            }
        });
    }

    private void findviewsbyId()
    {
        et_first_name=findViewById(R.id.et_first_name);
        et_last_name=findViewById(R.id.et_last_name_sign_up);
        et_mobile_number=findViewById(R.id.et_mobile_number_sign_up);
        et_password=findViewById(R.id.et_passwrod_sign_up);
        checktnc=findViewById(R.id.check_agree_tnc_sign_up);
        btn_sign_up=findViewById(R.id.signup_signupButton);
        tv_sign_in=findViewById(R.id.tv_sign_in_sign_up);
        et_email_address=findViewById(R.id.et_email_addresss_sign_up);
        tvtnc=findViewById(R.id.tv_terms_anc_conditions);
        img_back=findViewById(R.id.img_back_sign_up);
        tvback_signup=findViewById(R.id.tv_back_soorty_sign_up);
        tvshowpassword=findViewById(R.id.tv_show_password_sign_up);

        }

        public void showProgress()
        {
            customProgress.show();

        }

        public void hideProgress()
        {
            customProgress.hide();

        }


    public  void startnewactivity()
    {

        startActivity(new Intent(SignUpActivity.this,Termsandconditions.class));
        finish();




    }

    @Override
    public void onClick(View view) {
            Intent intent;

        switch (view.getId())
        {
            case R.id.signup_signupButton:
                Log.d(TAG, strMobileNo + "");

                ValidateData();

               /* if (CheckNetwork.isInternetAvailable(getApplicationContext())) {

                    if(ValidateData())

                    {
                        Log.e("Hit api now ", " okkk ");
                        sendOtp();
                    }
                    else
                    {

                        //
                    }

                }*/

                break;

            case R.id.tv_sign_in_sign_up:
                intent=new Intent(SignUpActivity.this,Login.class);
                startActivity(intent);
                break;

            case R.id.img_back_sign_up:
                 intent=new Intent(SignUpActivity.this,Login.class);
                  startActivity(intent);
                  finish();
                break;


            case R.id.tv_show_password_sign_up:
                    if (tvshowpassword.getText().equals("Show")) {
                        et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        password = et_password.getText().toString();
                        isPasswordValid(password);
                        if (isPasswordValid(password)) {
                            tvshowpassword.setText("Hide");

                        }

                    }
                    else if (tvshowpassword.getText().equals("Hide") && isPasswordValid(password)) {

                        et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        tvshowpassword.setText("Show");


                    }

                    break;
            case R.id.tv_terms_anc_conditions:
                startnewactivity();
                    default:
                    break;



        }

    }


    private static boolean isValidMobile(String mobile)
    {
        return !TextUtils.isEmpty(mobile)&& Patterns.PHONE.matcher(mobile).matches();
    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        if (password.length() == 0) {
            Toast.makeText(mContext, "Password can not be empty", Toast.LENGTH_LONG).show();

            return false;
        } else if (password.length() < 4) {
            Toast.makeText(mContext, "Please enter at least 4 characters", Toast.LENGTH_LONG).show();

            return false;

        } else {

            return true;

        }

    }
    private void sendOtp() {

//        final ProgressDialog loading = ProgressDialog.show(this, "", "Please wait...", false, false);

        showProgress();


        apiInterface =
                apiClient.getClient().create(ApiInterface.class);

        Call<VerifyOTPMainModel> call = apiInterface.receivedOTP();

//        Call<VerifyOTPMainModel> call = apiInterface.getOTP(Constants.STATIC_HEADER, strMobileNo, Constants.LOGIN_TYPE);
        call.enqueue(new Callback<VerifyOTPMainModel>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<VerifyOTPMainModel> call, Response<VerifyOTPMainModel> response) {
                Log.e("response status GET OTP API ", response.code() + "");

                if (response.code() == 200) {
                    {
                        if (response.body() != null) {

                            VerifyOTPMainModel  verifyOTPMainModel=response.body();

                           verifyOtpUserModel=new VerifyOtpUserModel();

                            strServerOTP = verifyOTPMainModel.getOtp();
                            Log.d("TAG",response.code()+"");

                            Log.e("Server OTP", strServerOTP + "");




/*

                            verifyOtpUserModel.setUserFirstname(firstName);
                            verifyOtpUserModel.setUserLastname(lastName);
                            verifyOtpUserModel.setUserEmail(emailId);
                            verifyOtpUserModel.setDeviceType("android");
                            verifyOtpUserModel.setUserMobile(strMobileNo);
                            verifyOtpUserModel.setPassword(password);
                            verifyOTPMainModel.setVerifyOtpUserModel(verifyOtpUserModel);


*/

                            Toast.makeText(mContext, "OTP is : " + response.body().getOtp(), Toast.LENGTH_SHORT).show();

                            myAppPref.setUserName(firstName);
                            myAppPref.setEmail(emailId);

                            Intent intent=new Intent(SignUpActivity.this,EnterOTPActivity.class);
                            intent.putExtra("phone_number",strMobileNo);
                            intent.putExtra("first_name",firstName);
                            intent.putExtra("last_name",lastName);
                            intent.putExtra("email_id",emailId);
                            intent.putExtra("password",password);
                            intent.putExtra("otp",strServerOTP);
                            intent.putExtra("device_type","android");
                            startActivity(intent);

                        }
                    }
                }
                else if (response.code() == 401) {
                 /*   Intent intent = new Intent(mContext, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    mySharedPrefManagerInstance.clearPref();*/
                } else if (response.code() == 404) {
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.something_went_wrong_try_again) + "", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 500) {
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.internal_server_error) + "", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.something_went_wrong_try_again) + "", Toast.LENGTH_SHORT).show();
                }


              /*  if (loading.isShowing()) {
                    loading.dismiss();
                }*/

              hideProgress();
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<VerifyOTPMainModel> call, Throwable t) {
               /* if (loading.isShowing()) {
                    loading.dismiss();
                }*/

               hideProgress();
                Log.e("on failure Sign Up Activity ***************", t.toString());
            }
        });
    }


    public  boolean ValidateData()
    {
        if(et_first_name.getText().toString().isEmpty() )
        {
            Toast.makeText(mContext,"Please enter first name",Toast.LENGTH_LONG).show();

        }
        else
        {
            if(et_last_name.getText().toString().isEmpty() && et_last_name.getText().toString().contains("")) {
                Toast.makeText(mContext, "Please enter last name", Toast.LENGTH_LONG).show();
            }
            else
            {
                if(et_email_address.getText().toString().isEmpty() && et_email_address.getText().toString().contains(""))
                {
                    Toast.makeText(mContext, "Please enter  email address", Toast.LENGTH_LONG).show();


                }
                else
                {
                    if(!et_email_address.getText().toString().isEmpty()) {

                        emailId = et_email_address.getText().toString();
                        isEmailValid(emailId);
                        if(et_mobile_number.getText().toString().length() < 9 && !et_mobile_number.getText().toString().startsWith("5"))
                        {
                            Log.d("on phone number","after gettting valid email");

                            Toast.makeText(mContext, "Please enter valid phone number,it should start with 5", Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            if(et_password.getText().toString().isEmpty())
                            {
                                Toast.makeText(mContext, "Password can not be empty", Toast.LENGTH_LONG).show();

                            }
                            else
                            {

                                firstName = et_first_name.getText().toString();
                                lastName = et_last_name.getText().toString();
                                strMobileNo = et_mobile_number.getText().toString();
                                emailId = et_email_address.getText().toString();
                                password = et_password.getText().toString();
                                if(checktnc.isChecked())
                                {
                                 if(CheckNetwork.isInternetAvailable(getApplicationContext())) {

                                     sendOtp();
                                 }

                                }
                                else
                                {
                                    Toast.makeText(mContext,"Please check the terms and conditions ",Toast.LENGTH_LONG).show();


                                }

                            }

                        }


                    }


                }

            }
        }

        return true;


    }


    public boolean isEmailValid(String email)
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

            Toast.makeText(mContext,"Please enter valid email address",Toast.LENGTH_LONG).show();
            return false;
        }
    }




}
