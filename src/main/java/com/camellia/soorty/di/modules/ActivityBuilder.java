package com.camellia.soorty.di.modules;



import com.camellia.soorty.login.view.Login;
import com.camellia.soorty.splash.view.Splash;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract Splash bindSplash();

    @ContributesAndroidInjector(modules = LoginFragmentBuilder.class)
    abstract Login binLogin();
}
