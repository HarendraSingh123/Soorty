package com.camellia.soorty.selectphotos.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.camellia.soorty.R;
import com.camellia.soorty.databinding.SelectDriveFragmentBinding;
import com.camellia.soorty.selectphotos.viewmodel.SelectPhotosViewModel;
import com.camellia.soorty.utills.BaseFragment;


public class SelectPhoto_Drive extends BaseFragment<SelectDriveFragmentBinding, SelectPhotosViewModel> {


    SelectPhotosViewModel selectPhotosViewModel;
    SelectDriveFragmentBinding driveFragmentBinding;

    public SelectPhoto_Drive() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public int getLayoutid() {
        return R.layout.fragment_select_photo__drive;
    }

    @Override
    public int getViewModelBindingVar() {
        return BR.viewModel;
    }

    @Override
    public SelectPhotosViewModel getViewModel() {
        return selectPhotosViewModel;
    }

    @Override
    public void onRetryClicked() {

    }

}
