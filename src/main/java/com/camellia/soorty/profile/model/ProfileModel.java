package com.camellia.soorty.profile.model;

import com.camellia.soorty.REST.ApiInterface;
import com.camellia.soorty.Repos.MyAppPref;

public class ProfileModel {

   private ApiInterface apiInterface;
   private MyAppPref myAppPref;

    public  ProfileModel(ApiInterface apiInterface,MyAppPref appPref)
    {
        this.apiInterface=apiInterface;
        this.myAppPref=appPref;

    }
}
