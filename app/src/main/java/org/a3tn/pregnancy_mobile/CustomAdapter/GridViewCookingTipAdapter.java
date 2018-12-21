package org.a3tn.pregnancy_mobile.CustomAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.a3tn.pregnancy_mobile.Model.CookingTip;
import org.a3tn.pregnancy_mobile.R;

import java.util.List;

public class GridViewCookingTipAdapter extends BaseAdapter {
    private Context mContext;
    private List<CookingTip> mTips;

    public GridViewCookingTipAdapter(Context mContext, List<CookingTip> mTips) {
        this.mContext = mContext;
        this.mTips = mTips;
    }

    @Override
    public int getCount() {
        return mTips.size();
    }

    @Override
    public Object getItem(int position) {
        return mTips.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mTips.get(position).getId();
    }

    @Override
    public View getView(int position, View recycled, ViewGroup parent) {
        View itemView;
        if (recycled == null) {
            itemView = View.inflate(mContext, R.layout.custom_item_cooking_tip, null);
        } else {
            itemView = recycled;
        }
        TextView tvTip = itemView.findViewById(R.id.tv_item_cooking_tip);
        tvTip.setText(mTips.get(position).getTip());
        itemView.setTag(mTips.get(position));

        return itemView;
    }
}
