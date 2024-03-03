package com.example.audiostoriesappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        int delay = 4000;

        new Handler().postDelayed(() -> {
             Intent intent = new Intent(WelcomePage.this, MenuPage.class);
             startActivity(intent);
             finish();
        }, delay);
    }

}