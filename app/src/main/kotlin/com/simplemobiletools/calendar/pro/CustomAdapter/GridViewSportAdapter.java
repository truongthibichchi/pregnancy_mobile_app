package com.simplemobiletools.calendar.pro.CustomAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.simplemobiletools.calendar.pro.Model.Sport;
import com.simplemobiletools.calendar.pro.R;
import com.simplemobiletools.calendar.pro.apis.Constants;

import java.util.List;

public class GridViewSportAdapter extends BaseAdapter {
    private Context mContext;
    private List<Sport> mSports;

    public GridViewSportAdapter(Context mContext, List<Sport> mSports) {
        this.mContext = mContext;
        this.mSports = mSports;
    }

    @Override
    public int getCount() {
        return mSports.size();
    }

    @Override
    public Object getItem(int position) {
        return mSports.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mSports.get(position).getId();
    }

    @Override
    public View getView(int position, View recycled, ViewGroup parent) {
        View itemView;
        if (recycled == null) {
            itemView = View.inflate(mContext, R.layout.custom_item_sport, null);
        } else {
            itemView = recycled;
        }
        TextView textView = itemView.findViewById(R.id.tv_item_sport);
        ImageView imageView = itemView.findViewById(R.id.img_item_sport);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        textView.setText(mSports.get(position).getSportName());
        String pictureUrl = Constants.STATIC_URL+"sports/"+mSports.get(position).getPicture();
        Glide
                .with(mContext)
                .load(pictureUrl)
                .into(imageView);

        itemView.setTag(mSports.get(position));

        return itemView;
    }
}
