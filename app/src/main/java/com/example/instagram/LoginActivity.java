package com.example.instagram;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView txtKaydol,txtGirisYardim;
    private EditText etSifre;
    private Button btnFacebookGiris,btnGiris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

        goToRegister();
        
    }

    private void initViews() {
        txtKaydol = findViewById(R.id.ac_login_tvKaydol);
        txtGirisYardim = findViewById(R.id.ac_login_tvGirisYardim);
        etSifre = findViewById(R.id.ac_login_etSifre);
        btnFacebookGiris = findViewById(R.id.ac_login_btnGirisWithFacebook);
        btnGiris = findViewById(R.id.ac_login_btnGirisYap);
    }

    private void goToRegister() {
        txtKaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterScreen.class));
                finish();
            }
        });
    }

}