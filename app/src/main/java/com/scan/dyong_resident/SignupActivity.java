package com.scan.dyong_resident;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    TextView tvSignup, tvDN;
    EditText edtFullName, edtPhone, edtMail, edtPass, edtRePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.signup_screen);
        init();

        edtFullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    edtFullName.setError("Vui lòng nhập họ tên");
                }
                else {
                    edtFullName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    edtMail.setError("Vui lòng nhập email");
                }
                else {
                    edtMail.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    edtPass.setError("Vui lòng nhập mật khẩu");
                }
                else {
                    edtPass.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtRePass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    edtRePass.setError("Vui lòng nhập lại mật khâu");
                }
                else {
                    edtRePass.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tvSignup.setOnClickListener(v -> {
            String fullName = edtFullName.getText().toString();
            String phone = edtPhone.getText().toString();
            String mail = edtMail.getText().toString();
            String pass = edtPass.getText().toString();
            String rePass = edtRePass.getText().toString();

            //validate
            if(fullName.equals("") || phone.equals("") || mail.equals("") || pass.equals("") || rePass.equals("")){
                if (fullName.equals("")){
                    edtFullName.setError("Vui lòng nhập họ tên");
                }
                else {
                    edtFullName.setError(null);
                }
                if (phone.equals("")){
                    edtPhone.setError("Vui lòng nhập số điện thoại");
                }
                else {
                    edtPhone.setError(null);
                }
                if (mail.equals("")){
                    edtMail.setError("Vui lòng nhập email");
                }
                else {
                    edtMail.setError(null);
                }
                if (pass.equals("")){
                    edtPass.setError("Vui lòng nhập mật khẩu");
                }
                else {
                    edtPass.setError(null);
                }
                if (rePass.equals("")){
                    edtRePass.setError("Vui lòng nhập lại mật khẩu");
                }
                else {
                    edtRePass.setError(null);
                }
            }
            else if (!pass.equals(rePass)) {
                Toast.makeText(this, "Nhập lại mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent();
                intent.putExtra("fullName", fullName);
                intent.putExtra("phone", phone);
                intent.putExtra("mail", mail);
                intent.putExtra("pass", pass);
                setResult(1,intent);
                finish();

            }

        });

        tvDN.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });

    }
    public void init(){
        tvSignup = findViewById(R.id.tvSignup);
        tvDN = findViewById(R.id.tvDN);
        edtFullName = findViewById(R.id.edtFullName);
        edtPhone = findViewById(R.id.edtPhone);
        edtMail = findViewById(R.id.edtMail);
        edtPass = findViewById(R.id.edtPass);
        edtRePass = findViewById(R.id.edtRePass);

    }
}