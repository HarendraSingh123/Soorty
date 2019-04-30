package com.camellia.soorty.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.camellia.soorty.R;
import com.camellia.soorty.REST.ApiClient;
import com.camellia.soorty.REST.ApiInterface;
import com.camellia.soorty.Repos.MyAppPref;
import com.camellia.soorty.adapters.Selected_photos_List_Adapter;
import com.camellia.soorty.adapters.ShippingAddAdapter;
import com.camellia.soorty.addnewaddress.view.AddNewAddressActivity;
import com.camellia.soorty.login.view.Login;
import com.camellia.soorty.models.AddressModel;
import com.camellia.soorty.models.Datum;
import com.camellia.soorty.utills.CheckNetwork;
import com.camellia.soorty.utills.CustomProgress;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingAddressListActivity extends AppCompatActivity implements ShippingAddAdapter.OnItemClickListener {

    RecyclerView recyclerViewAddList;
    ShippingAddAdapter adapter;
    List<AddressModel> addModelList;
    CustomProgress customProgress;
    private ApiInterface apiInterface;
    private ApiClient apiClient;
    private MyAppPref myAppPref;
    private RelativeLayout rlAddAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address_list);
        initView();
    }

    private void initView() {
        customProgress = new CustomProgress(this);
        myAppPref = new MyAppPref(this);

        if (CheckNetwork.isInternetAvailable(this) && myAppPref.getAccessToken() != null)
            getAddressList();
        else
            Toast.makeText(ShippingAddressListActivity.this, "No Internet connection found!!!", Toast.LENGTH_LONG).show();
        rlAddAddress = (RelativeLayout) findViewById(R.id.rl_add_address);
        recyclerViewAddList = (RecyclerView) findViewById(R.id.recycler_view_add_list);
        recyclerViewAddList.setLayoutManager(new LinearLayoutManager(this));

        rlAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShippingAddressListActivity.this, AddNewAddressActivity.class);
                startActivity(intent);
            }
        });

    }

    public void getAddressList() {
        showProgress();
        apiInterface = apiClient.getClient().create(ApiInterface.class);
        Call<AddressModel> call = apiInterface.getAddressList("XQKCI9492W9dx43UFyg9qkucT9Wktki0SF7HH1hi98c=", myAppPref.getAccessToken(), myAppPref.getUserId());
        call.enqueue(new Callback<AddressModel>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<AddressModel> call, Response<AddressModel> response) {
                Log.e("response status add list", response.code() + "");

                if (response.code() == 200) {
                    {
                        AddressModel addressModel = response.body();
                        List<Datum> addressModelList = addressModel.getDatum();
                        if (addressModel != null) {
                            adapter = new ShippingAddAdapter(ShippingAddressListActivity.this, addressModelList);
                            adapter.setOnItemClickListener(ShippingAddressListActivity.this);
                            recyclerViewAddList.setAdapter(adapter);

                        }
                    }
                } else if (response.code() == 401) {

                } else if (response.code() == 404) {
                    Toast.makeText(ShippingAddressListActivity.this, getResources().getString(R.string.something_went_wrong_try_again) + "", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 500) {
                    Toast.makeText(ShippingAddressListActivity.this, getResources().getString(R.string.internal_server_error) + "", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ShippingAddressListActivity.this, getResources().getString(R.string.something_went_wrong_try_again) + "", Toast.LENGTH_SHORT).show();
                }
                hideProgress();
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<AddressModel> call, Throwable t) {
                hideProgress();
                Log.e("on failure Sign Up Fragment ***************", t.toString());
            }
        });

    }

    public void showProgress() {
        customProgress.show();
    }

    public void hideProgress() {
        customProgress.hide();

    }

    @Override
    public void onItemClick(int position, View v) {

    }


}
