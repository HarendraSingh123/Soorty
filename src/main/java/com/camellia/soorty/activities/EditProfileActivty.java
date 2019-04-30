package com.camellia.soorty.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.camellia.soorty.R;
import com.camellia.soorty.Repos.MyAppPref;
import com.camellia.soorty.profile.view.Profile_Fragment;
import com.camellia.soorty.utills.CheckNetwork;
import com.camellia.soorty.utills.CustomProgress;

public class EditProfileActivty extends AppCompatActivity implements View.OnClickListener {

    ImageView img_back;
    Button btnupdateprofile;
    MyAppPref myAppPref;
    EditText etfname,etemail,et_last_name;
    CustomProgress customProgress;
    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_activty);
        myAppPref=new MyAppPref(getApplicationContext());
        mcontext=EditProfileActivty.this;
        customProgress=new CustomProgress(mcontext);
        findviewsbyId();

        if(CheckNetwork.isInternetAvailable(getApplicationContext())&&myAppPref.getAccessToken()!=null)
        {

            etfname.setText(myAppPref.getfirstname());
            etemail.setText(myAppPref.getEmail());
            et_last_name.setText(myAppPref.getlastnmae());

        }


    }

    public void findviewsbyId()
    {
        img_back=findViewById(R.id.image_back_edit_profile);
        btnupdateprofile=findViewById(R.id.btn_update_profile);
        etfname=findViewById(R.id.et_first_name_edit_profile);
        etemail=findViewById(R.id.et_email_addresss_edit_profile);
        et_last_name=findViewById(R.id.et_last_name_edit_profile);

        }

        @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.image_back_edit_profile:


                break;
            case R.id.btn_update_profile:
                Toast.makeText(EditProfileActivty.this,"Please wait...",Toast.LENGTH_LONG).show();
                break;
                default:
                    break;

                    }

    }

    public void UpdateProfile()
    {




    }



}
