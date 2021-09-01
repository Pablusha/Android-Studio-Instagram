package com.example.instagram.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.widget.TextView;

import com.example.instagram.R;

public class SplashActivity extends AppCompatActivity {

    private TextView txtFacebook;
    private int GECIS_SURESI = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txtFacebook = findViewById(R.id.ac_splash_tvFacebook);
        changeColorOfFacebookText();
        toTheLoginScreen();

    }

    private void changeColorOfFacebookText() {
        txtFacebook.setText(Html.fromHtml("<font color=\"#fa8706\">" + "F" +
                "</font>" + "<font color=\"#f26906\">" + "A" + "</font>" +
                "<font color=\"#e64302\">" + "C" + "</font>" +
                "<font color=\"#eb1504\">" + "E" + "</font>" +
                "<font color=\"#da0111\">" + "B" + "</font>" +
                "<font color=\"#cf012d\">" + "O" + "</font>" +
                "<font color=\"#c80251\">" + "O" + "</font>" +
                "<font color=\"#b40375\">" + "K" + "</font>"));
    }

    private void toTheLoginScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        },GECIS_SURESI);
    }

}