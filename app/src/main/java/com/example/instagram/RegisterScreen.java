package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterScreen extends AppCompatActivity {

    private TextView txtGirisYap;
    private Button btnKaydol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        initViews();

    }

    private void initViews() {
        txtGirisYap = findViewById(R.id.ac_register_txtGirisYap);
        btnKaydol = findViewById(R.id.ac_register_btnEmail);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(RegisterScreen.this,LoginActivity.class));
        finish();
    }


    public void goToSecondRegister(View view) {
        btnKaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterScreen.this,SecondRegisterScreen.class));
                finish();
            }
        });
    }

    public void goToLogin(View view) {
        txtGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterScreen.this,LoginActivity.class));
                finish();
            }
        });
    }
}