package com.camellia.soorty.profile.model;

import com.camellia.soorty.REST.ApiInterface;
import com.camellia.soorty.Repos.MyAppPref;
import com.camellia.soorty.login.model.SignInModel;

import dagger.Module;
import dagger.Provides;

@Module
public class DIProfileModule {

    @Provides
    ProfileModel provideProfileModel(ApiInterface apiInterface, MyAppPref appPref)
    {
        return new ProfileModel(apiInterface,appPref);


        }


}
