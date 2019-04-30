package com.camellia.soorty.activities;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.camellia.soorty.R;
import com.camellia.soorty.adapters.Photo_Print_Adapter;

import butterknife.OnItemClick;


public class PhotoPrintActivity extends AppCompatActivity  implements View.OnClickListener, Photo_Print_Adapter.OnItemClickListener {
    RecyclerView photoprintrecyclerview;
    Context mcontext;
    Photo_Print_Adapter photo_print_adapter;
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_print);
        findviews();
        initView();



    }
    private void initView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mcontext);
        photoprintrecyclerview.setLayoutManager(mLayoutManager);
        photo_print_adapter = new Photo_Print_Adapter(mcontext);
        DividerItemDecoration divider = new
                DividerItemDecoration(photoprintrecyclerview.getContext(),
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(
//                ContextCompat.getDrawable(mcontext, R.drawable.divider)
                ContextCompat.getDrawable(getBaseContext(), R.drawable.divider)
        );
        photoprintrecyclerview.addItemDecoration(divider);
        photo_print_adapter = new Photo_Print_Adapter(mcontext);
        photoprintrecyclerview.setAdapter(photo_print_adapter);
        photo_print_adapter.setOnItemClickListener(this);

        }
    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.iv_back:
                Intent intent =new Intent(PhotoPrintActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
                default:
                    break;


        }

    }
    public void findviews()
    {
        iv_back=findViewById(R.id.iv_back);
        photoprintrecyclerview =findViewById(R.id.recycler_view_photo_print);

        }

    @Override
    public void onItemClick(int position, View v) {
        Intent intent=new Intent(PhotoPrintActivity.this,PhotoDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

}