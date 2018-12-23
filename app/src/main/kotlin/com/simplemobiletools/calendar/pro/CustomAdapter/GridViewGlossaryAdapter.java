package com.simplemobiletools.calendar.pro.CustomAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.simplemobiletools.calendar.pro.Model.Glossary;
import com.simplemobiletools.calendar.pro.R;

import java.util.List;

public class GridViewGlossaryAdapter extends BaseAdapter {
    private Context mContext;
    private List<Glossary> mGlossaries;
    private int images[] ={};

    public GridViewGlossaryAdapter(Context mContext, List<Glossary> mGlossaries) {
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
        textView.setText(mGlossaries.get(position).getWord());

        itemView.setTag(mGlossaries.get(position).getId());

        return itemView;
    }
}
