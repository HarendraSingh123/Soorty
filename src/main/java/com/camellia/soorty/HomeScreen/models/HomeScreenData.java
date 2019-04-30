package com.camellia.soorty.HomeScreen.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.camellia.soorty.HomeScreen.models.Data;
public class HomeScreenData {

    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @SerializedName("status")
    @Expose
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}