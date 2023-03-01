package com.example.chigratitude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splashActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        getSupportActionBar().hide();

        Thread thread = new Thread() {

            public void run() {
                try {
                    sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
               //     Intent intent = new Intent(splashActivity2.this, MainActivity2.class);
                 Intent intent = new Intent(splashActivity2.this, SignUpActivity.class);
                    startActivity(intent);
                    finish();
                }
            }


        };
        thread.start();
    }
}