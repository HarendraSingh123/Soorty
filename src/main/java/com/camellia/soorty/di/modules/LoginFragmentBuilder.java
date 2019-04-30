package com.camellia.soorty.di.modules;



import com.camellia.soorty.login.model.DISignInModule;
import com.camellia.soorty.login.view.SignInFragment;
import com.camellia.soorty.login.view.SignUp_Fragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LoginFragmentBuilder {
    @ContributesAndroidInjector(modules = DISignInModule.class)
    abstract SignInFragment signInFragment();



//    @ContributesAndroidInjector
//    abstract VerifiOtpFragment verifiOtpFragment();
//
    @ContributesAndroidInjector
    abstract SignUp_Fragment signUp_fragment();


}
