package org.a3tn.pregnancy_mobile;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.a3tn.pregnancy_mobile.CustomAdapter.ListViewDetailInfoAdapter;
import org.a3tn.pregnancy_mobile.CustomAdapter.ListViewSummaryInfoAdapter;
import org.a3tn.pregnancy_mobile.Model.SummaryInfo;
import org.a3tn.pregnancy_mobile.apis.ApiFactory;
import org.a3tn.pregnancy_mobile.apis.Constants;
import org.a3tn.pregnancy_mobile.apis.api_services.SummaryInfoService;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeekDetails extends AppCompatActivity {
    private int week_id;
    private Bundle mBundle;

    private ArrayList<String> babyInfos =  new ArrayList<>();
    private ArrayList<String> momInfos =  new ArrayList<>();
    private ArrayList<String> symptoms =  new ArrayList<>();
    private ArrayList<String> advices =  new ArrayList<>();

    private ListView lvDetail;

    private BottomNavigationView mBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_details);
        mBundle = getIntent().getExtras();
        week_id = (int) mBundle.get("id");

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
    }

    private void showBabyInfo() {



    }

    private void showMomInfo() {

    }



    private void showSymptomInfo() {
        if(symptoms.size()>0){
            ListViewDetailInfoAdapter mSymptomAdapter = new ListViewDetailInfoAdapter(getApplicationContext(), symptoms, R.drawable.symptom_info);
            lvDetail.setAdapter(mSymptomAdapter);
            //Toast.makeText(getApplicationContext(), "có rồi", Toast.LENGTH_SHORT).show();
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
                            ListViewDetailInfoAdapter mSymptomAdapter = new ListViewDetailInfoAdapter(getApplicationContext(), symptoms, R.drawable.symptom_info);
                            lvDetail.setAdapter(mSymptomAdapter);
                        },
                        err-> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }

    private void showAdvices() {
        if(advices.size()>0){
            ListViewDetailInfoAdapter mAdviceAdapter = new ListViewDetailInfoAdapter(getApplicationContext(), advices, R.drawable.advice_info);
            lvDetail.setAdapter(mAdviceAdapter);
            //Toast.makeText(getApplicationContext(), "có rồi", Toast.LENGTH_SHORT).show();
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
                            ListViewDetailInfoAdapter mAdviceAdapter = new ListViewDetailInfoAdapter(getApplicationContext(), advices, R.drawable.advice_info);
                            lvDetail.setAdapter(mAdviceAdapter);
                        },
                        err-> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }
}
