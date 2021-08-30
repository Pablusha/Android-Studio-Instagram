package com.example.instagram;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Locale;

public class SecondRegisterScreen extends AppCompatActivity {

    EditText etUsername,etFullName,etEmail,etPassword,etConfirmPassword;
    Button btnKayit;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_register_screen);
        initViews();
        changeButtonBackground();

        firebaseAuth = FirebaseAuth.getInstance();

        btnKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    public void register() {
        String username = etUsername.getText().toString();
        String fullName = etFullName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confPassword = etConfirmPassword.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(fullName) || TextUtils.isEmpty(email)
                || TextUtils.isEmpty(password) || TextUtils.isEmpty(confPassword)) {
            Toast.makeText(SecondRegisterScreen.this,"Alanlar boş bırakılamaz.",
                    Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(SecondRegisterScreen.this,"Şifrenizin uzunluğu 6 karakterden " +
                    "fazla olmalıdır.",Toast.LENGTH_LONG).show();
        } else if (!TextUtils.equals(password,confPassword)) {
            Toast.makeText(SecondRegisterScreen.this,"Şifreleriniz uyuşmuyor.",Toast.LENGTH_SHORT).show();
        } else {
            createUser(username,fullName,email,password);
        }
    }

    public void createUser(String username,String fullName,String email,String password) {
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            String userId = firebaseUser.getUid();

                            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users")
                                    .child(userId);

                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("id",userId);
                            hashMap.put("username",username.toLowerCase(Locale.ROOT));
                            hashMap.put("fullName",fullName);
                            hashMap.put("bio","");

                            databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(SecondRegisterScreen.this,LoginActivity.class));
                                        finish();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(SecondRegisterScreen.this,task.getException().toString(),
                                    Toast.LENGTH_LONG).show();
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
            } else {
                btnKayit.setBackgroundResource(R.drawable.button_background);
                btnKayit.setHintTextColor(getResources().getColor(R.color.btnGirisHint));
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public void initViews() {
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