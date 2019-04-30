package com.camellia.soorty.adapters;



import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import com.camellia.soorty.R;


import java.util.ArrayList;

public class TopBannersSliding_Adapter extends PagerAdapter {

    private ArrayList<Integer> Imagesarraylist;
    Context mcontext;
    private LayoutInflater minflater;

    public TopBannersSliding_Adapter(Context context,ArrayList<Integer> bottomarraylist)
    {
        this.Imagesarraylist=bottomarraylist;
        this.mcontext=context;
        this.minflater=LayoutInflater.from(mcontext);

    }

    @Override
    public int getCount() {
        return Imagesarraylist.size();

    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View imagelayoutview=minflater.inflate(R.layout.top_images_sliding_layout,container,false);


        final ImageView imageView =  imagelayoutview
                .findViewById(R.id.top_images_home_page);


        imageView.setImageResource(Imagesarraylist.get(position));

        container.addView(imagelayoutview, 0);



        assert imagelayoutview != null;

       /* Picasso.with(mcontext)
                .load(Imagesarraylist.get(position))
                .placeholder(mcontext.getResources().getDrawable(R.drawable.home_page_banner))
                .into(imageView);
        container.addView(imagelayoutview, 0);*/

        return imagelayoutview;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Nullable
    @Override
    public Parcelable saveState() {
        return super.saveState();
    }

    @Override
    public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {
        super.restoreState(state, loader);
    }
}
