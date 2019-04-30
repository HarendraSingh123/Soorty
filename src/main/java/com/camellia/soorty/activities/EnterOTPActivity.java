package com.camellia.soorty.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
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
import com.camellia.soorty.utills.CheckNetwork;
import com.camellia.soorty.utills.CustomProgress;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterOTPActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = EnterOTPActivity.class
            .getSimpleName();
    TextView tvphone_number,tvresendcode;
    EditText etdigit1,etdigit2,editdidgit3,editdigit4;
    String strOTPdigit1,strOTPdigit2,strOTPdigit3,strOTPdigit4,strfinalOTP;
    ApiClient apiClient;
    private ApiInterface apiInterface;
    Context mContext;
    String finalphoneno,phonenumber;
    String strServerOTP,strOTP,resentOTP,  userID;;
    ImageView imgbackOTP;
    public boolean resent = false;
    Intent intent;
    String f_name,l_name,email_id,mobile_no,password,access_token,devicetype;
      public CustomProgress customProgress;
      MyAppPref myAppPref;
      VerifyOtpUserModel verifyOtpUserModel;
      VerifyOTPMainModel verifyOTPMainModel;
      @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);
        mContext =EnterOTPActivity.this;
        customProgress=new CustomProgress(mContext);
        myAppPref=new MyAppPref(getApplicationContext());
        findviewbyId();
//        getOTP();
          intent=getIntent();
          f_name=intent.getStringExtra("first_name");
          l_name=intent.getStringExtra("last_name");
          email_id=intent.getStringExtra("email_id");
          mobile_no=intent.getStringExtra("phone_number");
          password=intent.getStringExtra("password");
          devicetype=intent.getStringExtra("device_type");
          strOTP=intent.getStringExtra("otp");

          etdigit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                strOTPdigit1= etdigit1.getText().toString();
                if (strOTPdigit1.length() <= 1) {
                    etdigit2.requestFocus();

                }

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etdigit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                strOTPdigit2= etdigit2.getText().toString();
                if (strOTPdigit2.length() <= 1) {
                    editdidgit3.requestFocus();

                }

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editdidgit3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                strOTPdigit3= editdidgit3.getText().toString();
                if (strOTPdigit3.length() <= 1) {
                    editdigit4.requestFocus();

                }

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editdigit4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                strOTPdigit4= editdigit4.getText().toString();


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        SpannableString spannableString = new SpannableString("Resend Code");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                etdigit1.setText("");
                etdigit2.setText("");
                editdidgit3.setText("");
                editdigit4.setText("");
                etdigit1.requestFocus();
                resendOTP();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);

            }
        };

        spannableString.setSpan(clickableSpan, 0, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF7D01")), 0, 11, 0);
        tvresendcode.setText(spannableString);
        tvresendcode.setMovementMethod(LinkMovementMethod.getInstance());
        tvresendcode.setHighlightColor(Color.TRANSPARENT);


    }
    public void showProgress(){
        customProgress.show();
    }

    public void hideProgress(){
        customProgress.dismiss();
    }

    public String getOTP()
        {
            strOTP=null;
            intent=getIntent();
            strOTP=intent.getStringExtra("otp");
            return  strOTP;
        }

        public void  findviewbyId()
   {
       etdigit1=findViewById(R.id.firstdigit_otp);
       etdigit2=findViewById(R.id.seconddigit_otp);
       editdidgit3=findViewById(R.id.thirddigit_otp);
       editdigit4=findViewById(R.id.fourthdigit_otp);
       tvresendcode=findViewById(R.id.tvresendcode_otp);
       imgbackOTP=findViewById(R.id.imgback_otp);

       }

       @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btncontinue_otp:
                //********************open dashboard here*********
                if (CheckNetwork.isInternetAvailable(getApplicationContext()))
                {
                    verifyOTP();

                }

                else
                {

                    Toast.makeText(mContext,"No Internet connection Found!",Toast.LENGTH_LONG).show();
                }

                break;

                case R.id.tvresendcode_otp:
                resendOTP();
                break;
                case R.id.imgback_otp:
                Intent intent=new Intent(mContext,SignUpActivity.class);
                   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   mContext.startActivity(intent);
                   break;

                default:
                    break;



        }

    }


    public  void openHomePage()
    {

        showProgress();

        apiInterface =
                apiClient.getClient().create(ApiInterface.class);

        Call<VerifyOtpUserModel> call = apiInterface.registerUser(f_name,l_name,email_id,mobile_no,devicetype,password);

//        Call<VerifyOTPMainModel> call = apiInterface.getOTP(Constants.STATIC_HEADER, strMobileNo, Constants.LOGIN_TYPE);
        call.enqueue(new Callback<VerifyOtpUserModel>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<VerifyOtpUserModel> call, Response<VerifyOtpUserModel> response) {
                Log.e("response status GET OTP API ", response.code() + "");

                if (response.code() == 200) {
                    {
                        if (response.body() != null) {

                            VerifyOtpUserModel  verifyOtpUserModel=response.body();
                            VerifyOTPMainModel verifyOTPMainModel=new VerifyOTPMainModel();

                            Log.d(TAG,response.code()+"");
                            userID=verifyOtpUserModel.getUserId();
                            myAppPref.setUserId(userID);
                            myAppPref.setEmail(email_id);
                            myAppPref.setMobile(mobile_no);
                            myAppPref.setfirstName(f_name);
                            myAppPref.setlastName(l_name);
                            Intent intent=new Intent(mContext,MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(mContext,"You successfully Registered",Toast.LENGTH_LONG).show();

                            }
                    }
                }
                else if (response.code() == 401) {
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
            public void onFailure(Call<VerifyOtpUserModel> call, Throwable t) {
               /* if (loading.isShowing()) {
                    loading.dismiss();
                }*/

               hideProgress();
                Log.e("on failure Enter OTP Activity ***************", t.toString());
            }
        });


        }
        public  void resendOTP()
        {
            resent = true;
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
                            strOTP = verifyOTPMainModel.getOtp();
                            Log.d("TAG",response.code()+"");
                            Log.e("Server OTP", strOTP + "");

                            Toast.makeText(mContext, "resent OTP is : " + response.body().getOtp(), Toast.LENGTH_SHORT).show();

                            //************************ Now verify user otp *******************************

                        }
                    }
                }
                else if (response.code() == 401) {
                    Intent intent = new Intent(mContext, MainActivity.class);
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
            public void onFailure(Call<VerifyOTPMainModel> call, Throwable t) {
                hideProgress();
                Log.e("on failure Sign Up Fragment ***************", t.toString());
            }
        });


        }
    @SuppressLint("LongLogTag")
    private boolean verifyOTP() {
        Log.e("Verify OTP function callled ", " OKKKKKKKK");
        strOTPdigit1 = etdigit1.getText().toString();
        strOTPdigit2 = etdigit2.getText().toString();
        strOTPdigit3 = editdidgit3.getText().toString();
        strOTPdigit4 = editdigit4.getText().toString();
        strfinalOTP =  strOTPdigit1 + strOTPdigit2 + strOTPdigit3  + strOTPdigit4 ;
        Log.e("final otp", strfinalOTP );
        if (strfinalOTP.equalsIgnoreCase(strOTP)) {
            Log.d("otp verfied sucessfully",strfinalOTP);

            openHomePage();

            return true;
        } else {
            Toast.makeText(mContext, "OTP does not match", Toast.LENGTH_SHORT).show();
            return false;
        }
    }





    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
