package a3tn.pregnancy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import a3tn.pregnancy.apis.ApiFactory;
import a3tn.pregnancy.apis.api_services.PregnancySummaryInfoService;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private TextView txtResponse;
    private EditText txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupEventHandlers();
    }

    private void setupEventHandlers() {
        txtResponse = findViewById(R.id.txt_response);
        txtTitle = findViewById(R.id.txt_title);

        findViewById(R.id.btn_get_all).setOnClickListener(
                e -> getAllSummaryInfoAndShowOnView()
        );
        findViewById(R.id.btn_get_by_id).setOnClickListener(e -> {
            String title = txtTitle.getText().toString();
            getSummaryInfoByTitleAndShowOnView(title);
        });
    }

    private void getAllSummaryInfoAndShowOnView() {
        ApiFactory.createRetrofitService(
                PregnancySummaryInfoService.class,
                "http://192.168.1.103:5000/api/v1/"
        )
                .getAllSummaryInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> {
                            JsonArray summaryInfoList = res.get("dt").getAsJsonArray();
                            for (JsonElement element : summaryInfoList) {
                                JsonObject jsonObject = element.getAsJsonObject();

                                System.out.println(jsonObject.get("weeks").getAsInt());
                                System.out.println(jsonObject.get("baby_info").getAsString());
                                System.out.println(jsonObject.get("mom_info").getAsString());
                            }
                            txtResponse.setText(summaryInfoList.toString());
                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }

    private void getSummaryInfoByTitleAndShowOnView(String week) {
        ApiFactory.createRetrofitService(
                PregnancySummaryInfoService.class,"http://192.168.1.103:5000/api/v1/"
        )
                .getSummaryInfoByWeek(week)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res ->{
                            JsonArray summaryInfoList = res.get("dt").getAsJsonArray();
                            for(JsonElement element : summaryInfoList){
                                JsonObject jsonObject = element.getAsJsonObject();

                                System.out.println(jsonObject.get("weeks").getAsInt());
                                System.out.println(jsonObject.get("baby_info").getAsString());
                                System.out.println(jsonObject.get("mom_info").getAsString());
                            }
                            txtResponse.setText(summaryInfoList.toString());
                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()

                );
    }
}
