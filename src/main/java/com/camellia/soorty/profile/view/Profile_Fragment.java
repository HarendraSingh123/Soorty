package com.camellia.soorty.profile.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.camellia.soorty.BR;
import com.camellia.soorty.R;

import com.camellia.soorty.Repos.MyAppPref;
import com.camellia.soorty.activities.EditProfileActivty;
import com.camellia.soorty.activities.ReferAndEarnActivity;
import com.camellia.soorty.databinding.UserProfileBinding;


import com.camellia.soorty.myorders.view.MyOrders;
import com.camellia.soorty.profile.model.ProfileModel;
import com.camellia.soorty.profile.viewmodel.UserProfileViewModel;

import com.camellia.soorty.utills.BaseFragment;

import java.util.Objects;

import io.reactivex.observers.TestObserver;


public class Profile_Fragment  extends BaseFragment<UserProfileBinding,UserProfileViewModel> implements View.OnClickListener {
    private  View view;
    private UserProfileViewModel userProfileViewModel;

    private UserProfileBinding userProfileBinding;
    ImageView image_back;
    MyAppPref myAppPref;
    TextView tv_user_name,tv_email,tv_mobile;
    Button btn_edit_profile,btn_log_out,btn_back,btn_update_profile;

    ImageView imagertarow_my_order,imgrtarrow_ref_earn;
  /*  @Inject
    public ProfileModel profileModel;*/

    @Override
    public UserProfileViewModel getViewModel() {
        return userProfileViewModel;
    }

    @Override
    public int getViewModelBindingVar() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutid() {
        return R.layout.user_profile_fragment;
    }

    @Override
    public void onRetryClicked() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       userProfileViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(UserProfileViewModel.class);
       myAppPref=new MyAppPref(getContext());


    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.user_profile_fragment,container,false);

        findviewsbyId();
        Toast.makeText(getContext(),"the first name is"+myAppPref.getfirstname(),Toast.LENGTH_LONG).show();

        tv_user_name.setText(myAppPref.getfirstname()+myAppPref.getlastnmae());
        tv_email.setText(myAppPref.getEmail());
        tv_mobile.setText(myAppPref.getMobile());

        btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent  intent=new Intent(getActivity(),EditProfileActivty.class);
                startActivity(intent);

            }
        });

        imagertarow_my_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(getActivity(),MyOrders.class);
               startActivity(intent);

            }
        });

        imgrtarrow_ref_earn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(getActivity(),ReferAndEarnActivity.class);
                startActivity(intent);


            }
        });


        return view;
    }

    /* @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       userProfileBinding= getBindingLayout();
        userProfileViewModel.setProfileModel(profileModel);

    }*/


   public void findviewsbyId()
   {
       btn_edit_profile=view.findViewById(R.id.btn_edit_profile_profile);
       btn_log_out=view.findViewById(R.id.btn_logout_profile);
       tv_user_name=view.findViewById(R.id.tv_user_name_profile);
       tv_email=view.findViewById(R.id.tv_user_email_profile);
       tv_mobile=view.findViewById(R.id.tv_user_mobile_profile);
       imagertarow_my_order=view.findViewById(R.id.image1_right_arrow_order_user_profile);
       imgrtarrow_ref_earn=view.findViewById(R.id.image4_right_arrow_order_user_profile);

       }
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId())
       {
           case R.id.btn_edit_profile_profile:
               intent=new Intent(getActivity(),EditProfileActivty.class);
               startActivity(intent);
               break;
           case R.id.btn_logout_profile:
               break;
           case R.id.image_back_edit_profile:

               break;
               case R.id.btn_update_profile:
                   break;

           case R.id.image1_right_arrow_order_user_profile:
               intent=new Intent(getActivity(),MyOrders.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               startActivity(intent);

               break;

           case R.id.image4_right_arrow_order_user_profile:
               intent=new Intent(getActivity(),ReferAndEarnActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               startActivity(intent);

               break;

                   default:
                       break;

                       }

    }
}
