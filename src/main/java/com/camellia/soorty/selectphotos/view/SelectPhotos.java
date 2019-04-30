package com.camellia.soorty.selectphotos.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import com.camellia.soorty.BR;
import com.camellia.soorty.R;
import com.camellia.soorty.databinding.SelectPhotoBinding;
import com.camellia.soorty.selectphotos.viewmodel.SelectPhotosViewModel;
import com.camellia.soorty.utills.BaseActivity;

public class SelectPhotos extends BaseActivity<SelectPhotoBinding,SelectPhotosViewModel> {

    SelectPhotoBinding selectPhotoBinding;
    private SelectPhotosViewModel selectPhotosViewModel;

    @Override
    public SelectPhotosViewModel getViewModel() {
        return selectPhotosViewModel;
    }

    @Override
    public int getViewModelBindingVar() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutid() {
        return R.layout.actvity_select_photos;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        selectPhotoBinding.btNextBotomSelectPhotos.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startnewActivity();
//            }
//        });

        selectPhotosViewModel = ViewModelProviders.of(SelectPhotos.this).get(SelectPhotosViewModel.class);
        FragmentManager fragmentManager = getSupportFragmentManager();
        selectPhotosViewModel.addFragment(fragmentManager, new SelectPhoto_Photos());

        TextView tvMyAlbumSelectPhotos = (TextView) findViewById(R.id.tv_my_album_select_photos);
        TextView tvGgoglePhotosSelectPhotos = (TextView) findViewById(R.id.tv_ggogle_photos_select_photos);
        TextView tvDropboxSelectPhotos = (TextView) findViewById(R.id.tv_dropbox_select_photos);

        tvMyAlbumSelectPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new SelectPhoto_Photos());
            }
        });
       tvGgoglePhotosSelectPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new SelectPhoto_Drive());
            }
        });
        tvDropboxSelectPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new SelectPhoto_DropBox());
            }
        });

    }

    void getFragment(Fragment fragment){
        selectPhotosViewModel = ViewModelProviders.of(SelectPhotos.this).get(SelectPhotosViewModel.class);
        FragmentManager fragmentManager = getSupportFragmentManager();
        selectPhotosViewModel.addFragment(fragmentManager, fragment);
    }


//    public void startnewActivity()
//    {
//        Intent intent;
//        intent=new Intent(SelectPhotos.this,MainActivity.class);
//        startActivity(intent);
//        }
}
