package com.example.e_shop.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_shop.R;
import com.example.e_shop.databinding.ActivitySplashScreenBinding;

public class SignUp extends AppCompatActivity {

    TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        skip = findViewById(R.id.btn_skip);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this,MainActivity.class));
            }
        });
    }
}
