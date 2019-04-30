package com.camellia.soorty.login.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;


import com.android.databinding.library.baseAdapters.BR;
import com.camellia.soorty.R;
import com.camellia.soorty.Repos.MyAppPref;

import com.camellia.soorty.databinding.LoginBinding;
import com.camellia.soorty.login.viewmodel.LoginViewModel;
import com.camellia.soorty.utills.BaseActivity;


import javax.inject.Inject;


public class Login extends BaseActivity<LoginBinding, LoginViewModel> {
    private LoginViewModel loginViewModel;

    @Inject
    MyAppPref myAppPref;

    @Override
    public LoginViewModel getViewModel() {
        return loginViewModel;
    }

    @Override
    public int getViewModelBindingVar() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_login;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        FragmentManager fragmentManager = getSupportFragmentManager();
        loginViewModel.addFragment(fragmentManager);
    }
}
