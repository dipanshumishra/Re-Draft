package com.example.re_draft_d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.re_draft_d.databinding.ActivityResultBinding;
import com.example.re_draft_d.databinding.ActivitySettingBinding;

public class Setting extends AppCompatActivity {

    LinearLayout home,profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        try {
            getSupportActionBar().hide();
        }
        catch (Exception e) {}

        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    profile=findViewById(R.id.profile);
    profile.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Setting.this,Register.class));
            finish();
        }
    });


    }
}