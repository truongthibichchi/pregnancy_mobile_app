package com.simplemobiletools.calendar.pro.CustomAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.simplemobiletools.calendar.pro.Model.ImageForBaby;
import com.simplemobiletools.calendar.pro.R;

import java.util.List;

public class GridViewExtendAdapter extends BaseAdapter {
    private Context mContext;
    private List<ImageForBaby> mImages;

    public GridViewExtendAdapter(Context mContext, List<ImageForBaby> mImages) {
        this.mContext = mContext;
        this.mImages = mImages;
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public Object getItem(int position) {
        return mImages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mImages.get(position).getId();
    }

    @Override
    public View getView(int position, View recycled, ViewGroup parent) {
        View itemView;
        if (recycled == null) {
            itemView = View.inflate(mContext, R.layout.custom_item_extend, null);
        } else {
            itemView = recycled;
        }
        ImageView imageView = itemView.findViewById(R.id.img_item_extend);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        String pictureUrl = mImages.get(position).getPicture();
//        imageView.setImageResource(images[position]);
        Glide
                .with(mContext)
                .load(pictureUrl)
                .into(imageView);

        itemView.setTag(mImages.get(position).getId());

        return itemView;
    }
}
