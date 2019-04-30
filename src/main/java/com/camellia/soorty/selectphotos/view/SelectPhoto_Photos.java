package com.camellia.soorty.selectphotos.view;

import android.Manifest;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import android.support.v7.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.camellia.soorty.R;

import com.camellia.soorty.activities.Selected_Photos_Lists;
import com.camellia.soorty.adapters.RecyclerSelectPhotos;
import com.camellia.soorty.databinding.SelectPhotoFragmentBinding;

import com.camellia.soorty.selectphotos.model.SelectModel;
import com.camellia.soorty.selectphotos.viewmodel.SelectPhotosViewModel;
import com.camellia.soorty.utills.AppConstant;
import com.camellia.soorty.utills.BaseFragment;

import java.util.ArrayList;

import java.util.Objects;


public class SelectPhoto_Photos extends BaseFragment<SelectPhotoFragmentBinding, SelectPhotosViewModel> implements RecyclerSelectPhotos.OnItemClickListener {


    SelectPhotosViewModel selectPhotosViewModel;
    SelectPhotoFragmentBinding selectPhotoFragmentBinding;
    private int PICK_IMAGE_MULTIPLE = 100;
    private String imageEncoded;
    private RecyclerSelectPhotos adapter;
    ArrayList<SelectModel> images = new ArrayList<>();
    ArrayList<SelectModel> selectPhotosList = new ArrayList<>();
    private int PICK_FROM_GALLERY = 111;

    public SelectPhoto_Photos() {

    }


    @Override
    public int getLayoutid() {
        return R.layout.fragment_select_photo__photos;
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
        selectPhotosViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(SelectPhotosViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
            } else {
                images = getAllShownImagesPath(getContext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 111:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    images = getAllShownImagesPath(getContext());
                } else {
                    Toast.makeText(getContext(), "Permission not granted", Toast.LENGTH_SHORT).show();
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selectPhotoFragmentBinding = getBindingLayout();

        selectPhotoFragmentBinding.btNextBotomSelectPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Selected_Photos_Lists.class);
                Bundle bundles = new Bundle();
                if (selectPhotosList != null && !selectPhotosList.isEmpty()) {
                    bundles.putSerializable(AppConstant.IMAGES_LIST, selectPhotosList);
                    intent.putExtras(bundles);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "No pics selected....", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_photo_print);
        int numberOfColumns = 2;
        selectPhotoFragmentBinding.recyclerViewSelectImages.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
        adapter = new RecyclerSelectPhotos(getContext(), images);
        adapter.setOnItemClickListener(this);
        selectPhotoFragmentBinding.recyclerViewSelectImages.setAdapter(adapter);
    }

    private ArrayList<SelectModel> getAllShownImagesPath(Context activity) {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        ArrayList<SelectModel> listOfAllImages = new ArrayList<>();
        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        cursor = activity.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            SelectModel selectModel = new SelectModel(absolutePathOfImage);
            listOfAllImages.add(selectModel);
        }
        return listOfAllImages;
    }

    @Override
    public void onItemClick(int position, View v) {
        if (images != null) {
            v.setBackgroundColor(!images.get(position).isSelected() ? Color.CYAN : Color.WHITE);
            if (images.get(position).isSelected()) {
                images.get(position).setSelected(false);
                for (int i = 0; i < selectPhotosList.size(); i++) {
                    if (images.get(position).getImageUrl().equals(selectPhotosList.get(i).getImageUrl()))
                        selectPhotosList.remove(position);
                }
            } else {
                images.get(position).setSelected(true);
                SelectModel selectModel = new SelectModel(images.get(position).getImageUrl());
                selectPhotosList.add(selectModel);
            }
        }
    }
}
