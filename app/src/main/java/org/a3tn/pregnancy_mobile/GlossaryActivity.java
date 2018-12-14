package org.a3tn.pregnancy_mobile;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.a3tn.pregnancy_mobile.CustomAdapter.ListViewGlossaryAdapter;
import org.a3tn.pregnancy_mobile.Model.Glossary;
import org.a3tn.pregnancy_mobile.apis.ApiFactory;
import org.a3tn.pregnancy_mobile.apis.Constants;
import org.a3tn.pregnancy_mobile.apis.api_services.SummaryInfoService;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GlossaryActivity extends AppCompatActivity {
    private EditText etSearch;
    private ListView lvGlossary;
    private List<Glossary> mGlossaries;
    private ListViewGlossaryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glossary);

        etSearch = findViewById(R.id.et_glossary);
        lvGlossary = findViewById(R.id.lv_glossary);
        getGlossaryData();

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(getApplicationContext(), etSearch.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        setupEventHandler();

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
                            mGlossaries=data;
                            mAdapter = new ListViewGlossaryAdapter(getApplicationContext(), mGlossaries);
                            lvGlossary.setAdapter(mAdapter);
                        },
                        err-> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }

    private void setupEventHandler() {
        lvGlossary.setOnItemClickListener((parent, view, position, idItem) -> {
//            Intent intent = new Intent(GlossaryActivity.this, WeekDetailsActivity.class);
//            int idWord = mGlossaries.get(position).getId();
//            intent.putExtra("id", idWord);
//            startActivity(intent);
            AlertDialog.Builder builder = new AlertDialog.Builder(GlossaryActivity.this);
            builder.setMessage(mGlossaries.get(position).getMeaning());
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
