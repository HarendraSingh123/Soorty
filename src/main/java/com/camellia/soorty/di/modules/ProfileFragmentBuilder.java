package com.camellia.soorty.di.modules;


import com.camellia.soorty.profile.model.DIProfileModule;
import com.camellia.soorty.profile.view.Profile_Fragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract  class ProfileFragmentBuilder {
    @ContributesAndroidInjector(modules = DIProfileModule.class)
    abstract Profile_Fragment profile_fragment();

}
