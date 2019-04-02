package com.camellia.soorty.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camellia.soorty.R;

public class Home_Fragment extends Fragment implements View.OnClickListener {

    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.home_fragment,container,false);
        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
