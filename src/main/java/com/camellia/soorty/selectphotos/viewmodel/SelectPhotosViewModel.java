package com.camellia.soorty.selectphotos.viewmodel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.camellia.soorty.R;
import com.camellia.soorty.utills.BaseViewModel;


public class SelectPhotosViewModel extends BaseViewModel {

     private  String selectphotoview;

     public SelectPhotosViewModel()
     {
         selectphotoview="SelectPhotos";
     }
     public String getSelectphotoview() {
        return selectphotoview;
    }

    public void setSelectphotoview(String selectphotoview) {
        this.selectphotoview = selectphotoview;
    }

    public void addFragment(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();
    }
}
