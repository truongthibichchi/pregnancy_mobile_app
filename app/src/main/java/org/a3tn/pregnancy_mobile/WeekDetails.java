package org.a3tn.pregnancy_mobile;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.a3tn.pregnancy_mobile.CustomAdapter.ListViewDetailInfoAdapter;
import org.a3tn.pregnancy_mobile.Model.Glossary;
import org.a3tn.pregnancy_mobile.apis.ApiFactory;
import org.a3tn.pregnancy_mobile.apis.Constants;
import org.a3tn.pregnancy_mobile.apis.api_services.SummaryInfoService;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeekDetails extends AppCompatActivity {
    private int week_id;
    private Bundle mBundle;

    private ArrayList<String> babyInfos =  new ArrayList<>();
    private ArrayList<String> momInfos =  new ArrayList<>();
    private ArrayList<String> symptoms =  new ArrayList<>();
    private ArrayList<String> advices =  new ArrayList<>();
    private List<Glossary> glossaries = new ArrayList<>();

    private ListView lvDetail;

    private BottomNavigationView mBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_details);
        mBundle = getIntent().getExtras();
        week_id = (int) mBundle.get("id");
        getGlossaryData();

        lvDetail = findViewById(R.id.lv_detail);
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
                        res->{
                            JsonArray summaryInfoList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : summaryInfoList) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                int id = jsonObject.get("id").getAsInt();
                                String word = jsonObject.get("word").getAsString();
                                String meaning = jsonObject.get("meaning").getAsString();
                                data.add(new Glossary(id, word, meaning));

                            }
                            glossaries=data;
//                            ListViewDetailInfoAdapter mMomInfoAdapter = new ListViewDetailInfoAdapter(getApplicationContext(), momInfos, R.drawable.img_detail_mom_info);
////                            lvDetail.setAdapter(mMomInfoAdapter);
                        },
                        err-> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }

    private void showBabyInfo() {
        if(babyInfos.size()>0){
            ListViewDetailInfoAdapter mBabyInfoAdapter = new ListViewDetailInfoAdapter(getApplicationContext(), babyInfos, R.drawable.img_detail_baby_info);
            lvDetail.setAdapter(mBabyInfoAdapter);
        }
        else{
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
                        res->{
                            JsonArray summaryInfoList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : summaryInfoList) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                String detail = jsonObject.get("detail").getAsString();
                                data.add(detail);

                            }
                            babyInfos=data;
                            ListViewDetailInfoAdapter mBabyInfoAdapter = new ListViewDetailInfoAdapter(getApplicationContext(), babyInfos, R.drawable.img_detail_baby_info);
                            lvDetail.setAdapter(mBabyInfoAdapter);
                        },
                        err-> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }

    private void showMomInfo() {
        if(momInfos.size()>0){
            ListViewDetailInfoAdapter mBabyInfoAdapter = new ListViewDetailInfoAdapter(getApplicationContext(), momInfos, R.drawable.img_detail_mom_info);
            lvDetail.setAdapter(mBabyInfoAdapter);
        }
        else{
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
                        res->{
                            JsonArray summaryInfoList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : summaryInfoList) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                String detail = jsonObject.get("detail").getAsString();
                                data.add(detail);

                            }
                            momInfos=data;
                            ListViewDetailInfoAdapter mMomInfoAdapter = new ListViewDetailInfoAdapter(getApplicationContext(), momInfos, R.drawable.img_detail_mom_info);
                            lvDetail.setAdapter(mMomInfoAdapter);
                            findWord();
                        },
                        err-> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }

    private void findWord() {
        String print = "";
        for (String sentence:momInfos){
            for(Glossary glossary:glossaries)
                if(sentence.toLowerCase().contains(glossary.getWord().toLowerCase())){
                print+="id: "+glossary.getId()+ "; word: "+glossary.getWord();

                }

            Toast.makeText(getApplicationContext(),print, Toast.LENGTH_LONG).show();
        }
//        String sentence = "Check this answer and you can find the keyword with this code";
//        String search = "keyword";
//
//        if (sentence.toLowerCase().contains(search.toLowerCase())) {
//            Toast.makeText(getApplicationContext(), "I found the keyword", Toast.LENGTH_SHORT).show();
//
//        }
//        else {
//
//            Toast.makeText(getApplicationContext(), "not found", Toast.LENGTH_SHORT).show();
//
//        }
    }


    private void showSymptomInfo() {
        if(symptoms.size()>0){
            ListViewDetailInfoAdapter mSymptomAdapter = new ListViewDetailInfoAdapter(getApplicationContext(), symptoms, R.drawable.img_detail_symptom_info);
            lvDetail.setAdapter(mSymptomAdapter);
        }
        else{
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
                        res->{
                            JsonArray summaryInfoList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : summaryInfoList) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                String detail = jsonObject.get("detail").getAsString();
                                data.add(detail);

                            }
                            symptoms=data;
                            ListViewDetailInfoAdapter mSymptomAdapter = new ListViewDetailInfoAdapter(getApplicationContext(), symptoms, R.drawable.img_detail_symptom_info);
                            lvDetail.setAdapter(mSymptomAdapter);
                        },
                        err-> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }

    private void showAdvices() {
        if(advices.size()>0){
            ListViewDetailInfoAdapter mAdviceAdapter = new ListViewDetailInfoAdapter(getApplicationContext(), advices, R.drawable.img_detail_advice);
            lvDetail.setAdapter(mAdviceAdapter);
        }
        else{
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
                        res->{
                            JsonArray adviceInfoList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : adviceInfoList) {
                                JsonObject jsonObject = element.getAsJsonObject();
                                String detail = jsonObject.get("detail").getAsString();

                                data.add(detail);
                            }
                            advices=data;
                            ListViewDetailInfoAdapter mAdviceAdapter = new ListViewDetailInfoAdapter(getApplicationContext(), advices, R.drawable.img_detail_advice);
                            lvDetail.setAdapter(mAdviceAdapter);
                        },
                        err-> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }
}
