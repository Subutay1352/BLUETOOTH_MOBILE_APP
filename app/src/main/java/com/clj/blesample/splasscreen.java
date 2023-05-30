package com.clj.blesample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.view.WindowManager;

public class splasscreen extends AppCompatActivity {

    private static int DELAY_TIME = 4000;
    Animation topAnim,bottomAnim;
    ImageView imageView;

    private SharedPreferences loginPreferences,languagePreferences;
    private SharedPreferences.Editor loginPrefsEditor,languageEditor;
    private Boolean saveLogin;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splasscreen);

        variables.setLanguagePreferences(getSharedPreferences("langPrefs", MODE_PRIVATE));
        variables.setLanguageEditor(variables.getLanguagePreferences().edit());
        variables.setSaveLanguage(variables.getLanguagePreferences().getBoolean("saveLanguage", true));
        if(variables.isSaveLanguage())
        {
            variables.setLanguage(variables.getLanguagePreferences().getString("language",""));
        }

        checkFirstRun();

    }

    public void splashScreen()
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        imageView = findViewById(R.id.asdasd);
        imageView.setAnimation(topAnim);

        new  Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(splasscreen.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        },DELAY_TIME);
    }

    public void splashScreenNormal()
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        imageView = findViewById(R.id.asdasd);
        imageView.setAnimation(topAnim);

        new  Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(splasscreen.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        },DELAY_TIME);
    }

    private void checkFirstRun() {

        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;

        // Get current version code
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {

            //splashScreen();
            splashScreenNormal(); // normalde bu aktif
            return;

        } else if (savedVersionCode == DOESNT_EXIST) {

            splashScreen();

        } else if (currentVersionCode > savedVersionCode) {

            splashScreen();
        }

        // Update the shared preferences with the current version code
        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
    }
}