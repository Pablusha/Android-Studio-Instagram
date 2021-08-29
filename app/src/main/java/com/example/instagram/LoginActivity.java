package com.example.instagram;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private TextView txtKaydol,txtGirisYardim;
    private EditText etSifre,etEmail;
    private Button btnFacebookGiris,btnGiris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        changeButtonBackground();

        goToRegister();
        
    }

    private void initViews() {
        txtKaydol = findViewById(R.id.ac_login_tvKaydol);
        txtGirisYardim = findViewById(R.id.ac_login_tvGirisYardim);
        etSifre = findViewById(R.id.ac_login_etSifre);
        etEmail = findViewById(R.id.ac_login_etEmail);
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

    private void changeButtonBackground() {
        etEmail.addTextChangedListener(editTextWatcher);
        etSifre.addTextChangedListener(editTextWatcher);
    }

    private TextWatcher editTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String email = etEmail.getText().toString().trim();
            String password = etSifre.getText().toString().trim();

            if (!email.isEmpty() && !password.isEmpty()) {
                btnGiris.setBackgroundResource(R.drawable.button_facebook_background);
                btnGiris.setHintTextColor(getResources().getColor(R.color.white));
            } else {
                btnGiris.setBackgroundResource(R.drawable.button_background);
                btnGiris.setHintTextColor(getResources().getColor(R.color.btnGirisHint));
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Uygulamadan çıkış yapmak istediğinize emin misiniz?");
        builder.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        builder.setNegativeButton("HAYIR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }
}