package com.simplemobiletools.calendar.pro;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.simplemobiletools.calendar.pro.CustomAdapter.GridViewCookingIngredientAdapter;
import com.simplemobiletools.calendar.pro.CustomAdapter.GridViewCookingStepAdapter;
import com.simplemobiletools.calendar.pro.CustomAdapter.GridViewCookingTipAdapter;
import com.simplemobiletools.calendar.pro.Model.CookingIngredient;
import com.simplemobiletools.calendar.pro.Model.CookingStep;
import com.simplemobiletools.calendar.pro.Model.CookingTip;
import com.simplemobiletools.calendar.pro.apis.ApiFactory;
import com.simplemobiletools.calendar.pro.apis.Constants;
import com.simplemobiletools.calendar.pro.apis.api_services.CookingService;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CookingDetailActivity extends AppCompatActivity {
    private ImageView imgPicture;
    private TextView tvFoodName;
    private TextView tvDetail;
    private GridView gvIngredient;
    private GridView gvStep;
    private GridView gvTip;
    private List<CookingIngredient> mIngedients;
    private List<CookingStep> mSteps;
    private List<CookingTip> mTips;
    private Bundle mBundle;
    private int id;
    private String foodName;
    private String detail;
    private String picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_detail);
        mBundle = getIntent().getExtras();
        id = (int) mBundle.get("id");
        foodName = (String) mBundle.get("foodName");
        detail = (String) mBundle.get("detail");
        picture = (String) mBundle.get("picture");

        findViewByIds();
        showCookingInfo();
        getCookingIngredientData();
        getCookingStepData();
        getCookingTipData();


    }

    private void showCookingInfo() {
        tvFoodName.setText(foodName);
        tvDetail.setText(detail);
        String pictureUrl = Constants.STATIC_URL + "foods/" + picture;
        Glide
                .with(getApplicationContext())
                .load(pictureUrl)
                .into(imgPicture);
    }

    private void findViewByIds() {
        imgPicture = findViewById(R.id.img_cooking_detail_picture);
        tvFoodName = findViewById(R.id.tv_cooking_food_name);
        tvDetail = findViewById(R.id.tv_cooking_detail);
        gvIngredient = findViewById(R.id.gv_cooking_ingredient);
        gvStep = findViewById(R.id.gv_cooking_step);
        gvTip = findViewById(R.id.gv_cooking_tip);
    }

    private void getCookingIngredientData() {
        List<CookingIngredient> data = new ArrayList<>();
        ApiFactory.createRetrofitService(CookingService.class, Constants.HOST_SERVER)
                .getCookingIngredientByCookingId(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> {
                            JsonArray ingreditentList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : ingreditentList) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                int id = jsonObject.get("id").getAsInt();
                                String ingredient = jsonObject.get("ingredient").getAsString();
                                String mesurement = jsonObject.get("mesurement").getAsString();
                                data.add(new CookingIngredient(id, ingredient, mesurement));
                            }
                            mIngedients = data;
                            GridViewCookingIngredientAdapter mAdapter = new GridViewCookingIngredientAdapter(this, mIngedients);
                            gvIngredient.setAdapter(mAdapter);
                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }

    private void getCookingStepData() {
        List<CookingStep> data = new ArrayList<>();

        ApiFactory.createRetrofitService(CookingService.class, Constants.HOST_SERVER)
                .getCookingStepByCookingId(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> {
                            JsonArray tips = res.get("dt").getAsJsonArray();
                            for (JsonElement element : tips) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                int id = jsonObject.get("id").getAsInt();
                                int cooking_id = jsonObject.get("cooking_id").getAsInt();
                                String cookingDetail = jsonObject.get("detail").getAsString();
                                data.add(new CookingStep(id, cooking_id, cookingDetail));
                            }
                            mSteps = data;
                            GridViewCookingStepAdapter mAdapter = new GridViewCookingStepAdapter(this, mSteps);
                            gvStep.setAdapter(mAdapter);
                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );

    }


    private void getCookingTipData() {
        List<CookingTip> data = new ArrayList<>();

        ApiFactory.createRetrofitService(CookingService.class, Constants.HOST_SERVER)
                .getCookingTipByCookingId(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> {
                            JsonArray ingreditentList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : ingreditentList) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                int id = jsonObject.get("id").getAsInt();
                                int cooking_id = jsonObject.get("cooking_id").getAsInt();
                                String cookingTip = jsonObject.get("tip").getAsString();
                                data.add(new CookingTip(id, cooking_id, cookingTip));
                            }
                            mTips = data;
                            GridViewCookingTipAdapter mAdapter = new GridViewCookingTipAdapter(this, mTips);
                            gvTip.setAdapter(mAdapter);
                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }


}
