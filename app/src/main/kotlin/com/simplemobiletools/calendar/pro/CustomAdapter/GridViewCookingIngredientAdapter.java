package com.simplemobiletools.calendar.pro.CustomAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.simplemobiletools.calendar.pro.Model.CookingIngredient;
import com.simplemobiletools.calendar.pro.R;

import java.util.List;

public class GridViewCookingIngredientAdapter extends BaseAdapter{

    private Context mContext;
    private List<CookingIngredient> mCookingIngredients;

    public GridViewCookingIngredientAdapter(Context mContext, List<CookingIngredient> mCookingIngredients) {
        this.mContext = mContext;
        this.mCookingIngredients = mCookingIngredients;
    }

    @Override
    public int getCount() {
        return mCookingIngredients.size();
    }

    @Override
    public Object getItem(int position) {
        return mCookingIngredients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mCookingIngredients.get(position).getId();
    }

    @Override
    public View getView(int position, View recycled, ViewGroup parent) {
        View itemView;
        if (recycled == null) {
            itemView = View.inflate(mContext, R.layout.custom_item_cooking_ingredient, null);
        } else {
            itemView = recycled;
        }
        TextView tvName = itemView.findViewById(R.id.tv_item_cooking_ingredient_name);
        TextView tvMesurement = itemView.findViewById(R.id.tv_item_cooking_ingredient_mesure);
        tvName.setText(mCookingIngredients.get(position).getIngredient());
        tvMesurement.setText(mCookingIngredients.get(position).getMesurement());

        itemView.setTag(mCookingIngredients.get(position));

        return itemView;
    }
}
