package com.example.re_draft_d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        try {
            getSupportActionBar().hide();
        }
        catch (Exception e) {}


        //splash activity code
        Thread thread = new Thread(){
            public void run(){

                try{ sleep(1000);}

                catch(Exception e){ }

                finally {
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();   // is se main activity se back karne pe splash screen pe nahi lautenge dobara
                }
            }
        };
        thread.start();


    }
}