package com.camellia.soorty.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.camellia.soorty.R;
import com.camellia.soorty.models.AddressModel;
import com.camellia.soorty.models.Datum;

import java.util.List;

public class ShippingAddAdapter extends RecyclerView.Adapter<ShippingAddAdapter.HolderClass> {

    private OnItemClickListener listener = null;
    private Context mContext;
    private List<Datum> addressModels;

    public ShippingAddAdapter(Context mContext, List<Datum> addressModels) {
        this.mContext = mContext;
        this.addressModels = addressModels;
    }

    @NonNull
    @Override
    public HolderClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.address_item, viewGroup, false);

        return new HolderClass(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderClass HolderClass, int position) {

        if (addressModels != null) {
            HolderClass.tv_name.setText(addressModels.get(position).getName());
            HolderClass.tv_address_street.setText(addressModels.get(position).getCity() + ", " + addressModels.get(position).getLandmark()
                    + ", " + addressModels.get(position).getPincode());
            HolderClass.tv_address_state.setText(addressModels.get(position).getState());
            HolderClass.tv_mob.setText(addressModels.get(position).getPrimary_contact());
        }
    }

    @Override
    public int getItemCount() {
        return addressModels.size();
    }

    public class HolderClass extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_name, tv_address_street, tv_address_state, tv_mob;

        public HolderClass(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_address_street = (TextView) itemView.findViewById(R.id.tv_address_street);
            tv_address_state = (TextView) itemView.findViewById(R.id.tv_address_state);
            tv_mob = (TextView) itemView.findViewById(R.id.tv_mob);
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
