package com.camellia.soorty.utills;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.camellia.soorty.Repos.MyAppPref;
import com.camellia.soorty.callback.ErrorHandlingCallBack;
import com.camellia.soorty.login.view.Login;

import java.net.ConnectException;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import retrofit2.HttpException;

public abstract class BaseFragment<T extends ViewDataBinding, VM extends ViewModel> extends Fragment {
    private T bindingLayout;
    private VM viewModel;
    private CustomProgress customProgress;
    private ErrorHandlingCallBack errorHandlingCallBack;
    @Inject
    MyAppPref myAppPref;

    public abstract int getLayoutid();

    public T getBindingLayout() {
        return bindingLayout;
    }

    public abstract int getViewModelBindingVar();
    public abstract VM getViewModel();
    public abstract void onRetryClicked();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindingLayout = DataBindingUtil.inflate(inflater, getLayoutid(),container,false);
        this.viewModel=viewModel==null?getViewModel():viewModel;
        bindingLayout.setVariable(getViewModelBindingVar(), viewModel);
        customProgress=new CustomProgress(getActivity());
        if(getActivity() instanceof ErrorHandlingCallBack) errorHandlingCallBack= (ErrorHandlingCallBack) getActivity();
        return bindingLayout.getRoot();
    }

    public void showProgress(){
        customProgress.show();
    }

    public void hideProgress(){
        customProgress.dismiss();
    }

    public void handleUnauthorised(){
        myAppPref.clearMyPref();
        Intent intent=new Intent(getActivity(),Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        ActivityCompat.finishAffinity(getActivity());
        getActivity().finish();
        Toast.makeText(getActivity(),"Session token expired.",Toast.LENGTH_SHORT).show();
    }

    public void handleException(Throwable e){
        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            if(exception.code()==401){
                handleUnauthorised();
            }
            Log.e("error m ", "exception " + exception.code());
        }
        else if(e instanceof ConnectException){
            Snackbar snackbar = Snackbar
                    .make(getView(), "Internet connection is not connected or too weak.",Snackbar.LENGTH_INDEFINITE)
                    .setAction("RETRY", view -> {
                        onRetryClicked();
                    });
            snackbar.setActionTextColor(Color.RED);

            View sbView = snackbar.getView();
            TextView textView =   sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }
        else Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_SHORT).show();
    }

    public void handleExceptionOnDataSend(Throwable e){
        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            if(exception.code()==401){
                handleUnauthorised();
            }
            Log.e("error m ", "exception " + exception.code());
        }
        else if(e instanceof ConnectException){
           showNetworkError();
        }
        else Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_SHORT).show();
    }

    public void showNetworkError(){
        Toast.makeText(getActivity(),"Internet connection is not connected or too weak.",Toast.LENGTH_SHORT).show();
    }

    public ErrorHandlingCallBack getErrorHandlingCallBack() {
        return errorHandlingCallBack;
    }
}
