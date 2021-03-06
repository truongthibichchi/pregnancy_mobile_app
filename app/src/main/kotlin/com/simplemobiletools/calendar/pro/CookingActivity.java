package com.simplemobiletools.calendar.pro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.simplemobiletools.calendar.pro.CustomAdapter.GridViewCookingAdapter;
import com.simplemobiletools.calendar.pro.Model.Cooking;
import com.simplemobiletools.calendar.pro.Model.CookingIngredient;
import com.simplemobiletools.calendar.pro.Model.CookingStep;
import com.simplemobiletools.calendar.pro.Model.CookingTip;
import com.simplemobiletools.calendar.pro.apis.ApiFactory;
import com.simplemobiletools.calendar.pro.apis.Constants;
import com.simplemobiletools.calendar.pro.apis.api_services.CookingService;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CookingActivity extends Activity {

    private List<Cooking> mCookings;
    private List<CookingIngredient> mCookingIngredients;
    private List<CookingStep> mCookingSteps;
    private List<CookingTip> mCookingTips;

    private GridView gvCooking;
//    private BottomNavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cooking);

        gvCooking = findViewById(R.id.gv_cooking);
//        mNavigationView = findViewById(R.id.navigation_cooking);

//        mNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
//            switch (menuItem.getItemId()){
//                case R.id.navigation_cooking_list:
//                    showCookingList();
//                    return true;
//                case R.id.navigation_cooking_schedule:
//                    showCookingSchedule();
//                    return true;
//            }
//            return false;
//        });

        showCookingList();

        gvCooking.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(CookingActivity.this, CookingDetailActivity.class);
            int cooking_id = mCookings.get(position).getId();
            String foodName = mCookings.get(position).getFoodName();
            String detail = mCookings.get(position).getDetail();
            String picture = mCookings.get(position).getPicture();
            intent.putExtra("id", cooking_id);
            intent.putExtra("foodName", foodName);
            intent.putExtra("detail", detail);
            intent.putExtra("picture", picture);
            startActivity(intent);
        });
    }

    private void showCookingSchedule() {

    }

    private void showCookingList() {
        List<Cooking> data = new ArrayList<>();
        ApiFactory.createRetrofitService(CookingService.class, Constants.HOST_SERVER)
                .getAllCookingInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> {
                            JsonArray cookingList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : cookingList) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                int id = jsonObject.get("id").getAsInt();
                                String foodName = jsonObject.get("food_name").getAsString();
                                String detail = jsonObject.get("detail").getAsString();
                                String picture = jsonObject.get("picture").getAsString();
                                data.add(new Cooking(id, foodName, detail, picture));

                            }
                            mCookings = data;
                            GridViewCookingAdapter mCookingAdapter = new GridViewCookingAdapter(getApplicationContext(), mCookings);
                            gvCooking.setAdapter(mCookingAdapter);
                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );

    }
    }
