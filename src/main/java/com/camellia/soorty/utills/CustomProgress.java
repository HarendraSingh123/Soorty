package com.camellia.soorty.utills;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.camellia.soorty.R;



public class CustomProgress extends Dialog {
    Context context;
    View view;

    public CustomProgress(@NonNull Context context) {
        super(context, R.style.TransparentProgressDialog);
        this.context=context;
        WindowManager.LayoutParams wlmp = getWindow().getAttributes();
        wlmp.gravity= (Gravity.CENTER_VERTICAL);
        getWindow().setAttributes(wlmp);

        view = View.inflate(context, R.layout.custom_progress,null);
        setContentView(view);
        this.setCancelable(false);
    }

}
