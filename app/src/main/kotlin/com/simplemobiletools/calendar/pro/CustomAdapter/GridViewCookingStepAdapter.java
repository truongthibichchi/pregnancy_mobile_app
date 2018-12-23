package com.simplemobiletools.calendar.pro.CustomAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.simplemobiletools.calendar.pro.Model.CookingStep;
import com.simplemobiletools.calendar.pro.R;

import java.util.List;

public class GridViewCookingStepAdapter extends BaseAdapter {
    private Context mContext;
    private List<CookingStep> mSteps;

    public GridViewCookingStepAdapter(Context mContext, List<CookingStep> mSteps) {
        this.mContext = mContext;
        this.mSteps = mSteps;
    }

    @Override
    public int getCount() {
        return mSteps.size();
    }

    @Override
    public Object getItem(int position) {
        return mSteps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mSteps.get(position).getId();
    }

    @Override
    public View getView(int position, View recycled, ViewGroup parent) {
        View itemView;
        if (recycled == null) {
            itemView = View.inflate(mContext, R.layout.custom_item_cooking_step, null);
        } else {
            itemView = recycled;
        }
        TextView tvStep = itemView.findViewById(R.id.tv_item_cooking_step);
        tvStep.setText(mSteps.get(position).getDetail());

//        LinearLayout llparent = itemView.findViewById(R.id.item_cooking_step_parent);
//        if(position%2 !=0){
//            llparent.setBackgroundResource(R.color.blue);
//        }
//        else{
//            llparent.setBackgroundResource(R.color.home_color);
//        }
        itemView.setTag(mSteps.get(position));

        return itemView;
    }
}
