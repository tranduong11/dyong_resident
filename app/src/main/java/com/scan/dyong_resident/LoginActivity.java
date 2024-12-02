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

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.scan.dyong_resident.database.RoleHelper;

public class LoginActivity extends AppCompatActivity {
    TextView tvLogin, tvDK;
    EditText edtEmail;
    CheckBox cbSave;
    private TextInputEditText passwordEditText;
    private TextInputLayout passwordTextInputLayout;
    private RoleHelper roleHelper;
    private final String ADMIN_EMAIL = "admin@dyong.com";
    private final String ADMIN_PASSWORD = "admin123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        init();

        roleHelper = new RoleHelper(this);

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        boolean isRemember = sharedPreferences.getBoolean("isRemember", false);
        if (isRemember) {
            String phone = sharedPreferences.getString("phone", "");
            edtEmail.setText(phone);
            cbSave.setChecked(isRemember);
        }

        tvLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString();
            String password = passwordEditText.getText().toString();

            if (isValidEmail(email)) {
                if (email.equals(ADMIN_EMAIL) && password.equals(ADMIN_PASSWORD)) {
                    Toast.makeText(this, "Đăng nhập admin thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, BottomAppBarActivity.class);
                    startActivity(intent);
                    finish();
                } else if (roleHelper.isUserValid(email, password)) {
                    Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, BottomAppBarActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Sai email hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
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

    public void init() {
        tvLogin = findViewById(R.id.tvlogin);
        tvDK = findViewById(R.id.tvDK);
        edtEmail = findViewById(R.id.edtMail);
        cbSave = findViewById(R.id.cbSave);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout);
        passwordTextInputLayout.setPasswordVisibilityToggleEnabled(true);
    }
}
