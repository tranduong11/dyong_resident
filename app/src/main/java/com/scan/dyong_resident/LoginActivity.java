package com.scan.dyong_resident;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    TextView tvLogin, tvDK;
    EditText edtEmail, edtPass;
    CheckBox cbSave;
    String userRegister = "", passRegister = "";
    private TextInputEditText passwordEditText;
    private TextInputLayout passwordTextInputLayout;
    private boolean isPasswordVisible = false;
    ImageView passwordToggleIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_screen);
        init();

        //ktra tt
        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        boolean isRemember = sharedPreferences.getBoolean("isRemember",false);
        if(isRemember){
            String phone = sharedPreferences.getString("phone","");
            String pass = sharedPreferences.getString("pass","");
            edtEmail.setText(phone);
//            edtPass.setText(pass);
            cbSave.setChecked(isRemember);

            userRegister = phone;
//            passRegister = pass;
        }

        tvLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString();
//            String pass = edtPass.getText().toString();

            if (isValidEmail(email)){
                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, BottomAppBarActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

            }
            else {
                Toast.makeText(this,"Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }

        });
        tvDK.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });

    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void init(){
        tvLogin = findViewById(R.id.tvlogin);
        tvDK = findViewById(R.id.tvDK);
        edtEmail = findViewById(R.id.edtMail);
//        edtPass = findViewById(R.id.edtPass);
        cbSave = findViewById(R.id.cbSave);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout);
        passwordTextInputLayout.setPasswordVisibilityToggleEnabled(true);
    }

}