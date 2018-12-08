package org.a3tn.pregnancy_mobile.CustomAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.a3tn.pregnancy_mobile.Model.SummaryInfo;
import org.a3tn.pregnancy_mobile.R;

import java.util.List;

public class ListViewSummaryInfoAdapter extends BaseAdapter {
    private Context mContext;
    private List<SummaryInfo> mSummaryInfoList;
    private int[] images = {
            R.drawable.week_1, R.drawable.week_2, R.drawable.week_3,
            R.drawable.week_4, R.drawable.week_5, R.drawable.week_6,
            R.drawable.week_7, R.drawable.week_8, R.drawable.week_9,
            R.drawable.week_10, R.drawable.week_11, R.drawable.week_12,
            R.drawable.week_13, R.drawable.week_14, R.drawable.week_15,
            R.drawable.week_16, R.drawable.week_17, R.drawable.week_18,
            R.drawable.week_19, R.drawable.week_20, R.drawable.week_21,
            R.drawable.week_22, R.drawable.week_23, R.drawable.week_24,
            R.drawable.week_25, R.drawable.week_26, R.drawable.week_27,
            R.drawable.week_28, R.drawable.week_29, R.drawable.week_30,
            R.drawable.week_31, R.drawable.week_32, R.drawable.week_33,
            R.drawable.week_34, R.drawable.week_35, R.drawable.week_36,
            R.drawable.week_37, R.drawable.week_38, R.drawable.week_39,
            R.drawable.week_40, R.drawable.week_41, R.drawable.week_42
    };


    public ListViewSummaryInfoAdapter(Context mContext, List<SummaryInfo> mSummaryInfoList) {
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
        TextView textView = itemView.findViewById(R.id.tv_week);
        ImageView imageView = itemView.findViewById(R.id.img_image);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        textView.setText(mSummaryInfoList.get(position).getWeeks());
//        imageView.setImageResource(images[position]);
        Glide
                .with(mContext)
                .load(images[position])
                .into(imageView);

        itemView.setTag(mSummaryInfoList.get(position).getId());

        return itemView;
    }
}
