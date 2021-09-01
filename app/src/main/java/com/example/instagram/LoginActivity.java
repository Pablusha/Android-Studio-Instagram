package com.example.instagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private TextView txtKaydol,txtGirisYardim;
    private EditText etPassword,etEmail;
    private Button btnFacebookGiris,btnGiris;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        changeButtonBackground();

        firebaseAuth = FirebaseAuth.getInstance();
        login();
    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //Redirect if user is not null
        if (firebaseUser != null) {
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            finish();
        }
    }

    private void login() {
        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this,"Boş alan bırakılamaz.",Toast.LENGTH_LONG).show();
                } else {
                    signIn(email,password);
                }
            }
        });
    }

    private void signIn(String email,String password) {
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                                    .child("Users").child(firebaseAuth.getCurrentUser().getUid());

                            reference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                                    finish();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        } else {
                            Toast.makeText(LoginActivity.this,"E-posta veya şifre yanlış.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void resetPassword(View view) {
        txtGirisYardim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etEmail = new EditText(view.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Şifremi Unuttum");
                passwordResetDialog.setMessage("Hesabınıza ait olan e-posta adresini giriniz.");
                passwordResetDialog.setView(etEmail);

                passwordResetDialog.setPositiveButton("Onayla", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String email = etEmail.getText().toString();

                        firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(LoginActivity.this,"E-posta adresinize sıfırlama " +
                                        "bağlantısı gönderildi",Toast.LENGTH_LONG).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                passwordResetDialog.create().show();
            }
        });
    }

    private void initViews() {
        txtKaydol = findViewById(R.id.ac_login_tvKaydol);
        txtGirisYardim = findViewById(R.id.ac_login_tvGirisYardim);
        etPassword = findViewById(R.id.ac_login_etSifre);
        etEmail = findViewById(R.id.ac_login_etEmail);
        btnFacebookGiris = findViewById(R.id.ac_login_btnGirisWithFacebook);
        btnGiris = findViewById(R.id.ac_login_btnGirisYap);
    }

    public void goToRegister(View view) {
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
        etPassword.addTextChangedListener(editTextWatcher);
    }

    private TextWatcher editTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

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