package org.a3tn.pregnancy_mobile.CustomAdapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.a3tn.pregnancy_mobile.R;

import java.util.ArrayList;

public class GridViewDetailInfoAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> details;
    private int icon;

    public GridViewDetailInfoAdapter(Context mContext, ArrayList<String> details, int icon) {
        this.mContext = mContext;
        this.details = details;
        this.icon = icon;
    }

    @Override
    public int getCount() {
        return details.size();
    }

    @Override
    public Object getItem(int position) {
        return details.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View recycled, ViewGroup parent) {
        View itemView;
        if (recycled == null) {
            itemView = View.inflate(mContext, R.layout.custom_item_detail_info, null);
        } else {
            itemView = recycled;
        }
        TextView textView = itemView.findViewById(R.id.tv_detail);
        ImageView imageView = itemView.findViewById(R.id.img_icon);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        textView.setText(details.get(position));
//        imageView.setImageResource(images[position]);
        Glide
                .with(mContext)
                .load(icon)
                .into(imageView);

        itemView.setTag(details.get(position));

        LinearLayout llparent = itemView.findViewById(R.id.item_detail_parent);
        if(position%2 !=0){
            llparent.setBackgroundResource(R.color.blue);
        }
        else{
            llparent.setBackgroundResource(R.color.home_color);
        }
        return itemView;

    }
}
