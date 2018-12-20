package org.a3tn.pregnancy_mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.a3tn.pregnancy_mobile.CustomAdapter.GridViewCookingIngredientAdapter;
import org.a3tn.pregnancy_mobile.Model.CookingIngredient;
import org.a3tn.pregnancy_mobile.Model.Glossary;
import org.a3tn.pregnancy_mobile.apis.ApiFactory;
import org.a3tn.pregnancy_mobile.apis.Constants;
import org.a3tn.pregnancy_mobile.apis.api_services.CookingService;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CookingDetailActivity extends AppCompatActivity {

    private GridView gvIngredient;
    private List<CookingIngredient> mIngedients;
    private Bundle mBundle;
    private int cookingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_detail);
        mBundle = getIntent().getExtras();
        cookingId = (int) mBundle.get("cookingId");

        gvIngredient = findViewById(R.id.gv_cooking_gredient);
        getCookingIngredientData();

    }

    private void getCookingIngredientData() {
        List<CookingIngredient> data = new ArrayList<>();

        ApiFactory.createRetrofitService(CookingService.class, Constants.HOST_SERVER)
                .getCookingIngredientByCookingId(cookingId)
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
                            GridViewCookingIngredientAdapter mAdapter = new GridViewCookingIngredientAdapter(this,mIngedients);
                            gvIngredient.setAdapter(mAdapter);
                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
}


    }

