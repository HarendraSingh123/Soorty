package com.camellia.soorty.utills;


import com.camellia.soorty.di.components.DaggerNetComponent;


import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class MyApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerNetComponent.builder().create(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    }

