package com.camellia.soorty.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.camellia.soorty.R;
import com.camellia.soorty.adapters.Selected_photos_List_Adapter;
import com.camellia.soorty.selectedphotosconfig.view.SelectedPhotosConfigurations;
import com.camellia.soorty.selectphotos.model.SelectModel;
import com.camellia.soorty.utills.AppConstant;

import java.util.ArrayList;
import java.util.List;

public class Selected_Photos_Lists extends AppCompatActivity implements Selected_photos_List_Adapter.OnItemClickListener {

    RecyclerView recyclerView;
    List<SelectModel> imagesList;
    Selected_photos_List_Adapter adapter;
    ImageView ivNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected__photos__list);
        Bundle bundle = getIntent().getExtras();

        if (null != bundle) {
            imagesList = (ArrayList<SelectModel>) bundle.getSerializable(AppConstant.IMAGES_LIST);
        }
        init();
    }

    private void init() {
        ivNextButton = (ImageView) findViewById(R.id.iv_next_button);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_select_images);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Selected_photos_List_Adapter(this, imagesList);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);

        ivNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imagesList!=null){
                    Intent intent = new Intent(Selected_Photos_Lists.this, SelectedPhotosConfigurations.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onItemClick(int position, View v) {

    }
}
