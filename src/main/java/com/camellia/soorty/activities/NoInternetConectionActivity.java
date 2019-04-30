package com.camellia.soorty.activities;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.camellia.soorty.R;
import com.camellia.soorty.login.view.Login;

public class NoInternetConectionActivity extends AppCompatActivity  implements View.OnClickListener {

    Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet_conection);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_ok_no_internet:
                Intent intent=new Intent(NoInternetConectionActivity.this,Login.class);
                startActivity(intent);
                break;
                default:
                    break;

                    }

    }

    public void findviews()
    {
        btn_ok=findViewById(R.id.btn_ok_no_internet);


    }
}
