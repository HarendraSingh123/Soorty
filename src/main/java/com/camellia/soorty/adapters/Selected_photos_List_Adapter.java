package com.camellia.soorty.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.camellia.soorty.R;
import com.camellia.soorty.selectphotos.model.SelectModel;

import java.util.ArrayList;
import java.util.List;

public class Selected_photos_List_Adapter extends RecyclerView.Adapter<Selected_photos_List_Adapter.ViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    List<SelectModel> imagesList = new ArrayList<>();
    private OnItemClickListener listener = null;


    // Data is passed into the constructor
    public Selected_photos_List_Adapter(Context context, List<SelectModel> data) {
        this.mInflater = LayoutInflater.from(context);
        mContext = context;
        imagesList = data;

    }

    // Inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.selected_photos_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void deleteItem(int position) {
        if (imagesList != null) {
            imagesList.remove(position);
            notifyDataSetChanged();
        }
    }

    // Binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.ivAddPics.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int Addcount = imagesList.get(position).getCount() + 1;
                imagesList.get(position).setCount(Addcount);
                holder.tvQuantity.setText(Addcount + "");
            }
        });

        holder.ivDecPics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imagesList != null && imagesList.get(position).getCount() > 0) {
                    int dec = imagesList.get(position).getCount() - 1;
                    imagesList.get(position).setCount(dec);
                    holder.tvQuantity.setText(dec + "");
                }
            }
        });

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem(position);
            }
        });

        Glide.with(mContext).load(imagesList.get(position).getImageUrl())
                .placeholder(R.drawable.camera_icon).centerCrop()
                .into(holder.imageView);
    }

    // Total number of cells
    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    // Stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView, ivAddPics, ivDecPics, ivDelete;
        public TextView tvQuantity;

        public ViewHolder(View itemView) {
            super(itemView);
            ivDecPics = (ImageView) itemView.findViewById(R.id.iv_dec_pics);
            tvQuantity = (TextView) itemView.findViewById(R.id.iv_quantity);
            ivAddPics = (ImageView) itemView.findViewById(R.id.iv_add_pics);
            imageView = (ImageView) itemView.findViewById(R.id.iv_image1);
            ivDelete = (ImageView) itemView.findViewById(R.id.iv_delete);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition(), view);
            // onItemClick(view, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }
}
