package org.a3tn.pregnancy_mobile;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class WeekDetails extends AppCompatActivity {
    private TextView mTextMessage;
    private int id;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_baby:
                    mTextMessage.setText(R.string.title_baby_info);
                    return true;
                case R.id.navigation_mom:
                    mTextMessage.setText(R.string.title_mom_info);
                    return true;
                case R.id.navigation_symptom:
                    mTextMessage.setText(R.string.title_symptom);
                    return true;
                case R.id.navigation_advice:
                    mTextMessage.setText(R.string.title_advice);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_details);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Bundle  bundle = getIntent().getExtras();
        if(bundle!=null){
            id= (int) bundle.get("weekId");
            if(id>0){
                getDetailData(id);
            }
            //Toast.makeText(getApplicationContext(), "item thu "+id,Toast.LENGTH_SHORT).show();
        }
    }

    private void getDetailData(int id) {

    }
}
