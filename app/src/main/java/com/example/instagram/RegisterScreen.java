package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterScreen extends AppCompatActivity {

    private TextView txtGirisYap;
    private Button btnFacebook,btnKaydol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        initViews();

        goToLogin();
        goToSecondRegisterScreen();

    }

    private void initViews() {
        txtGirisYap = findViewById(R.id.ac_register_txtGirisYap);
        btnFacebook = findViewById(R.id.ac_register_btnGirisWithFacebook);
        btnKaydol = findViewById(R.id.ac_register_btnEmailOrPhoneNumber);
    }

    private void goToLogin() {
        txtGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterScreen.this,LoginActivity.class));
                finish();
            }
        });
    }

    private void goToSecondRegisterScreen() {
        btnKaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterScreen.this,SecondRegisterScreen.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(RegisterScreen.this,LoginActivity.class));
        finish();
    }

}