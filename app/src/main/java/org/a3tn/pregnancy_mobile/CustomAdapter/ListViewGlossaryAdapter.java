package org.a3tn.pregnancy_mobile.CustomAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.a3tn.pregnancy_mobile.Model.Glossary;
import org.a3tn.pregnancy_mobile.R;

import java.util.List;

public class ListViewGlossaryAdapter extends BaseAdapter {
    private Context mContext;
    private List<Glossary> mGlossaries;
    private int images[] ={};

    public ListViewGlossaryAdapter(Context mContext, List<Glossary> mGlossaries) {
        this.mContext = mContext;
        this.mGlossaries = mGlossaries;
    }

    @Override
    public int getCount() {
        return mGlossaries.size();
    }

    @Override
    public Object getItem(int position) {
        return mGlossaries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;
        if (convertView == null) {
            itemView = View.inflate(mContext, R.layout.custom_item_glossary, null);
        } else {
            itemView = convertView;
        }
        TextView textView = itemView.findViewById(R.id.tv_glossary);
        ImageView imageView = itemView.findViewById(R.id.img_glossary);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView imgIcon = itemView.findViewById(R.id.img_icon);
        imgIcon.setScaleType(ImageView.ScaleType.FIT_XY);

        textView.setText(mGlossaries.get(position).getWord());
//        imageView.setImageResource(images[position]);
        Glide
                .with(mContext)
                .load(R.drawable.ic_dashboard_black_24dp)
                .into(imageView );

        Glide
                .with(mContext)
                .load(R.drawable.ic_navigation_menu_chevron_right)
                .into(imgIcon );


        itemView.setTag(mGlossaries.get(position).getId());

        return itemView;
    }
}
