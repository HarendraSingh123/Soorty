package com.camellia.soorty.HomeScreen.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.camellia.soorty.HomeScreen.models.Data;
import com.camellia.soorty.Interfaces.ItemClickListener;
import com.camellia.soorty.R;
import com.camellia.soorty.REST.ApiClient;
import com.camellia.soorty.REST.ApiInterface;
import com.camellia.soorty.Repos.MyAppPref;
import com.camellia.soorty.activities.PhotoPrintActivity;
import com.camellia.soorty.activities.SubcategoryActivity;
import com.camellia.soorty.adapters.BottomBannersSliding_Adapter;
import com.camellia.soorty.adapters.SlidingImage_Adapter;
import com.camellia.soorty.adapters.TopBannersSliding_Adapter;
import com.camellia.soorty.login.view.Login;

import com.camellia.soorty.HomeScreen.models.Banner;
import com.camellia.soorty.HomeScreen.models.Category;
import com.camellia.soorty.HomeScreen.models.HomeScreenData;

import com.camellia.soorty.utills.CheckNetwork;
import com.camellia.soorty.utills.CustomProgress;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_Fragment extends Fragment implements View.OnClickListener {
    private View view;
    String[] imageurl;
    String[] catimageurl;
    private static ViewPager mPager,bottom_viewpager;
    private ArrayList<String> ImagesArray = new ArrayList<>();
    private ArrayList<Integer> ImagesArraylistbanners = new ArrayList<>();
    private static int currentPage = 0;
    private static int currentPage_bottom = 0;
    private static int NUM_PAGES = 0;
    private static int NUM_PAGES_BOTTOM = 0;
    CustomProgress customProgress;
    Context mContext;
    MyAppPref myAppPref;
    ApiInterface apiInterface;
    ApiClient apiClient;
    private List<Banner> bannerList;
    private List<Category> categorylist;
    private String strMobileNo;
    private String strServerOTP;
    private String emailId, firstName, lastName, password, access_token;
    ArrayList<Category> categoryArrayList;

    RecyclerView.LayoutManager layoutManager;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter cat_adapter;

    public static final int[] ImagesArray1={R.drawable.intr_screen_1_bg,R.drawable.intro_screen_2_bg,R.drawable.intr0_screen_3_bg,R.drawable.intro_screen_4_bg};
    public ArrayList<Integer> imagesarraylist=null;

    public static Home_Fragment newInstance() {
        Home_Fragment fragment = new Home_Fragment();
        return fragment;
    }

    public Home_Fragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        mContext = this.getContext();
        customProgress = new CustomProgress(mContext);
        myAppPref = new MyAppPref(getActivity());
        mContext = getContext();
        access_token = null;
        imagesarraylist=new ArrayList<>();
        layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

        if (CheckNetwork.isInternetAvailable(mContext) && myAppPref.getAccessToken() != null) {
            getHomepagedata();

        } else {
            Toast.makeText(getContext(), "No Internet connection found!!!", Toast.LENGTH_LONG).show();

        }
        init();
        initbottombanners();
        return view;
    }

    @Override
    public void onClick(View view) {

    }

    public void showProgress() {
        customProgress.show();
    }


    public void hideProgress() {
        customProgress.hide();

    }
    private void init() {
        for (int i = 0; i < ImagesArray1.length; i++)
            ImagesArraylistbanners.add(ImagesArray1[i]);
        mPager = view.findViewById(R.id.pager);
        mPager.setAdapter(new TopBannersSliding_Adapter(Home_Fragment.this.getContext(), ImagesArraylistbanners));
        CirclePageIndicator indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(3 * density);
        NUM_PAGES =ImagesArray1.length;
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
                /*mPager.setClipToPadding(false);
                mPager.setPadding(30,0,30,0);
                mPager.setPageMargin(20);
*/
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }
            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }


    private void initbottombanners()
    {
        for (int i=0;i<ImagesArray1.length;i++)

        {
            imagesarraylist.add(ImagesArray1[i]);
            bottom_viewpager=view.findViewById(R.id.viewpager_bottom_banners);
            bottom_viewpager.setAdapter(new BottomBannersSliding_Adapter(mContext,imagesarraylist));

            CirclePageIndicator pageIndicator=view.findViewById(R.id.circular_theme_indicators);
            pageIndicator.setViewPager(bottom_viewpager);
            final  float density=getResources().getDisplayMetrics().density;
            pageIndicator.setRadius(3*density);
            NUM_PAGES_BOTTOM=ImagesArray1.length;
            final Handler handler2=new Handler();
            final Runnable update=new Runnable() {
                @Override
                public void run() {
                    if(currentPage_bottom==NUM_PAGES_BOTTOM){
                        currentPage_bottom=0;
                    }
                    bottom_viewpager.setCurrentItem(currentPage_bottom++,true);
                }
            };

            Timer swipetimer2=new Timer();
            swipetimer2.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler2.post(update);

                }
            },3000,3000);

            pageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int position) {
                    currentPage_bottom=position;

                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });

        }

        }

        public class CategoryAdapter extends RecyclerView.Adapter<Home_Fragment.CategoryAdapter.ViewHolder> {
        ArrayList<Category> categories;
        Context mconctext;
        public CategoryAdapter(Context context, ArrayList<Category> categorylist) {
            super();
            this.mconctext = context;
            this.categories = categorylist;

        }

        @NonNull
        @Override
        public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
            View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_card_view,viewGroup,false);

            ViewHolder viewHolder=new ViewHolder(view);


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
        public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder viewHolder, int position) {

            viewHolder.category_button.setText(categories.get(position).getName());

            try {
                Picasso.with(Home_Fragment.this.getContext())
                        .load(categories.get(position).getUrl()).placeholder(getResources().getDrawable(R.drawable.soorty_ic_launcher)).into(viewHolder.category_imageview);

            }
            catch (IndexOutOfBoundsException ex)
            {
                ex.printStackTrace();

                }


                viewHolder.category_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(viewHolder.category_button.getText().toString().equalsIgnoreCase("Photo Prints")) {
                            Intent intent = new Intent(mconctext, PhotoPrintActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }


                       else if(viewHolder.category_button.getText().toString().equalsIgnoreCase("FLOWERS"))
                            {
                                Intent intent = new Intent(mconctext, SubcategoryActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);


                        }

                       }
                });

            }
        @Override
        public int getItemCount() {
            return categories.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
        {

            public ImageView category_imageview;
            public TextView category_name;
            public Button category_button;
            private ItemClickListener clickListener;

            public ViewHolder(View itemview) {
                super(itemview);
                this.category_button=itemview.findViewById(R.id.btn_category_name);
                this.category_imageview=itemview.findViewById(R.id.iv_category_photo);
                category_button.setOnClickListener(this::onClick);
                }


            public void setClickListener(ItemClickListener clickListener) {
                this.clickListener = clickListener;
            }

            @Override
            public void onClick(View v) {
             category_button.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     String buttonID=Integer.toString(v.getId());
                     Log.d("the buttton id is",buttonID);
                    String categoryname=category_button.getText().toString();

                    /*if(categoryname.equalsIgnoreCase("Photo Prints"))
                    {
                        Intent intent=new Intent(mconctext,PhotoPrintActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);



                    }*/

                 }
             });

            }

            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        }

    }




    public void getHomepagedata() {

//        final ProgressDialog loading = ProgressDialog.show(this, "", "Please wait...", false, false);


        showProgress();

        apiInterface =
                apiClient.getClient().create(ApiInterface.class);

        Call<HomeScreenData> call = apiInterface.gethomescreendata("XQKCI9492W9dx43UFyg9qkucT9Wktki0SF7HH1hi98c=", myAppPref.getAccessToken(), myAppPref.getUserId());

//        Call<VerifyOTPMainModel> call = apiInterface.getOTP(Constants.STATIC_HEADER, strMobileNo, Constants.LOGIN_TYPE);
        call.enqueue(new Callback<HomeScreenData>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<HomeScreenData> call, Response<HomeScreenData> response) {
                Log.e("response status GET OTP API ", response.code() + "");

                if (response.code() == 200) {
                    {
                        HomeScreenData homeScreenData = response.body();
                        if (homeScreenData.isStatus()) {

                            Data maindata = homeScreenData.getData();
                            categoryArrayList = new ArrayList<>();

                            bannerList = maindata.getBanners();
                            Banner banner = new Banner();
                            Category category = new Category();
                            categorylist=maindata.getCategory();

                            Log.d("the bannere size", Integer.toString(bannerList.size()));
                            Log.d("the category list size", Integer.toString(categorylist.size()));
                            for (int i = 0; i < bannerList.size(); i++) {
                                Intent intent = new Intent(mContext, Login.class);
                                banner = maindata.getBanners().get(i);
                                banner.getUrl();
                                banner.getId();
                                banner.getName();
                            }

                            for (int m = 0; m < bannerList.size(); m++) {
                                try {
                                    imageurl = new String[bannerList.size()];
                                    imageurl[m] = banner.getUrl();

                                } catch (IndexOutOfBoundsException e) {
                                    e.printStackTrace();
                                }


                            }
                            for (int k = 0; k < categorylist.size(); k++) {
                                category=maindata.getCategory().get(k);
                                int catId = category.getId();
                                String catName = category.getName();
                                String catimageurl = category.getUrl();
                                categoryArrayList.add(category);

                            }

                            for (int n = 0; n < categorylist.size(); n++) {
                                catimageurl = new String[categorylist.size()];
                                try {

                                    catimageurl[n] = category.getUrl();

                                } catch (IndexOutOfBoundsException ex) {
                                    ex.printStackTrace();


                                }

                            }
                            Log.d("imageurl size", Integer.toString(bannerList.size()));
                            try {
//                              init();
                            }
                           catch (NullPointerException ex)
                           {
                               ex.printStackTrace();

                           }
                            mRecyclerView=view.findViewById(R.id.rv_categories);
                            mRecyclerView.hasFixedSize();
                            layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                            mRecyclerView.setLayoutManager(layoutManager);
                            cat_adapter=new CategoryAdapter(getContext(),categoryArrayList);
                            mRecyclerView.setAdapter(cat_adapter);
                            SnapHelper snapHelper = new PagerSnapHelper();
                            mRecyclerView.setPadding(0,16,0,0);

                            }
                    }
                } else if (response.code() == 401) {
                    Intent intent = new Intent(mContext, Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                } else if (response.code() == 404) {
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.something_went_wrong_try_again) + "", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 500) {
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.internal_server_error) + "", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.something_went_wrong_try_again) + "", Toast.LENGTH_SHORT).show();
                }


                hideProgress();
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<HomeScreenData> call, Throwable t) {
               /* if (loading.isShowing()) {
                    loading.dismiss();
                }*/

                hideProgress();
                Log.e("on failure Sign Up Fragment ***************", t.toString());
            }
        });


    }



}
