package com.example.myactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LifeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onstart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onstop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onpause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onresume");
    }
}