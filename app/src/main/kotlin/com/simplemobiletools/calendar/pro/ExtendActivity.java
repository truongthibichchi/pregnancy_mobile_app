package com.simplemobiletools.calendar.pro;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.simplemobiletools.calendar.pro.CustomAdapter.GridViewExtendAdapter;
import com.simplemobiletools.calendar.pro.CustomAdapter.GridViewSummaryInfoAdapter;
import com.simplemobiletools.calendar.pro.Model.ImageForBaby;
import com.simplemobiletools.calendar.pro.Model.SummaryInfo;
import com.simplemobiletools.calendar.pro.apis.ApiFactory;
import com.simplemobiletools.calendar.pro.apis.Constants;
import com.simplemobiletools.calendar.pro.apis.api_services.ExtendService;
import com.simplemobiletools.calendar.pro.apis.api_services.SummaryInfoService;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ExtendActivity extends AppCompatActivity {
    private GridView gvExtend;
    private List<ImageForBaby> mImages;
    private GridViewExtendAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend);

        gvExtend = findViewById(R.id.gv_extend);
        getImageForBabyData();



    }

    private void getImageForBabyData() {
        mImages = new ArrayList<>();
        ApiFactory.createRetrofitService(ExtendService.class, Constants.HOST_SERVER)
                .getAllImageForBaby()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> {
                            JsonArray summaryInfoList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : summaryInfoList) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                int id = jsonObject.get("id").getAsInt();
                                String picture = jsonObject.get("picture").getAsString();
                                mImages.add(new ImageForBaby(id, picture));
//
                            }
                            mAdapter = new GridViewExtendAdapter(getApplicationContext(), mImages);
                            gvExtend.setAdapter(mAdapter);
                            setupEventHandler();
                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }

    private void setupEventHandler() {
        gvExtend.setOnItemClickListener((parent, view, position, id) -> {
            final Dialog dialog = new Dialog(ExtendActivity.this);
            dialog.setContentView(R.layout.dialog_image_for_baby);

            ImageView imgPicture = dialog.findViewById(R.id.img_dialog_image_for_baby);
            Glide
                    .with(getApplicationContext())
                    .load(mImages.get(position).getPicture())
                    .into(imgPicture);

            dialog.show();
        });
    }


}
