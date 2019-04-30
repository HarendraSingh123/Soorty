package com.camellia.soorty.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.camellia.soorty.R;
import com.camellia.soorty.selectphotos.model.SelectModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerSelectPhotos extends RecyclerView.Adapter<RecyclerSelectPhotos.ViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    List<SelectModel> images = new ArrayList<>();
    private OnItemClickListener listener = null;

    // Data is passed into the constructor
    public RecyclerSelectPhotos(Context context, List<SelectModel> data) {
        this.mInflater = LayoutInflater.from(context);
        mContext = context;
        images = data;

    }

    // Inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // Binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(mContext).load(images.get(position).getImageUrl())
                .placeholder(R.drawable.camera_icon).centerCrop()
                .into(holder.imageView);
    }

    // Total number of cells
    @Override
    public int getItemCount() {
        return images.size();
    }

    // Stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_list_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition(), view);
// onItemClick(view, getAdapterPosition());
        }
    }

    // Convenience method for getting data at click position
    public String getItem(int id) {
        return images.get(id).getImageUrl();
    }

    // Method that executes your code for the action received
    public void onItemClick(View view, int position) {
//Log.i("TAG", "You clicked number " + getItem(position).toString() + ", which is at cell position " + position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }
}






