package com.example.instagram;

import android.content.Intent;
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
public class PhoneFragment extends Fragment {

    public PhoneFragment() {
        //Required empty public constructor
    }

    private TextView txtGirisYap;
    private EditText etPhone;
    private Button btnIleri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_phone, container, false);

        //Initialize Views
        txtGirisYap = view.findViewById(R.id.ac_secondRegisterPhone_txtGirisYap);
        etPhone = view.findViewById(R.id.ac_secondRegister_etPhone);
        btnIleri = view.findViewById(R.id.ac_secondRegister_btnIleriPhone);
        goToLogin();
        register();

        return view;
    }

    private void register() {
        String phoneNumber = etPhone.getText().toString();
        etEmptyCheck();

        btnIleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(phoneNumber)) {
                    Toast.makeText(getActivity(),"Telefon numarası boş bırakılamaz.",Toast.LENGTH_LONG).show();
                    return;
                }



                startActivity(new Intent(getActivity(),ThirdPhoneRegisterScreen.class));

            }
        });

    }

    private void etEmptyCheck() {
        etPhone.addTextChangedListener(new TextWatcher() {
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