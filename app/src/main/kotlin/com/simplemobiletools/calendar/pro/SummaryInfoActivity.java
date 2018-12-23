package com.simplemobiletools.calendar.pro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.simplemobiletools.calendar.pro.CustomAdapter.GridViewSummaryInfoAdapter;
import com.simplemobiletools.calendar.pro.Model.SummaryInfo;
import com.simplemobiletools.calendar.pro.apis.ApiFactory;
import com.simplemobiletools.calendar.pro.apis.Constants;
import com.simplemobiletools.calendar.pro.apis.api_services.SummaryInfoService;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SummaryInfoActivity extends Activity {

    private GridView glSummaryInfo;
    private List<SummaryInfo> mSummaryInfoList;
    private GridViewSummaryInfoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show_summary_info_list);


        glSummaryInfo = findViewById(R.id.gv_summary_info);
        getSummaryInfoData();
        setupEventHandler();
    }

    private void setupEventHandler() {
        glSummaryInfo.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(SummaryInfoActivity.this, WeekDetailsActivity.class);
            int weekId = mSummaryInfoList.get(position).getId();
            intent.putExtra("id", weekId);
            startActivity(intent);
        });
    }

    private void getSummaryInfoData() {
        mSummaryInfoList = new ArrayList<>();
        ApiFactory.createRetrofitService(SummaryInfoService.class, Constants.HOST_SERVER)
                .getAllSummaryInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> {
                            JsonArray summaryInfoList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : summaryInfoList) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                int id = jsonObject.get("id").getAsInt();
                                String weeks = jsonObject.get("weeks").getAsString();

                                String picture = jsonObject.get("picture").getAsString();
                                mSummaryInfoList.add(new SummaryInfo(id, weeks, picture));
//
                            }
                            mAdapter = new GridViewSummaryInfoAdapter(getApplicationContext(), mSummaryInfoList);
                            glSummaryInfo.setAdapter(mAdapter);
                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()
                );

    }
}
