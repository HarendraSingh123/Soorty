package com.camellia.soorty.callback;

public interface ErrorHandlingCallBack {
    void handleNoData(String title, String buttonText, boolean isFabEnable);
    void onButtonClicked(String action);
}
