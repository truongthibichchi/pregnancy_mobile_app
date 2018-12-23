package com.simplemobiletools.calendar.pro.CustomAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.simplemobiletools.calendar.pro.Model.SummaryInfo;
import com.simplemobiletools.calendar.pro.R;
import com.simplemobiletools.calendar.pro.apis.Constants;


import java.util.List;

public class GridViewSummaryInfoAdapter extends BaseAdapter {
    private Context mContext;
    private List<SummaryInfo> mSummaryInfoList;

    public GridViewSummaryInfoAdapter(Context mContext, List<SummaryInfo> mSummaryInfoList) {
        this.mContext = mContext;
        this.mSummaryInfoList = mSummaryInfoList;
    }

    @Override

    public int getCount() {
        return mSummaryInfoList.size();
    }

    @Override
    public Object getItem(int position) {

        return mSummaryInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View recycled, ViewGroup parent) {
        View itemView;
        if (recycled == null) {
            itemView = View.inflate(mContext, R.layout.custom_item_summary_info, null);
        } else {
            itemView = recycled;
        }
        TextView textView = itemView.findViewById(R.id.tv_item_summary_info_week);
        ImageView imageView = itemView.findViewById(R.id.img_item_summary_info);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        textView.setText(mSummaryInfoList.get(position).getWeeks());
        String pictureUrl = Constants.STATIC_URL+"weeks/"+mSummaryInfoList.get(position).getPicture();
//        imageView.setImageResource(images[position]);
        Glide
                .with(mContext)
                .load(pictureUrl)
                .into(imageView);

        itemView.setTag(mSummaryInfoList.get(position).getId());

        return itemView;
    }
}
