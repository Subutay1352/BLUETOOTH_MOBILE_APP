package com.clj.blesample;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.clj.fastble.BleManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainScreenActivity extends AppCompatActivity {

    private BottomNavigationView mBottomBar;
    public FlowFragment flowFragment = new FlowFragment();
    public WriteDataFragment writeDataFragment = new WriteDataFragment();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        mBottomBar = findViewById(R.id.main_screen_activity_bottomNavigationView);

        flowFragment = new FlowFragment();
        writeDataFragment = new WriteDataFragment();


        createFragment(flowFragment);
        createFragment(writeDataFragment);

        showFragment(flowFragment);

        mBottomBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){


                    case R.id.bottombar_menu_flow:

                        hideFragment(writeDataFragment);
                        showFragment(flowFragment);
                        break;

                    case R.id.bottombar_menu_write:

                        hideFragment(flowFragment);
                        showFragment(writeDataFragment);
                        break;

                }
                return true;
            }
        });

    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_screen_activity_frameLayout,fragment);
        transaction.commit();
    }

    private void createFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_screen_activity_frameLayout, fragment)
                .hide(fragment)
                .commit();
    }
    private void showFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .show(fragment)
                .commit();
    }
    private void hideFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .hide(fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            /*AlertDialog alertDialog = new AlertDialog.Builder(
                    this)
                    .setTitle("Check")  // Setting Dialog Title
                    .setMessage("Do you want to disconnect?")// Setting Dialog Message
                    .setCancelable(false)
                    .create();

            // Setting OK Button
            alertDialog.setButton2("Yes", (dialog, which) -> {



            });
            alertDialog.setButton("No", (dialog, which) -> {
                ;
            });
            alertDialog.show();*/

        BleManager.getInstance().disconnectAllDevice();
        finish();
        Intent intent = new Intent(MainScreenActivity.this, MainActivity.class);
        startActivity(intent);


        }
        else {
            getSupportFragmentManager().popBackStack();
        }

    }

}

