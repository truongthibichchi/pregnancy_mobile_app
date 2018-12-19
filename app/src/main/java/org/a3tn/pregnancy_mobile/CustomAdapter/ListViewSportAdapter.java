package org.a3tn.pregnancy_mobile.CustomAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.a3tn.pregnancy_mobile.Model.Sport;
import org.a3tn.pregnancy_mobile.R;
import org.a3tn.pregnancy_mobile.apis.Constants;

import java.util.List;

public class ListViewSportAdapter extends BaseAdapter {
    private Context mContext;
    private List<Sport> mSports;

    public ListViewSportAdapter(Context mContext, List<Sport> mSports) {
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
//        imageView.setImageResource(images[position]);
        String pictureUrl = Constants.STATIC_URL+"sports/"+mSports.get(position).getPicture();
        Glide
                .with(mContext)
                .load(pictureUrl)
                .into(imageView);

        itemView.setTag(mSports.get(position));

        return itemView;
    }
}
