package com.camellia.soorty.di.components;


import com.camellia.soorty.di.modules.ActivityBuilder;
import com.camellia.soorty.di.modules.AppModule;
import com.camellia.soorty.di.modules.NetModule;
import com.camellia.soorty.di.modules.ServiceBuilder;
import com.camellia.soorty.utills.MyApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,AppModule.class,NetModule.class,ActivityBuilder.class,ServiceBuilder.class})
public interface NetComponent extends AndroidInjector<MyApplication> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MyApplication> {
    }
}
