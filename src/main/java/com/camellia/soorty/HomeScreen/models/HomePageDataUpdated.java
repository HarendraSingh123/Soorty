package com.camellia.soorty.HomeScreen.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomePageDataUpdated {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("status")
    @Expose
    private String status;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}