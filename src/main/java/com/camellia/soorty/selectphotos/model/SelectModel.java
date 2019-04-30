package com.camellia.soorty.selectphotos.model;

import java.io.Serializable;

public class SelectModel implements Serializable {


    private String imageUrl;
    private int count = 1;
    private boolean isSelected = false;

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }



    public SelectModel(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public boolean isSelected() {
        return isSelected;
    }
}
