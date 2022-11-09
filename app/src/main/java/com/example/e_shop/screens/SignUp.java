package com.example.e_shop.screens;

import android.app.Activity;
import android.os.Bundle;

import com.example.e_shop.databinding.ActivitySplashScreenBinding;

public class SignUp extends Activity {

    ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}
