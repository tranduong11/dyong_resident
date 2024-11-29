package com.scan.dyong_resident;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignupActivity extends AppCompatActivity {
    TextView tvSignup, tvDN;
    EditText edtFullName, edtMail;
    private TextInputEditText passwordEditText, passwordEditText1;
    private TextInputLayout passwordTextInputLayout, passwordTextInputLayout1;
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

        tvSignup.setOnClickListener(v -> {
            String fullName = edtFullName.getText().toString();
            String mail = edtMail.getText().toString();
            String pass = passwordEditText.getText().toString();
            String pass1 = passwordEditText1.getText().toString();

            //validate
            if(fullName.equals("")  || mail.equals("") ){
                if (fullName.equals("")){
                    edtFullName.setError("Vui lòng nhập họ tên");
                }
                else {
                    edtFullName.setError(null);
                }

                if (mail.equals("")){
                    edtMail.setError("Vui lòng nhập email");
                }
                else {
                    edtMail.setError(null);
                    if (!isValidEmail(mail)){
                        edtMail.setError("Email không hợp lệ");
                    }
                    else {
                        edtMail.setError(null);
                    }
                }

                if (pass.equals("") && pass1.equals("")){
                    passwordEditText.setError("Vui lòng nhập mật khẩu");
                    passwordEditText1.setError("Vui lòng nhập lại mật khẩu");
                }
                else {
                    passwordEditText.setError(null);
                    passwordEditText1.setError(null);
                    if (!pass.equals(pass1)){
                        passwordEditText.setError("Mật khẩu không khớp");
                        passwordEditText1.setError("Mật khẩu không khớp");
                    }
                    else {
                        passwordEditText.setError(null);
                        passwordEditText1.setError(null);
                    }
                }

            }
            else {
                showToast("Ban hay nhap day du thong tin");
                Intent intent = new Intent();
                intent.putExtra("fullName", fullName);
                intent.putExtra("mail", mail);
                setResult(1,intent);
                finish();
            }


        });

        tvDN.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });

    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void init(){
        tvSignup = findViewById(R.id.tvSignup);
        tvDN = findViewById(R.id.tvDN);
        edtFullName = findViewById(R.id.edtFullName);
        edtMail = findViewById(R.id.edtMail);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordEditText1 = findViewById(R.id.passwordEditText1);
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout);
        passwordTextInputLayout.setPasswordVisibilityToggleEnabled(true);
        passwordTextInputLayout1 = findViewById(R.id.passwordTextInputLayout1);
        passwordTextInputLayout1.setPasswordVisibilityToggleEnabled(true);

    }

    public void showToast(String t){
        Toast.makeText(this, t, Toast.LENGTH_SHORT).show();
    }
}