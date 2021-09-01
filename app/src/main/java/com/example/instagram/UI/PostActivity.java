package com.example.instagram.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.instagram.R;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PostActivity.this, HomeActivity.class));
        finish();
    }
}