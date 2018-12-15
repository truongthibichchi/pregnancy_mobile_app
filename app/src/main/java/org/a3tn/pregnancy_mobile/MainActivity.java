package org.a3tn.pregnancy_mobile;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FindViewById();

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNavigation = findViewById(R.id.navigation_menu);
        mNavigation.setNavigationItemSelectedListener(this);


    }

    private void FindViewById() {
        mDrawerLayout = findViewById(R.id.drawer);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_summary_info:
                onCickMenuSummaryInfo();
                break;

            case R.id.menu_glossary:
                onClickMenuGlossary();
                break;

            case R.id.menu_food:
                onClickMenuFood();
                break;

            case R.id.menu_sport:
                onClickMenuSport();
                break;

            case R.id.menu_plan:
                onClickMenuPlan();
                break;

            case R.id.menu_mom_image:
                onClickMenuMomImage();
                break;
        }
        return true;
    }

    private void onClickMenuMomImage() {

    }

    private void onClickMenuPlan() {
    }

    private void onClickMenuSport() {
        Intent intent = new Intent(MainActivity.this, SportActivity.class);
        startActivity(intent);
    }

    private void onClickMenuGlossary() {
        Intent intent = new Intent(MainActivity.this, GlossaryActivity.class);
        startActivity(intent);
    }

    private void onClickMenuFood() {
        Intent intent = new Intent(MainActivity.this, CookingActivity.class);
        startActivity(intent);
    }

    private void onCickMenuSummaryInfo() {
        Intent intent = new Intent(MainActivity.this, SummaryInfoActivity.class);
        startActivity(intent);
    }

}