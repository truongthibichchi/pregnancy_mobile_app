package org.a3tn.pregnancy_mobile.CustomAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.a3tn.pregnancy_mobile.Model.CookingDetail;
import org.a3tn.pregnancy_mobile.R;
import org.a3tn.pregnancy_mobile.apis.Constants;

import java.util.List;

public class GridViewCookingAdapter extends BaseAdapter {
    private Context context;
    private List<CookingDetail> cookingDetails;

    public GridViewCookingAdapter(Context context, List<CookingDetail> cookingDetails) {
        this.context = context;
        this.cookingDetails = cookingDetails;
    }


    @Override
    public int getCount() {
        return cookingDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return cookingDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cookingDetails.get(position).getId();
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

        textView.setText(cookingDetails.get(position).getFoodName());
//        imageView.setImageResource(images[position]);
        String pictureUrl = Constants.STATIC_URL+"foods/"+cookingDetails.get(position).getPicture();
        Glide
                .with(context)
                .load(pictureUrl)
                .into(imageView);

        itemView.setTag(cookingDetails.get(position));

        return itemView;
    }
}
