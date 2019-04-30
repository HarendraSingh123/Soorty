package com.camellia.soorty.myorders.viewmodels;

import android.arch.lifecycle.ViewModel;

public class MyOrdersViewModel extends ViewModel {

    private String myorders;


    public MyOrdersViewModel()
    {
        myorders="MYORDERS";


    }

    public String getMyorders() {
        return myorders;
    }

    public void setMyorders(String myorders) {
        this.myorders = myorders;
    }
}
