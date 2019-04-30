package com.camellia.soorty.tnc.viewmodel;

import android.arch.lifecycle.ViewModel;

public class Tncviewmodel extends ViewModel {

    public String tncviewmodel;

    public Tncviewmodel()
    {

        tncviewmodel="tncviewmodel";

        }
    public String getTncviewmodel() {
        return tncviewmodel;
    }

    public void setTncviewmodel(String tncviewmodel) {
        this.tncviewmodel = tncviewmodel;
    }
}
