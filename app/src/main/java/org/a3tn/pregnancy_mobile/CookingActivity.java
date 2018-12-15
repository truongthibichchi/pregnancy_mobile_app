package org.a3tn.pregnancy_mobile;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.a3tn.pregnancy_mobile.CustomAdapter.ListViewCookingAdapter;
import org.a3tn.pregnancy_mobile.CustomAdapter.ListViewDetailInfoAdapter;
import org.a3tn.pregnancy_mobile.Model.CookingDetail;
import org.a3tn.pregnancy_mobile.Model.CookingIngredient;
import org.a3tn.pregnancy_mobile.Model.CookingStep;
import org.a3tn.pregnancy_mobile.Model.CookingTip;
import org.a3tn.pregnancy_mobile.Model.Glossary;
import org.a3tn.pregnancy_mobile.apis.ApiFactory;
import org.a3tn.pregnancy_mobile.apis.Constants;
import org.a3tn.pregnancy_mobile.apis.api_services.CookingService;
import org.a3tn.pregnancy_mobile.apis.api_services.SummaryInfoService;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CookingActivity extends AppCompatActivity {

    private List<CookingDetail> mCookingDetails;
    private List<CookingIngredient> mCookingIngredients;
    private List<CookingStep> mCookingSteps;
    private List<CookingTip> mCookingTips;

    private ListView lvCooking;
    private BottomNavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking);

        lvCooking= findViewById(R.id.lv_cooking);
        mNavigationView = findViewById(R.id.navigation_cooking);

        mNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.navigation_cooking_list:
                    showCookingList();
                    return true;
                case R.id.navigation_cooking_schedule:
                    showCookingSchedule();
                    return true;
            }
            return false;
        });

        showCookingList();
    }

    private void showCookingSchedule() {

    }

    private void showCookingList() {
        List<CookingDetail> data = new ArrayList<>();
        ApiFactory.createRetrofitService(CookingService.class, Constants.HOST_SERVER)
                .getAllCookingInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res->{
                            JsonArray cookingList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : cookingList) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                int id = jsonObject.get("id").getAsInt();
                                String foodName = jsonObject.get("food_name").getAsString();
                                String description = jsonObject.get("descriptions").getAsString();
                                String picture = jsonObject.get("picture").getAsString();
                                data.add(new CookingDetail(id,foodName,description,picture));

                            }
                            mCookingDetails=data;
                            ListViewCookingAdapter mCookingAdapter = new ListViewCookingAdapter(getApplicationContext(), mCookingDetails);
                            lvCooking.setAdapter(mCookingAdapter);
                        },
                        err-> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }
}
