package com.pace.lumbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity{
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
        setContentView(R.layout.splash_activity);
        Animation fade = new AlphaAnimation(1,0);
        fade.setInterpolator(new AccelerateInterpolator());
        fade.setStartOffset(500);
        fade.setDuration(1800);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}

