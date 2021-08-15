package com.example.re_draft_d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {


    Button registerbtn;
    EditText txtEmail,txtpass,txtname,txtphone;
    TextView txtskip,txtregisteractivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        try {
            getSupportActionBar().hide();
        }
        catch (Exception e) {}

    registerbtn=findViewById(R.id.btn_register);
    txtEmail=findViewById(R.id.txt_email);
    txtpass=findViewById(R.id.txt_pass);
    txtname=findViewById(R.id.txt_name);
    txtphone=findViewById(R.id.txt_phoneno);
    txtskip=findViewById(R.id.txt_skip);
    txtregisteractivity=findViewById(R.id.txt_loginactivity);


    txtregisteractivity.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Register.this,Login.class));
            finish();
        }
    });



    }
}