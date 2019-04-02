package com.camellia.soorty;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.camellia.soorty.fragments.Cart_Fragment;
import com.camellia.soorty.fragments.Categories_Fragment;
import com.camellia.soorty.fragments.Home_Fragment;
import com.camellia.soorty.fragments.Notification_Fragments;
import com.camellia.soorty.fragments.Profile_Fragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Fragment fragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    fragment=new Home_Fragment();
                    break;
                case R.id.navigation_categories:

                    fragment=new Categories_Fragment();
                    break;
                case R.id.navigation_notifications:

                    fragment=new Notification_Fragments();


                    break;

                    case R.id.navigation_profile:

                        fragment=new Profile_Fragment();


                    break;

                case R.id.naviagation_cart:

                   fragment=new Cart_Fragment();

                   break;
            }
            return loadFragment(fragment);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new Home_Fragment());

        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private boolean loadFragment(android.support.v4.app.Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, fragment)
                    .commit();
            return true;
        }
        return false;
    }



}
