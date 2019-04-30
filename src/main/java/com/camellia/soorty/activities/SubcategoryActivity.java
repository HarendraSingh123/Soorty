package com.camellia.soorty.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.camellia.soorty.HomeScreen.models.Category;

import com.camellia.soorty.HomeScreen.models.HomeScreenData;
import com.camellia.soorty.Interfaces.ItemClickListener;
import com.camellia.soorty.R;
import com.camellia.soorty.REST.ApiClient;
import com.camellia.soorty.REST.ApiInterface;
import com.camellia.soorty.Repos.MyAppPref;
import com.camellia.soorty.profile.view.Profile_Fragment;
import com.camellia.soorty.utills.CustomProgress;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;

public class SubcategoryActivity extends AppCompatActivity implements View.OnClickListener {


    private CustomProgress customProgress;
    private Context mcontext;
    public MyAppPref myAppPref;
    public ApiInterface apiInterface;
    public ApiClient apiClient;
    public static final int[] subcatimagesarray={R.drawable.intr_screen_1_bg,R.drawable.intro_screen_2_bg,R.drawable.intr0_screen_3_bg,R.drawable.intro_screen_4_bg};
    RecyclerView subcatrecyclerview;
    RecyclerView.LayoutManager layoutManager;
    public static  ArrayList<Integer> subcatarraylist=null;
    SubcategoryAdapter subcategoryAdapter;
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);
        mcontext=SubcategoryActivity.this;
        myAppPref=new MyAppPref(mcontext);
        customProgress=new CustomProgress(mcontext);
        subcatrecyclerview=findViewById(R.id.rv_subcategory);
        subcatarraylist=new ArrayList<>();
        layoutManager = new LinearLayoutManager(mcontext, LinearLayoutManager.VERTICAL, false);
        subcatrecyclerview.setLayoutManager(layoutManager);
        subcategoryAdapter=new SubcategoryAdapter(mcontext,subcatarraylist);
        subcatrecyclerview.setAdapter(subcategoryAdapter);

        }

    public void findviews()
    {
        iv_back=findViewById(R.id.iv_back_subcategory);



    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back_subcategory:
                Intent intent=new Intent(mcontext,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

                default:
                    break;



        }

    }


    public void showProgress()
    {

        customProgress.show();

    }

    public void  hideprogres()
    {
        customProgress.hide();


    }



    public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryActivity.SubcategoryAdapter.ViewHolder> {
        ArrayList<Integer> subcategories;
        Context mconctext;

        public SubcategoryAdapter(Context context, ArrayList<Integer> categorylist) {
            super();
            this.mconctext = context;
            this.subcategories = categorylist;

        }
        @NonNull
        @Override
        public SubcategoryActivity.SubcategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
            View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.subcategory_card_view,viewGroup,false);

            SubcategoryActivity.SubcategoryAdapter.ViewHolder viewHolder=new SubcategoryActivity.SubcategoryAdapter.ViewHolder(view);

            switch (position)
            {
                case 0:



                    break;

                case 1:
                    break;


                default:

                    break;


            }

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull SubcategoryActivity.SubcategoryAdapter.ViewHolder viewHolder, int position) {



        /*    try {
                Picasso.with(SubcategoryActivity.this)
                        .load(subcategories.get(position).getUrl()).placeholder(getResources().getDrawable(R.drawable.soorty_ic_launcher)).into(viewHolder.subsubcategory_imageview);

            }
            catch (IndexOutOfBoundsException ex)
            {
                ex.printStackTrace();

            }*/


            /*viewHolder.category_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mconctext,PhotoPrintActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                       *//* switch (position)
                        {  }*//*


                }
            });
*/


        }
        @Override
        public int getItemCount() {
            return 10;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
        {

            public ImageView subsubcategory_imageview;
            public TextView category_name;
            public Button category_button;
            private ItemClickListener clickListener;

            public ViewHolder(View itemview) {
                super(itemview);
              
                this.subsubcategory_imageview=itemview.findViewById(R.id.iv_category_photo);

            }
            public void setClickListener(ItemClickListener clickListener) {
                this.clickListener = clickListener;
            }

            @Override
            public void onClick(View v) {
               /* category_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String buttonID=Integer.toString(v.getId());
                        Log.d("the buttton id is",buttonID);
                        String categoryname=category_button.getText().toString();

                        if(categoryname.equalsIgnoreCase("Photo Prints"))
                        {
                            Intent intent=new Intent(mconctext,PhotoPrintActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);



                        }

                    }
                });*/

            }

            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        }

    }
    
    public void fetchdata()
    {

        showProgress();
        apiInterface =
                apiClient.getClient().create(ApiInterface.class);

       /* Call<HomeScreenData>  call=apiInterface.getSubcategorydata("XQKCI9492W9dx43UFyg9qkucT9Wktki0SF7HH1hi98c=",myAppPref.getAccessToken(),Integer.parseInt(myAppPref.getUserId()));


            ((retrofit2.Call) call).enqueue(new Callback() {
                @Override
                public void onResponse(retrofit2.Call call, Response response) {

                }

                @Override
                public void onFailure(retrofit2.Call call, Throwable t) {

                }
            });
*/


        }
}
