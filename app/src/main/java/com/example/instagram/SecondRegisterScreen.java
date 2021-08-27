package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondRegisterScreen extends AppCompatActivity {

    private EditText etUsername,etFullName,etEmail,etPassword,etConfirmPassword;
    private Button btnKayit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_register_screen);
        initViews();
        changeButtonBackground();

        register();

    }

    private void register() {
        btnKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String fullName = etFullName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confPassword = etConfirmPassword.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(fullName) || TextUtils.isEmpty(email)
                        || TextUtils.isEmpty(password) || TextUtils.isEmpty(confPassword)) {
                    Toast.makeText(SecondRegisterScreen.this,"Alanlar boş bırakılamaz.",
                            Toast.LENGTH_SHORT).show();
                }

                if (!TextUtils.equals(password,confPassword)) {
                    Toast.makeText(SecondRegisterScreen.this,"Şifreleriniz uyuşmuyor.",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void changeButtonBackground() {
        etUsername.addTextChangedListener(editTextWatcher);
        etFullName.addTextChangedListener(editTextWatcher);
        etEmail.addTextChangedListener(editTextWatcher);
        etPassword.addTextChangedListener(editTextWatcher);
        etConfirmPassword.addTextChangedListener(editTextWatcher);
    }

    private TextWatcher editTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String username = etUsername.getText().toString().trim();
            String fullName = etFullName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confPassword = etConfirmPassword.getText().toString().trim();

            if (!username.isEmpty() && !fullName.isEmpty() && !email.isEmpty() && !password.isEmpty() &&
            !confPassword.isEmpty()) {
                btnKayit.setBackgroundResource(R.drawable.button_facebook_background);
                btnKayit.setHintTextColor(getResources().getColor(R.color.white));
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void initViews() {
        etUsername = findViewById(R.id.ac_secondRegister_etUsername);
        etFullName = findViewById(R.id.ac_secondRegister_etFullName);
        etEmail = findViewById(R.id.ac_secondRegister_etEmail);
        etPassword = findViewById(R.id.ac_secondRegister_etPassword);
        etConfirmPassword = findViewById(R.id.ac_secondRegister_etConfirmPassword);
        btnKayit = findViewById(R.id.ac_secondRegister_btnRegister);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(SecondRegisterScreen.this,RegisterScreen.class));
        finish();
    }
}