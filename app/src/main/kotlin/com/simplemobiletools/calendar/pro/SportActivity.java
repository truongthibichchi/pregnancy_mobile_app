package com.simplemobiletools.calendar.pro;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.simplemobiletools.calendar.pro.CustomAdapter.GridViewSportAdapter;
import com.simplemobiletools.calendar.pro.Model.Sport;
import com.simplemobiletools.calendar.pro.apis.ApiFactory;
import com.simplemobiletools.calendar.pro.apis.Constants;
import com.simplemobiletools.calendar.pro.apis.api_services.SportService;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SportActivity extends Activity {
    private EditText etSearch;
    private GridView gvSport;
    private List<Sport> mSports;
    private GridViewSportAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sport);

        etSearch = findViewById(R.id.et_sport);
        gvSport = findViewById(R.id.gv_sport);
        getGlossaryData();

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Toast.makeText(getApplicationContext(), etSearch.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        setupEventHandler();

    }

    private void getGlossaryData() {
        List<Sport> data = new ArrayList<>();
        ApiFactory.createRetrofitService(SportService.class, Constants.HOST_SERVER)
                .getAllSportInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res->{
                            JsonArray summaryInfoList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : summaryInfoList) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                int id = jsonObject.get("id").getAsInt();
                                String sportName = jsonObject.get("sport_name").getAsString();
                                String detail = jsonObject.get("detail").getAsString();
                                String picture = jsonObject.get("picture").getAsString();
                                data.add(new Sport(id, sportName, detail,picture));

                            }
                            mSports =data;
                            mAdapter = new GridViewSportAdapter(getApplicationContext(), mSports);
                            gvSport.setAdapter(mAdapter);
                        },
                        err-> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }

    private void setupEventHandler() {
        gvSport.setOnItemClickListener((parent, view, position, idItem) -> {
//            Intent intent = new Intent(GlossaryActivity.this, WeekDetailsActivity.class);
//            int idWord = mSports.get(position).getId();
//            intent.putExtra("id", idWord);
//            startActivity(intent);
            AlertDialog.Builder builder = new AlertDialog.Builder(SportActivity.this);
            builder.setMessage(mSports.get(position).getDetail());
            builder.setCancelable(true);

            builder.setNegativeButton(
                    "Cancel",
                    (dialog, id)->
                            dialog.cancel()
            );


            AlertDialog alert = builder.create();
            alert.show();
        });
    }
}
