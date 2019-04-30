package com.camellia.soorty.selectphotos.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.camellia.soorty.R;
import com.camellia.soorty.databinding.SelectDropboxFragmentBinding;
import com.camellia.soorty.selectphotos.viewmodel.SelectPhotosViewModel;
import com.camellia.soorty.utills.BaseFragment;


public class SelectPhoto_DropBox extends BaseFragment<SelectDropboxFragmentBinding, SelectPhotosViewModel> {

    SelectPhotosViewModel selectPhotosViewModel;

    public SelectPhoto_DropBox() {

    }


    @Override
    public int getLayoutid() {
        return R.layout.fragment_select_photo__drop_box;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }



}
