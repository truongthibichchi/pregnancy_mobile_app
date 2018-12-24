package com.simplemobiletools.calendar.pro;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.simplemobiletools.calendar.pro.CustomAdapter.GridViewSummaryInfoAdapter;
import com.simplemobiletools.calendar.pro.Model.SummaryInfo;
import com.simplemobiletools.calendar.pro.activities.SplashActivity;
import com.simplemobiletools.calendar.pro.apis.ApiFactory;
import com.simplemobiletools.calendar.pro.apis.Constants;
import com.simplemobiletools.calendar.pro.apis.api_services.SummaryInfoService;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigation;

    private GridView gvMainSummaryInfo;
    private List<SummaryInfo> mSummaryInfoList;
    private GridViewSummaryInfoAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FindViewById();

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNavigation = findViewById(R.id.navigation_menu);
        mNavigation.setNavigationItemSelectedListener(this);

        getSummaryInfoData();
        setupEventHandler();


    }

    private void setupEventHandler() {
        gvMainSummaryInfo.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, WeekDetailsActivity.class);
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
                            gvMainSummaryInfo.setAdapter(mAdapter);
                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()
                );

    }

    private void FindViewById() {
        mDrawerLayout = findViewById(R.id.drawer);
        gvMainSummaryInfo = findViewById(R.id.gv_main_summary_info);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_summary_info:
                onCickMenuSummaryInfo();
                break;

            case R.id.menu_glossary:
                onClickMenuGlossary();
                break;

            case R.id.menu_food:
                onClickMenuFood();
                break;

            case R.id.menu_sport:
                onClickMenuSport();
                break;

            case R.id.menu_plan:
                onClickMenuPlan();
                break;

            case R.id.menu_mom_image:
                onClickMenuMomImage();
                break;
        }
        return true;
    }

    private void onClickMenuMomImage() {
        Intent intent = new Intent(MainActivity.this, ExtendActivity.class);
        startActivity(intent);

    }

    private void onClickMenuPlan() {
        Intent intent = new Intent(MainActivity.this, SplashActivity.class);
        startActivity(intent);
    }

    private void onClickMenuSport() {
        Intent intent = new Intent(MainActivity.this, SportActivity.class);
        startActivity(intent);
    }

    private void onClickMenuGlossary() {
        Intent intent = new Intent(MainActivity.this, GlossaryActivity.class);
        startActivity(intent);
    }

    private void onClickMenuFood() {
        Intent intent = new Intent(MainActivity.this, CookingActivity.class);
        startActivity(intent);
    }

    private void onCickMenuSummaryInfo() {
        Intent intent = new Intent(MainActivity.this, SummaryInfoActivity.class);
        startActivity(intent);
    }

}
