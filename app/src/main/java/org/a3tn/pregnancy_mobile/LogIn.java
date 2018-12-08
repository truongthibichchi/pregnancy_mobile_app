package org.a3tn.pregnancy_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.a3tn.pregnancy_mobile.apis.ApiFactory;
import org.a3tn.pregnancy_mobile.apis.Constants;
import org.a3tn.pregnancy_mobile.apis.api_services.LogInService;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LogIn extends AppCompatActivity {

    private Button btn_login_email;
    private TextView tv_register;
    private EditText et_email, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        FindViewById();
        setupEventHandlers();

    }

    private void setupEventHandlers() {
        btn_login_email.setOnClickListener(view -> {
            String email = et_email.getText().toString();
            String password = et_password.getText().toString();
            onLogIn(email, password);
        });

        tv_register.setOnClickListener(view ->
            onSignUp()
        );
    }

    private void onSignUp() {
        Intent intent = new Intent(LogIn.this, SignUp.class);
        startActivity(intent);
    }

    private void onLogIn(String email, String password) {
        ApiFactory.createRetrofitService(
                LogInService.class,
                Constants.HOST_SERVER
        )
                .getUserLogIn(email, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> {
                            int err = res.get("err").getAsInt();
                            if (err == 0) {
//                                JsonObject jsonObject = res.get("dt").getAsJsonObject();
                                Intent intent = new Intent(LogIn.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            Toast.makeText(this, res.get("msg").getAsString(), Toast.LENGTH_SHORT).show();

                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }

    private void FindViewById() {
        btn_login_email = findViewById(R.id.btn_sign_up);
        tv_register = findViewById(R.id.tv_register);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
    }
}