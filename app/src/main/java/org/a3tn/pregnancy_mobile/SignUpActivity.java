package org.a3tn.pregnancy_mobile;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.a3tn.pregnancy_mobile.apis.ApiFactory;
import org.a3tn.pregnancy_mobile.apis.Constants;
import org.a3tn.pregnancy_mobile.apis.api_services.SignUpService;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SignUpActivity extends Activity {
    private Button btnSignUp;
    private EditText etEmail, etPassword, etFullName, etConceivedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign_up);

        FindViewById();

        btnSignUp.setOnClickListener(view-> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String fullname = etFullName.getText().toString();
            String conceivedDate = etConceivedDate.getText().toString();
            onSignUp(email, password, fullname, conceivedDate);
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);

        });
    }

    private void onSignUp(String email, String password, String fullname, String conceivedDate)
    {
        ApiFactory.createRetrofitService(
                SignUpService.class,
                Constants.HOST_SERVER
        )
                .getUserSignUp(email, password, fullname, conceivedDate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> {
                            int err = res.get("err").getAsInt();
                            if (err == 0) {
                                JsonObject jsonObject = res.get("dt").getAsJsonObject();
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            Toast.makeText(this, res.get("msg").getAsString(), Toast.LENGTH_SHORT).show();

                        },
                        err -> Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }

    private void FindViewById() {
        etEmail = findViewById(R.id.et_sign_up_email);
        etPassword = findViewById(R.id.et_sign_up_password);
        etFullName = findViewById(R.id.et_sign_up_fullname);
        etConceivedDate = findViewById(R.id.et_sign_up_Date);
        btnSignUp=findViewById(R.id.btn_sign_up);
    }
}
