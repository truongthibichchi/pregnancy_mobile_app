package org.a3tn.pregnancy_mobile;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
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

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private ListView lvSummaryInfo;
    private List<SummaryInfo> mSummaryInfoList;
    private ListViewSummaryInfoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FindViewById();

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSummaryInfoData();


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

    }

    private void FindViewById() {
        mDrawerLayout = findViewById(R.id.drawer);
        lvSummaryInfo = findViewById(R.id.listview_summary_info);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
