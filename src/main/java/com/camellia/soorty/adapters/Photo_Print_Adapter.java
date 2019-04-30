package com.camellia.soorty.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.camellia.soorty.R;

import butterknife.OnItemClick;


public class Photo_Print_Adapter extends RecyclerView.Adapter<Photo_Print_Adapter.HolderClass>{

    private  OnItemClickListener listener = null;

    public Photo_Print_Adapter(Context mContext) {
    }

    @NonNull
    @Override
    public HolderClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.photo_prints_items, viewGroup, false);


        return new HolderClass(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderClass holderClass, int position) {




        }
        @Override
    public int getItemCount() {
        return 10;
    }

    public class HolderClass extends RecyclerView.ViewHolder implements View.OnClickListener{

       public HolderClass(@NonNull View itemView) {
           super(itemView);
           itemView.setOnClickListener(this);
       }

       public void   OnItemClick()
       {

           }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition(), v);



        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }
}
