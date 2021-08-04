package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class RegisterScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(RegisterScreen.this,LoginActivity.class));
        finish();
    }

}