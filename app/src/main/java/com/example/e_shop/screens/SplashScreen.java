package com.example.e_shop.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;

import com.example.e_shop.databinding.ActivityMainBinding;
import com.example.e_shop.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends Activity {
    ActivitySplashScreenBinding binding;
    private static int SPLASH_SCREEN = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,LoginActivity.class));
                finish();
            }
        },SPLASH_SCREEN);

    }
}
