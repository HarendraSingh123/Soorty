package com.camellia.soorty.profile.viewmodel;

import com.camellia.soorty.login.model.SignInModel;
import com.camellia.soorty.profile.model.ProfileModel;
import com.camellia.soorty.utills.BaseViewModel;

public class UserProfileViewModel extends BaseViewModel {


    public ProfileModel profileModel;


    public ProfileModel getProfileModel() {
        return profileModel;
    }

    public void setProfileModel(ProfileModel profileModel) {
        this.profileModel = profileModel;
    }


}
