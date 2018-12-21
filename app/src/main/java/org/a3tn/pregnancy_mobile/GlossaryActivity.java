package org.a3tn.pregnancy_mobile;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
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

import org.a3tn.pregnancy_mobile.CustomAdapter.GridViewGlossaryAdapter;
import org.a3tn.pregnancy_mobile.Model.Glossary;
import org.a3tn.pregnancy_mobile.apis.ApiFactory;
import org.a3tn.pregnancy_mobile.apis.Constants;
import org.a3tn.pregnancy_mobile.apis.api_services.SummaryInfoService;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GlossaryActivity extends Activity {
    private EditText etSearch;
    private GridView gvGlossary;
    private List<Glossary> mGlossaries;
    private GridViewGlossaryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_glossary);

        etSearch = findViewById(R.id.et_glossary);
        gvGlossary = findViewById(R.id.gv_glossary);
        getGlossaryData();

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
        if(keyWord.isEmpty()){
            mAdapter = new GridViewGlossaryAdapter(getApplicationContext(), mGlossaries);
            gvGlossary.setAdapter(mAdapter);
            setupEventHandler(mGlossaries);
            return;
        }
        List<Glossary> list = new ArrayList<>();
        for (Glossary glossary : mGlossaries) {
            if (keyWord.toLowerCase().contains(glossary.getWord().toLowerCase())) {
                list.add(glossary);
                Toast.makeText(this, glossary.getWord(), Toast.LENGTH_SHORT).show();
            }

        }
        mAdapter = new GridViewGlossaryAdapter(getApplicationContext(), list);
        gvGlossary.setAdapter(mAdapter);
        setupEventHandler(list);
    }


    private void getGlossaryData() {
        List<Glossary> data = new ArrayList<>();
        ApiFactory.createRetrofitService(SummaryInfoService.class, Constants.HOST_SERVER)
                .getAllGlossary()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> {
                            JsonArray summaryInfoList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : summaryInfoList) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                int id = jsonObject.get("id").getAsInt();
                                String word = jsonObject.get("word").getAsString();
                                String meaning = jsonObject.get("meaning").getAsString();
                                data.add(new Glossary(id, word, meaning));

                            }
                            mGlossaries = data;
                            mAdapter = new GridViewGlossaryAdapter(getApplicationContext(), mGlossaries);
                            gvGlossary.setAdapter(mAdapter);
                            setupEventHandler(mGlossaries);
                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }

    private void setupEventHandler(List<Glossary> list) {
        if(list.size()<=0){
            return;
        }
        gvGlossary.setOnItemClickListener((parent, view, position, idItem) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(GlossaryActivity.this);
            builder.setMessage(list.get(position).getMeaning());
            builder.setCancelable(true);
            builder.setNegativeButton(
                    "Cancel",
                    (dialog, id) ->
                            dialog.cancel()
            );


            AlertDialog alert = builder.create();
            alert.show();
        });
    }
}
