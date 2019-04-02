package com.camellia.soorty.login.model;



import com.camellia.soorty.Repos.MyAppPref;
import com.camellia.soorty.di.ApiInterface;

import dagger.Module;
import dagger.Provides;

@Module
public class DISignInModule {
    @Provides
    public SignInModel provideSignInModel(ApiInterface apiInterface, MyAppPref myAppPref){
        return new SignInModel(apiInterface,myAppPref);
    }



}
