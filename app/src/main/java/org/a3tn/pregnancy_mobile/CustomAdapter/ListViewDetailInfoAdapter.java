package org.a3tn.pregnancy_mobile.CustomAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.a3tn.pregnancy_mobile.R;

import java.util.ArrayList;

public class ListViewDetailInfoAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> details;
    private int icon;

    public ListViewDetailInfoAdapter(Context mContext, ArrayList<String> details, int icon) {
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

        return itemView;
    }
}
