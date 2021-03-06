package com.simplemobiletools.calendar.pro;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.simplemobiletools.calendar.pro.CustomAdapter.GridViewDetailInfoAdapter;
import com.simplemobiletools.calendar.pro.Model.Glossary;
import com.simplemobiletools.calendar.pro.apis.ApiFactory;
import com.simplemobiletools.calendar.pro.apis.Constants;
import com.simplemobiletools.calendar.pro.apis.api_services.SummaryInfoService;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeekDetailsActivity extends Activity {
    private int week_id;
    private Bundle mBundle;

    private ArrayList<String> babyInfos = new ArrayList<>();
    private ArrayList<String> momInfos = new ArrayList<>();
    private ArrayList<String> symptoms = new ArrayList<>();
    private ArrayList<String> advices = new ArrayList<>();
    private List<Glossary> glossaries = new ArrayList<>();

    private GridView gvDetail;

    private BottomNavigationView mBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_week_details);
        mBundle = getIntent().getExtras();
        week_id = (int) mBundle.get("id");
        getGlossaryData();

        gvDetail = findViewById(R.id.gv_detail);
        mBottomNavigation = findViewById(R.id.navigation);

        mBottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navigation_baby:
                    showBabyInfo();
                    return true;
                case R.id.navigation_mom:
                    showMomInfo();
                    return true;
                case R.id.navigation_symptom:
                    showSymptomInfo();
                    return true;
                case R.id.navigation_advice:
                    showAdvices();
                    return true;
            }
            return false;
        });

        showBabyInfo();
    }

    private void getGlossaryData() {
        List<Glossary> data = new ArrayList<>();
        ApiFactory.createRetrofitService(SummaryInfoService.class, Constants.HOST_SERVER)
                .getAllGlossary()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> {
                            JsonArray summaryInfoList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : summaryInfoList) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                int id = jsonObject.get("id").getAsInt();
                                String word = jsonObject.get("word").getAsString();
                                String meaning = jsonObject.get("meaning").getAsString();
                                data.add(new Glossary(id, word, meaning));

                            }
                            glossaries = data;
                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }

    private void showBabyInfo() {
        if (babyInfos.size() > 0) {
            GridViewDetailInfoAdapter mBabyInfoAdapter = new GridViewDetailInfoAdapter(getApplicationContext(), babyInfos, R.drawable.img_detail_baby_info);
            gvDetail.setAdapter(mBabyInfoAdapter);
        } else {
            getBabyInfoData(week_id);
        }


    }

    private void getBabyInfoData(int week_id) {
        ArrayList<String> data = new ArrayList<>();
        ApiFactory.createRetrofitService(SummaryInfoService.class, Constants.HOST_SERVER)
                .getBabyInfoByWeek(week_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> {
                            JsonArray summaryInfoList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : summaryInfoList) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                String detail = jsonObject.get("detail").getAsString();
                                data.add(detail);

                            }
                            babyInfos = data;
                            GridViewDetailInfoAdapter mBabyInfoAdapter = new GridViewDetailInfoAdapter(getApplicationContext(), babyInfos, R.drawable.img_detail_baby_info);
                            gvDetail.setAdapter(mBabyInfoAdapter);
                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }

    private void showMomInfo() {
        if (momInfos.size() > 0) {
            GridViewDetailInfoAdapter mBabyInfoAdapter = new GridViewDetailInfoAdapter(getApplicationContext(), momInfos, R.drawable.img_detail_mom_info);
            gvDetail.setAdapter(mBabyInfoAdapter);
        } else {
            getMomInfoData(week_id);
        }


    }

    private void getMomInfoData(int week_id) {
        ArrayList<String> data = new ArrayList<>();
        ApiFactory.createRetrofitService(SummaryInfoService.class, Constants.HOST_SERVER)
                .getMomInfoByWeek(week_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> {
                            JsonArray summaryInfoList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : summaryInfoList) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                String detail = jsonObject.get("detail").getAsString();
                                data.add(detail);

                            }
                            momInfos = data;
                            GridViewDetailInfoAdapter mMomInfoAdapter = new GridViewDetailInfoAdapter(getApplicationContext(), momInfos, R.drawable.img_detail_mom_info);
                            gvDetail.setAdapter(mMomInfoAdapter);
                            //findWord();
                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }

    private void findWord() {
        String print = "";
        for (String sentence : momInfos) {
            for (Glossary glossary : glossaries)
                if (sentence.toLowerCase().contains(glossary.getWord().toLowerCase())) {
                    print += "id: " + glossary.getId() + "; word: " + glossary.getWord();

                }

            Toast.makeText(getApplicationContext(), print, Toast.LENGTH_LONG).show();
        }
    }


    private void showSymptomInfo() {
        if (symptoms.size() > 0) {
            GridViewDetailInfoAdapter mSymptomAdapter = new GridViewDetailInfoAdapter(getApplicationContext(), symptoms, R.drawable.img_detail_symptom_info);
            gvDetail.setAdapter(mSymptomAdapter);
        } else {
            getSymptomData(week_id);
        }
    }

    private void getSymptomData(int week_id) {
        ArrayList<String> data = new ArrayList<>();
        ApiFactory.createRetrofitService(SummaryInfoService.class, Constants.HOST_SERVER)
                .getSymptomByWeek(week_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> {
                            JsonArray summaryInfoList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : summaryInfoList) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                String detail = jsonObject.get("detail").getAsString();
                                data.add(detail);

                            }
                            symptoms = data;
                            GridViewDetailInfoAdapter mSymptomAdapter = new GridViewDetailInfoAdapter(getApplicationContext(), symptoms, R.drawable.img_detail_symptom_info);
                            gvDetail.setAdapter(mSymptomAdapter);
                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }

    private void showAdvices() {
        if (advices.size() > 0) {
            GridViewDetailInfoAdapter mAdviceAdapter = new GridViewDetailInfoAdapter(getApplicationContext(), advices, R.drawable.img_detail_advice);
            gvDetail.setAdapter(mAdviceAdapter);
        } else {
            getAdviceData(week_id);
        }
    }

    private void getAdviceData(int week_id) {
        ArrayList<String> data = new ArrayList<>();
        ApiFactory.createRetrofitService(SummaryInfoService.class, Constants.HOST_SERVER)
                .getAdviceByWeek(week_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> {
                            JsonArray adviceInfoList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : adviceInfoList) {
                                JsonObject jsonObject = element.getAsJsonObject();
                                String detail = jsonObject.get("detail").getAsString();

                                data.add(detail);
                            }
                            advices = data;
                            GridViewDetailInfoAdapter mAdviceAdapter = new GridViewDetailInfoAdapter(getApplicationContext(), advices, R.drawable.img_detail_advice);
                            gvDetail.setAdapter(mAdviceAdapter);
                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }
}
