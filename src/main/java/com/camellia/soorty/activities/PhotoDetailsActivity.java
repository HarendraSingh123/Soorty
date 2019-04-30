package com.camellia.soorty.activities;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.camellia.soorty.R;
import com.camellia.soorty.adapters.BottomBannersSliding_Adapter;
import com.camellia.soorty.selectphotos.view.SelectPhotos;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

public class PhotoDetailsActivity extends AppCompatActivity implements View.OnClickListener {


    private ArrayList<Integer> ImagesArrayList =null;
    public ViewPager photodetailsviewpager;
    Context mcontext;
    public static final int[] imagearray_photo_details={R.drawable.intr_screen_1_bg,R.drawable.intro_screen_2_bg,R.drawable.intr0_screen_3_bg,R.drawable.intro_screen_4_bg};
  CirclePageIndicator indicator;

     Button btn_select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);
        mcontext=PhotoDetailsActivity.this;
        ImagesArrayList=new ArrayList<>();
        findviews();
        initView();

    }
    private void initView() {

        for (int i=0;i<imagearray_photo_details.length;i++)
            ImagesArrayList.add(imagearray_photo_details[i]);
        photodetailsviewpager.setAdapter(new BottomBannersSliding_Adapter(mcontext, ImagesArrayList));
        indicator.setViewPager(photodetailsviewpager);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(3 * density);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }

    public void findviews()
    {
        photodetailsviewpager= findViewById(R.id.view_pager_photo_details);
        indicator = findViewById(R.id.indicator_photo_details);
        btn_select=findViewById(R.id.btn_select_photo_details);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_select_photo_details:
                Intent intent=new Intent(PhotoDetailsActivity.this,SelectPhotos.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

                default:
                    break;



        }

    }
}
