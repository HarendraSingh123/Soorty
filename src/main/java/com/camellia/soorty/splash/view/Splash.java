package com.camellia.soorty.splash.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import com.camellia.soorty.BR;
import com.camellia.soorty.MainActivity;
import com.camellia.soorty.R;
import com.camellia.soorty.Repos.MyAppPref;
import com.camellia.soorty.databinding.SplashBinding;
import com.camellia.soorty.login.view.Login;
import com.camellia.soorty.splash.viewmodel.SplashViewModel;
import com.camellia.soorty.utills.BaseActivity;

import java.util.Calendar;

import javax.inject.Inject;


public class Splash extends BaseActivity<SplashBinding, SplashViewModel> {

    private SplashBinding splashBinding;
    private SplashViewModel splashViewModel;
    private Runnable runnable;
    private Handler handler;
    private long timer;
    private long TIME_OUT_TIME = 1000;
    @Inject
    MyAppPref myAppPref;

    @Override
    public SplashViewModel getViewModel() {
        return splashViewModel;
    }

    @Override
    public int getViewModelBindingVar() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_splash;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
        splashBinding = getBindingLayout();
        timer = Calendar.getInstance().getTimeInMillis();
        runnable = () -> {
            timer = 0;
            startNewActivity();

        };
        handler = new Handler();
        handler.postDelayed(runnable, TIME_OUT_TIME);
        splashBinding.imageView2.setOnClickListener(view -> {
            splashViewModel.setMera("Harendra");
            System.out.println("getting view model string onclick : " + splashViewModel.getMera());
        });
        System.out.println("getting view model string : " + splashViewModel.getMera());


    }



    private void startNewActivity() {
        if (myAppPref.getAccessToken() != null) {
//            initializeFramework();
//            loginMe();
        } else {
            startActivity(new Intent(Splash.this, Login.class));
            finish();
        }
    }






    /*private void loginMe() {
        showProgress();
        Log.d("login " + myAppPref.getUserName().replaceAll(" ", "").toLowerCase() + "utter", "" + QB_PASSWORD);
        QBUser qbUser = new QBUser();
        qbUser.setLogin("utter"+myAppPref.getUserId());
        qbUser.setPassword(QB_PASSWORD);
        QBUsers.signIn(qbUser).performAsync(new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser qbUser2, Bundle bundle) {
                Log.d("hey ", "Login successfully");
                hideProgress();
                myAppPref.setQBUserId(qbUser2.getId());
                qbUser.setId(qbUser2.getId());
                ((MyApplication)getApplicationContext()).createChatSession(qbUser);
                startMainView();
            }

            @Override
            public void onError(QBResponseException e) {
                hideProgress();
                startMainView();
                Log.e("Hey in Error", " " + e.getMessage());
            }


        });
    }

    private void startMainView() {
        Intent intent;
        if (myAppPref.getUserType().equals(AppConstant.USERTYPE_ORG)) {
            intent = new Intent(this, OrgDashboard.class);
            startActivity(intent);
            finish();
        } else {
            intent = new Intent(Splash.this, CustomerHome.class);
            startActivity(intent);
            finish();
        }
    }

    private void initializeFramework() {
        QBSettings.getInstance().init(getApplicationContext(), BuildConfig.QB_APP_ID, BuildConfig.QB_AUTH_KEY, BuildConfig.QB_AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(BuildConfig.QB_ACCOUNT_KEY);
    }*/
}
