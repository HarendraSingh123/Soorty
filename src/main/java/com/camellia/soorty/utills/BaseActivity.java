package com.camellia.soorty.utills;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.camellia.soorty.Repos.MyAppPref;
import com.camellia.soorty.login.view.Login;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<T extends ViewDataBinding, VM extends ViewModel> extends DaggerAppCompatActivity {

    private T bindingVar;
    private VM viewModel;
    private CustomProgress customProgress;
    @Inject
    MyAppPref myAppPref;

    public abstract VM getViewModel();

    public abstract int getViewModelBindingVar();

    public abstract int getLayoutid();

    public T getBindingLayout() {
        return bindingVar;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        bindingVar = DataBindingUtil.setContentView(this, getLayoutid());
        this.viewModel=viewModel==null?getViewModel():viewModel;
        bindingVar.setVariable(getViewModelBindingVar(), viewModel);
        customProgress=new CustomProgress(this);
    }
    public void showProgress(){
        customProgress.show();
    }

    public void hideProgress(){
        customProgress.dismiss();
    }
    public void handleUnauthorision(){
        myAppPref.clearMyPref();
        Intent intent=new Intent(this,Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        ActivityCompat.finishAffinity(this);
        finish();
        Toast.makeText(this,"Session token expired.",Toast.LENGTH_SHORT).show();
    }

    public void showNetworkErroe(){
        Toast.makeText(this,"Internet connection is not connected or too weak.",Toast.LENGTH_SHORT).show();
    }
}
