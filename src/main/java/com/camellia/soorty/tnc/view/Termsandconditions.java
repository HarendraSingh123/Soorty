package com.camellia.soorty.tnc.view;



import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.camellia.soorty.BR;
import com.camellia.soorty.R;

import com.camellia.soorty.activities.SignUpActivity;
import com.camellia.soorty.databinding.TncBinding;

import com.camellia.soorty.login.view.Login;
import com.camellia.soorty.tnc.viewmodel.Tncviewmodel;
import com.camellia.soorty.utills.BaseActivity;

public class Termsandconditions extends BaseActivity<TncBinding,Tncviewmodel> implements View.OnClickListener {

    private TncBinding tncbinding;
    private Tncviewmodel tncviewmodel;
    ImageView imgback;

    @Override
    public Tncviewmodel getViewModel() {
        return tncviewmodel;
    }

    @Override
    public int getLayoutid() {
        return R.layout.tnc_layout;
    }

    @Override
    public int getViewModelBindingVar() {
        return BR.viewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tncviewmodel=ViewModelProviders.of(this).get(Tncviewmodel.class);
        tncbinding=getBindingLayout();

        findviews();
        }

        public void findviews()
        {
            imgback=findViewById(R.id.img_back_tnc);


        }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.img_back_tnc:
                startActivity(new Intent(Termsandconditions.this,SignUpActivity.class));
                finish();

                break;

                default:
                    break;


        }

    }
}
