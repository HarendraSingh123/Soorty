package com.camellia.soorty.di.modules;

import android.app.Application;


import com.camellia.soorty.Repos.MyAppPref;
import com.camellia.soorty.utills.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides
    @Singleton
    Application provideContext(MyApplication application){
        return application;
    }

    @Provides
    @Singleton
    MyAppPref providePreference(Application application){
        return new MyAppPref(application);
    }
}
