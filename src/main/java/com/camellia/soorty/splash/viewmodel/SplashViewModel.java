package com.camellia.soorty.splash.viewmodel;

import android.arch.lifecycle.ViewModel;


public class SplashViewModel extends ViewModel {
    private String mera;

    public SplashViewModel() {
        mera="Splash";
    }

    public String getMera(){
        return mera;
    }

    public void setMera(String value){
        this.mera=value;
    }
}
