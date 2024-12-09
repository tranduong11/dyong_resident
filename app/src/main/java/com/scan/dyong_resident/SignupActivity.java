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
import com.scan.dyong_resident.database.RoleHelper;
import com.scan.dyong_resident.models.User;

public class SignupActivity extends AppCompatActivity {
    TextView tvSignup, tvDN;
    EditText edtFullName, edtMail;
    private TextInputEditText passwordEditText, passwordEditText1;
    private TextInputLayout passwordTextInputLayout, passwordTextInputLayout1;
    private RoleHelper roleHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.signup_screen);
        roleHelper = new RoleHelper(this); // Khởi tạo RoleHelper
        init();

        // Validate họ tên
        edtFullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    edtFullName.setError("Vui lòng nhập họ tên");
                } else {
                    edtFullName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        // Validate email
        edtMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    edtMail.setError("Vui lòng nhập email");
                } else if (!isValidEmail(s.toString())) {
                    edtMail.setError("Email không hợp lệ");
                } else {
                    edtMail.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        // Xử lý sự kiện khi nhấn nút đăng ký
        tvSignup.setOnClickListener(v -> {
            String fullName = edtFullName.getText().toString().trim();
            String mail = edtMail.getText().toString().trim();
            String pass = passwordEditText.getText().toString().trim();
            String pass1 = passwordEditText1.getText().toString().trim();

            // Kiểm tra các trường nhập liệu
            if (fullName.isEmpty()) {
                edtFullName.setError("Vui lòng nhập họ tên");
                return;
            }

            if (mail.isEmpty() || !isValidEmail(mail)) {
                edtMail.setError(mail.isEmpty() ? "Vui lòng nhập email" : "Email không hợp lệ");
                return;
            }

            if (pass.isEmpty() || pass1.isEmpty()) {
                passwordEditText.setError("Vui lòng nhập mật khẩu");
                passwordEditText1.setError("Vui lòng nhập lại mật khẩu");
                return;
            }

            if (!pass.equals(pass1)) {
                passwordEditText.setError("Mật khẩu không khớp");
                passwordEditText1.setError("Mật khẩu không khớp");
                return;
            }

            // Kiểm tra email đã tồn tại chưa
            if (roleHelper.isEmailExists(mail)) {
                edtMail.setError("Email đã được sử dụng");
                return;
            }

            // Tạo user và lưu vào cơ sở dữ liệu
            User user = new User(0, fullName, mail, pass, "user");
            long result = roleHelper.addUser(user);

            if (result != -1) {
                showToast("Đăng ký thành công!");
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                showToast("Đăng ký thất bại! Vui lòng thử lại.");
            }
        });

        // Xử lý sự kiện chuyển về màn hình đăng nhập
        tvDN.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    // Hàm kiểm tra email hợp lệ
    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Khởi tạo các thành phần giao diện
    public void init() {
        tvSignup = findViewById(R.id.tvSignup); // Khởi tạo tvSignup
        tvDN = findViewById(R.id.tvDN); // Khởi tạo tvDN
        edtFullName = findViewById(R.id.edtFullName);
        edtMail = findViewById(R.id.edtMail);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordEditText1 = findViewById(R.id.passwordEditText1);
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout);
        passwordTextInputLayout.setPasswordVisibilityToggleEnabled(true);
        passwordTextInputLayout1 = findViewById(R.id.passwordTextInputLayout1);
        passwordTextInputLayout1.setPasswordVisibilityToggleEnabled(true);
    }


    // Hàm hiển thị thông báo
    public void showToast(String t) {
        Toast.makeText(this, t, Toast.LENGTH_SHORT).show();
    }
}
