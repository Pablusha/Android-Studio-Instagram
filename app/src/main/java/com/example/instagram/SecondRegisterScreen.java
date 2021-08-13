package com.example.instagram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SecondRegisterScreen extends AppCompatActivity {

    TabLayout tlPhoneOrEmail;
    ViewPager viewPager;
    TabItem tiTelefon,tiEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_register_screen);
        initViews();

        adapterSettings();
    }

    public void initViews() {
        tlPhoneOrEmail = findViewById(R.id.ac_secondRegister_tlPhoneOrEmail);
        viewPager = findViewById(R.id.ac_secondRegister_viewPager);
        tiTelefon = findViewById(R.id.ac_secondRegister_tiTelefon);
        tiEmail = findViewById(R.id.ac_secondRegister_tiEmail);

    }

    public void adapterSettings() {
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), tlPhoneOrEmail.
                getTabCount());
        viewPager.setAdapter(pageAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tlPhoneOrEmail.selectTab(tlPhoneOrEmail.getTabAt(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(SecondRegisterScreen.this,RegisterScreen.class));
        finish();
    }


}