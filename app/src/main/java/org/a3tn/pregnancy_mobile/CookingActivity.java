package org.a3tn.pregnancy_mobile;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.a3tn.pregnancy_mobile.CustomAdapter.GridViewCookingAdapter;
import org.a3tn.pregnancy_mobile.Model.CookingDetail;
import org.a3tn.pregnancy_mobile.Model.CookingIngredient;
import org.a3tn.pregnancy_mobile.Model.CookingStep;
import org.a3tn.pregnancy_mobile.Model.CookingTip;
import org.a3tn.pregnancy_mobile.apis.ApiFactory;
import org.a3tn.pregnancy_mobile.apis.Constants;
import org.a3tn.pregnancy_mobile.apis.api_services.CookingService;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CookingActivity extends Activity {

    private List<CookingDetail> mCookingDetails;
    private List<CookingIngredient> mCookingIngredients;
    private List<CookingStep> mCookingSteps;
    private List<CookingTip> mCookingTips;

    private GridView gvCooking;
    private BottomNavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cooking);

        gvCooking = findViewById(R.id.gv_cooking);
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

        gvCooking.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(CookingActivity.this, CookingDetailActivity.class);
            int cooking_id = mCookingDetails.get(position).getId();
            intent.putExtra("cookingId", cooking_id);
            startActivity(intent);
        });
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
                                String detail = jsonObject.get("detail").getAsString();
                                String picture = jsonObject.get("picture").getAsString();
                                data.add(new CookingDetail(id,foodName,detail,picture));

                            }
                            mCookingDetails=data;
                            GridViewCookingAdapter mCookingAdapter = new GridViewCookingAdapter(getApplicationContext(), mCookingDetails);
                            gvCooking.setAdapter(mCookingAdapter);
                        },
                        err-> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }
}
