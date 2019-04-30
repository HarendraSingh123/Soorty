package com.camellia.soorty.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.camellia.soorty.R;
import com.camellia.soorty.Repos.MyAppPref;
import com.camellia.soorty.login.view.Login;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity implements View.OnClickListener   {
    Fragment fragment;
    MyAppPref preferenceManager;
    LinearLayout Layout_bars;
    TextView[] bottomBars;
    RelativeLayout rlcontainer;
    TextView tv_continue;
    int[] screens;
    Button Next;
    TextView skip;
    ViewPager viewPager;
    MyViewPagerAdapter  myViewPagerAdapter,myvpAdapter;
    Button btnBack;
    private static int NUM_PAGES = 0;
    private static int currentPage = 0;
    private ArrayList<Integer> ImagArray = new ArrayList<Integer>();
    ImageView imgnext1,imgnext2,imgnext3,imgnext4,imgback1,imgback2,imgback3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        viewPager =  findViewById(R.id.view_pager);
//        Layout_bars =  findViewById(R.id.layoutBars);
        skip = findViewById(R.id.skip);
        rlcontainer=findViewById(R.id.contentmainscreen);
//        Next =  findViewById(R.id.next);
//        btnBack=findViewById(R.id.btnBack);
        screens = new int[]{
                R.layout.introduction_screen1,
                R.layout.introduction_screen2,
                R.layout.introduction_screen3,R.layout.introduction_screen4

        };
        myvpAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myvpAdapter);
        preferenceManager = new MyAppPref(this);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        /*if (!preferenceManager.FirstLaunch()) {
            launchMain();
            finish();
        }*/
        CirclePageIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
    }

    @Override
    public void onClick(View view) {

    }
    public void next(View v) {
        int i = getItem(+1);
        if (i < screens.length) {
            viewPager.setCurrentItem(i);
//            btnBack.setVisibility(View.VISIBLE);
        } else {
            launchMain();
        }
    }

    /* public  void back(View v)
    {
        int i=getItem(-1);
        if(i<screens.length)
        {
            viewPager.setCurrentItem(i);
            if(screens.length==1)
            {
                btnBack.setVisibility(View.GONE);
            }

        }
        else
        {

            launchMain();
        }



    }*/
    public void skip(View view) {
        launchMain();
    }


    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchMain() {
        preferenceManager.setFirstLaunch(false);
        startActivity(new Intent(MainScreen.this, Login.class));
        finish();
    }


    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {

            switch (position)
            {

                case 0:
                    findviewsfirstintro();
                    imgnext1.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             Log.d("hello","in introscreen2 ");

                             viewPager.setCurrentItem(1);

                         }
                     });
                     Log.d("in intro screen2","this is the second introscreen");
                    break;
                    case 1:
                    findviewssecondintro();
                    imgback1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewPager.setCurrentItem(0);


                        }
                    });
                    imgnext2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewPager.setCurrentItem(2);
                        }
                    });
                    Log.d("in intro screen2","this is the second introscreen");

                    break;

                case 2:
                    findviewsthirdintro();
                    imgback2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewPager.setCurrentItem(1);


                        }
                    });

                    imgnext3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewPager.setCurrentItem(3);

                        }
                    });
                    Log.d("in intro screen3","this is the third introscreen");

                    break;
                case 3:

                    findviewsfourthintro();
                    imgback3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewPager.setCurrentItem(2);


                        }
                    });
                    tv_continue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            launchMain();

                        }
                    });
                    Log.d("in intro screen4","this is the fourth introscreen");
                    break;
                    default:
                        break;

                        }

                        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
    public void findviewsfirstintro()
    {
        imgnext1=findViewById(R.id.img_next_choose_splash);



        }

    public void findviewssecondintro()
    {
        imgnext2=findViewById(R.id.img_next_select_splash);
        imgback1=findViewById(R.id.img_back_select_splash);

        }
    public void findviewsthirdintro()
    {
        imgnext3=findViewById(R.id.img_next_design_splash);
        imgback2=findViewById(R.id.img_back_design_splash);


        }
    public boolean loadfragment(Fragment fragment)
    {

        if(fragment!=null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.contentmainscreen,fragment).commit();

    return  true;
        }
        return false;

        }

    public void findviewsfourthintro()
    {
        imgback3=findViewById(R.id.img_back_order_splash);
       tv_continue=findViewById(R.id.tv_continue_order_screen);
        }
     public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater inflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
           inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(screens[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return screens.length;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View) object;
            container.removeView(v);
        }
        @Override
        public boolean isViewFromObject(View v, Object object) {
            return v == object;
        }
    }
}