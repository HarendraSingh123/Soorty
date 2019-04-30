package com.camellia.soorty.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressModel {


    @SerializedName("data")
    @Expose
    private List<Datum> data;

    @SerializedName("status")
    @Expose
    private String status;

    public List<Datum> getDatum() {
        return data;
    }

    public void setDatum(List<Datum> datum) {
        this.data = datum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
