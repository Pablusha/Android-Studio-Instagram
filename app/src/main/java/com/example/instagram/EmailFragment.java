package com.example.instagram;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmailFragment extends Fragment {

    public EmailFragment() {
        // Required empty public constructor
    }

    private EditText etEmail;
    private Button btnIleri;
    private TextView txtGirisYap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Initialize View
        View view = inflater.inflate(R.layout.fragment_email, container, false);

        //Initialize Views
        etEmail = view.findViewById(R.id.ac_secondRegister_etEmail);
        btnIleri = view.findViewById(R.id.ac_secondRegister_btnIleriEmail);
        txtGirisYap = view.findViewById(R.id.ac_secondRegisterEmail_txtGirisYap);
        goToLogin();
        register();




        return view;
    }

    public void register() {
        String email = etEmail.getText().toString();
        etEmptyCheck();

        btnIleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getActivity(),"Email kısmı boş bırakılamaz.",Toast.LENGTH_LONG).show();
                    return;
                }
                
                startActivity(new Intent(getActivity(),ThirdRegisterScreen.class));
            }
        });

    }

    private void etEmptyCheck() {
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().length() > 0) {
                    btnIleri.setBackgroundResource(R.drawable.button_facebook_background);
                    btnIleri.setTextColor(getResources().getColor(R.color.white));
                } else {
                    btnIleri.setBackgroundResource(R.drawable.button_background);
                    btnIleri.setTextColor(getResources().getColor(R.color.btnGirisHint));
                }

            }
        });
    }

    private void goToLogin() {
        txtGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),LoginActivity.class));

            }
        });
    }
}