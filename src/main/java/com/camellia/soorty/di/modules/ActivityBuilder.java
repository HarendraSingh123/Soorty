package com.camellia.soorty.di.modules;




import com.camellia.soorty.login.view.Login;
import com.camellia.soorty.myorders.view.MyOrders;
import com.camellia.soorty.profile.view.Profile_Fragment;
import com.camellia.soorty.selectphotos.view.SelectPhoto_Drive;
import com.camellia.soorty.selectphotos.view.SelectPhoto_DropBox;
import com.camellia.soorty.selectphotos.view.SelectPhoto_Photos;
import com.camellia.soorty.selectphotos.view.SelectPhotos;
import com.camellia.soorty.splash.view.Splash;
import com.camellia.soorty.tnc.view.Termsandconditions;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract Splash bindSplash();


    @ContributesAndroidInjector(modules = LoginFragmentBuilder.class)
    abstract Login bindLogin();

    @ContributesAndroidInjector(modules = ProfileFragmentBuilder.class)
    abstract Profile_Fragment bindProfile_fragment();

    @ContributesAndroidInjector
    abstract Termsandconditions bindTermsandconditions();

    @ContributesAndroidInjector(modules = SelectPhotosBuilder.class)
    abstract SelectPhotos bindSelectPhotos();

    @ContributesAndroidInjector
    abstract MyOrders bindMyOrders();

}
