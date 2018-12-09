package org.a3tn.pregnancy_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.a3tn.pregnancy_mobile.CustomAdapter.ListViewSummaryInfoAdapter;
import org.a3tn.pregnancy_mobile.Model.SummaryInfo;
import org.a3tn.pregnancy_mobile.apis.ApiFactory;
import org.a3tn.pregnancy_mobile.apis.Constants;
import org.a3tn.pregnancy_mobile.apis.api_services.SummaryInfoService;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ShowSummaryInfoList extends AppCompatActivity {

    private ListView lvSummaryInfo;
    private List<SummaryInfo> mSummaryInfoList;
    private ListViewSummaryInfoAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_summary_info_list);

        lvSummaryInfo = findViewById(R.id.listview_summary_info);
        getSummaryInfoData();
        setupEventHandler();
    }

    private void setupEventHandler() {
        lvSummaryInfo.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ShowSummaryInfoList.this, WeekDetails.class);
            int weekId = mSummaryInfoList.get(position).getId();
            intent.putExtra("weekId", weekId);
            //Toast.makeText(getApplicationContext(),"item thứ "+mSummaryInfoList.get(position).getId(),Toast.LENGTH_SHORT ).show();
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
                                String babyInfo = jsonObject.get("baby_info").getAsString();
                                String momInfo = jsonObject.get("mom_info").getAsString();

                                mSummaryInfoList.add(new SummaryInfo(id, weeks, babyInfo, momInfo));
//
                            }
                            mAdapter = new ListViewSummaryInfoAdapter(getApplicationContext(), mSummaryInfoList);
                            lvSummaryInfo.setAdapter(mAdapter);
                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()
                );
        createFakeData();

    }

    private void createFakeData() {
        for(int i=1; i<=42; i++){
            mSummaryInfoList.add(new SummaryInfo(i,"Tuan "+i, "baby","mom"));
        }
        mAdapter = new ListViewSummaryInfoAdapter(getApplicationContext(), mSummaryInfoList);
        lvSummaryInfo.setAdapter(mAdapter);
    }

}
