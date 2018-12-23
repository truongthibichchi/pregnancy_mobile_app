package com.simplemobiletools.calendar.pro.CustomAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.simplemobiletools.calendar.pro.Model.Cooking;
import com.simplemobiletools.calendar.pro.R;
import com.simplemobiletools.calendar.pro.apis.Constants;


import java.util.List;

public class GridViewCookingAdapter extends BaseAdapter {
    private Context context;
    private List<Cooking> cookings;

    public GridViewCookingAdapter(Context context, List<Cooking> cookings) {
        this.context = context;
        this.cookings = cookings;
    }


    @Override
    public int getCount() {
        return cookings.size();
    }

    @Override
    public Object getItem(int position) {
        return cookings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cookings.get(position).getId();
    }

    @Override
    public View getView(int position, View recycled, ViewGroup parent) {
        View itemView;
        if (recycled == null) {
            itemView = View.inflate(context, R.layout.custom_item_cooking, null);
        } else {
            itemView = recycled;
        }
        TextView textView = itemView.findViewById(R.id.tv_item_cooking_food_name);
        ImageView imageView = itemView.findViewById(R.id.img_item_cooking_picture);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        textView.setText(cookings.get(position).getFoodName());
//        imageView.setImageResource(images[position]);
        String pictureUrl = Constants.STATIC_URL+"foods/"+ cookings.get(position).getPicture();
        Glide
                .with(context)
                .load(pictureUrl)
                .into(imageView);

        itemView.setTag(cookings.get(position));

        return itemView;
    }
}
