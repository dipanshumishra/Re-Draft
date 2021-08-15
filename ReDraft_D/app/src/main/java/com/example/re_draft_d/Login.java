package com.example.re_draft_d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.re_draft_d.R;

public class Login extends AppCompatActivity {

    Button loginbtn;
    EditText txtEmail,txtpass;
    TextView txtskip,txthelp,txtloginactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            getSupportActionBar().hide();
        }
        catch (Exception e) {}

    loginbtn=findViewById(R.id.btn_login);
    txtEmail=findViewById(R.id.txt_email);
    txtpass=findViewById(R.id.txt_pass);
    txthelp=findViewById(R.id.txt_help);
    txtskip=findViewById(R.id.txt_skip);
    txtloginactivity=findViewById(R.id.txt_registeractivity);

    txtloginactivity.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Login.this, com.example.re_draft_d.Register.class));
            finish();
        }
    });





    }
}