package org.a3tn.pregnancy_mobile;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.a3tn.pregnancy_mobile.CustomAdapter.ListViewGlossaryAdapter;
import org.a3tn.pregnancy_mobile.CustomAdapter.ListViewSportAdapter;
import org.a3tn.pregnancy_mobile.Model.Glossary;
import org.a3tn.pregnancy_mobile.Model.Sport;
import org.a3tn.pregnancy_mobile.apis.ApiFactory;
import org.a3tn.pregnancy_mobile.apis.Constants;
import org.a3tn.pregnancy_mobile.apis.api_services.SportService;
import org.a3tn.pregnancy_mobile.apis.api_services.SummaryInfoService;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SportActivity extends Activity {
    private EditText etSearch;
    private ListView lvSport;
    private List<Sport> mSports;
    private ListViewSportAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sport);

        etSearch = findViewById(R.id.et_sport);
        lvSport = findViewById(R.id.lv_sport);
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
                            mAdapter = new ListViewSportAdapter(getApplicationContext(), mSports);
                            lvSport.setAdapter(mAdapter);
                        },
                        err-> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }

    private void setupEventHandler() {
        lvSport.setOnItemClickListener((parent, view, position, idItem) -> {
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
