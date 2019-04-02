package com.camellia.soorty.di.modules;




import com.camellia.soorty.services.MyFirebaseMessagingService;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ServiceBuilder {
//    @ContributesAndroidInjector
//    abstract GeofenceTransitionsIntentService bindSevice();

    @ContributesAndroidInjector
    abstract MyFirebaseMessagingService bindService();
}
