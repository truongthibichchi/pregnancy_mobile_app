package com.simplemobiletools.calendar.pro;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.simplemobiletools.calendar.pro.CustomAdapter.GridViewGlossaryAdapter;
import com.simplemobiletools.calendar.pro.CustomAdapter.GridViewSportAdapter;
import com.simplemobiletools.calendar.pro.Model.Glossary;
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
        getSportData();

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                findWord();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void findWord() {
        String keyWord = etSearch.getText().toString();
        if (keyWord.isEmpty()) {
            mAdapter = new GridViewSportAdapter(getApplicationContext(), mSports);
            gvSport.setAdapter(mAdapter);
            setupEventHandler();
            return;
        }
        List<Sport> list = new ArrayList<>();
        for (Sport sport : mSports) {
            if (sport.getSportName().toLowerCase().contains(keyWord.toLowerCase()))
                list.add(sport);

        }
        mAdapter = new GridViewSportAdapter(getApplicationContext(), list);
        gvSport.setAdapter(mAdapter);
        setupEventHandler();
    }

    private void getSportData() {
        List<Sport> data = new ArrayList<>();
        ApiFactory.createRetrofitService(SportService.class, Constants.HOST_SERVER)
                .getAllSportInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> {
                            JsonArray summaryInfoList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : summaryInfoList) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                int id = jsonObject.get("id").getAsInt();
                                String sportName = jsonObject.get("sport_name").getAsString();
                                String benefit = jsonObject.get("benefit").getAsString();
                                String step = jsonObject.get("step").getAsString();
                                String note = jsonObject.get("note").getAsString();
                                String picture = jsonObject.get("picture").getAsString();
                                data.add(new Sport(id, sportName, benefit, step, note, picture));

                            }
                            mSports = data;
                            mAdapter = new GridViewSportAdapter(getApplicationContext(), mSports);
                            gvSport.setAdapter(mAdapter);
                            setupEventHandler();
                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }


    private void setupEventHandler() {
        gvSport.setOnItemClickListener((parent, view, position, idItem) -> {
//            Intent intent = new Intent(GlossaryActivity.this, WeekDetailsActivity.class);
//            int idWord = mSports.get(position).getId();
//            intent.putExtra("id", idWord);
//            startActivity(intent);
//            AlertDialog.Builder builder = new AlertDialog.Builder(SportActivity.this);
//            builder.setView(R.layout.alert_sport_detail);
//            TextView tvSportname = (TextView) AlertDialog.
//
//            builder.setCancelable(true);
//            builder.setNegativeButton(
//                    "Cancel",
//                    (dialog, id)->
//                            dialog.cancel()
//            );
//
//
//            AlertDialog alert = builder.create();
//            alert.show();
            final Dialog dialog = new Dialog(SportActivity.this);
            dialog.setContentView(R.layout.alert_sport_detail);

            // set the custom dialog components - text, image and button
            TextView text = dialog.findViewById(R.id.tv_sport_detail_sport_name);
            text.setText(mSports.get(position).getSportName());
            ImageView imgPicture = dialog.findViewById(R.id.img_sport_detail_picture);
            ImageView imgBenefit = dialog.findViewById(R.id.img_sport_detail_benefit);
            ImageView imgStep = dialog.findViewById(R.id.img_sport_detail_step);
            ImageView imgNote = dialog.findViewById(R.id.img_sport_detail_note);
            Glide
                    .with(getApplicationContext())
                    .load(mSports.get(position).getPicture())
                    .into(imgPicture);

            Glide
                    .with(getApplicationContext())
                    .load(mSports.get(position).getBenefit())
                    .into(imgBenefit);
            Glide
                    .with(getApplicationContext())
                    .load(mSports.get(position).getStep())
                    .into(imgStep);
            Glide
                    .with(getApplicationContext())
                    .load(mSports.get(position).getNote())
                    .into(imgNote);

            dialog.show();
        });
    }
}
