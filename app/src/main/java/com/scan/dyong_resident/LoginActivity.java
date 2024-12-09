package com.scan.dyong_resident;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.CheckBox;
import android.widget.EditText;
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
    private final String ADMIN_ROLE = "admin";
    private final String USER_ROLE = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        init();
        roleHelper = new RoleHelper(this);

        // Load trạng thái đăng nhập
        loadLoginState();

        // Xử lý sự kiện đăng nhập
        tvLogin.setOnClickListener(v -> handleLogin());

        // Chuyển sang màn hình đăng ký
        tvDK.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
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


    private void handleLogin() {
        String email = edtEmail.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra tài khoản admin mặc định
        if (email.equals("admin@dyong.com") && password.equals("admin123")) {
            navigateToActivity("Đăng nhập admin thành công", BottomAppBarActivity.class);
            return;  // Nếu đăng nhập admin thành công, không kiểm tra tiếp
        }

        // Kiểm tra các tài khoản khác
        if (roleHelper.isUserValid(email, password)) {
            String role = roleHelper.getUserRole(email);

            switch (role) {
                case ADMIN_ROLE:
                    navigateToActivity("Đăng nhập admin thành công", BottomAppBarActivity.class);
                    break;
                case USER_ROLE:
                    navigateToActivity("Đăng nhập user thành công", BottomAppBarUserActivity.class);
                    break;
                default:
                    Toast.makeText(this, "Quyền không hợp lệ", Toast.LENGTH_SHORT).show();
                    break;
            }

            // Lưu trạng thái đăng nhập
            saveLoginState(email, cbSave.isChecked());
        } else {
            Toast.makeText(this, "Sai email hoặc mật khẩu", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Kiểm tra email hợp lệ
     */
    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Lưu trạng thái đăng nhập
     */
//    private void saveLoginState(String email, boolean isRemember) {
//        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putBoolean("isRemember", isRemember);
//        if (isRemember) {
//            editor.putString("email", email);
//            Log.d("LoginActivity", "Saved email: " + email);
//        }
//        //else {
//            //editor.remove("email");
//        //}
//        editor.apply();
//    }

    private void saveLoginState(String email, boolean isRemember) {
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Lưu trạng thái "isRemember" (lưu tài khoản hay không)
        editor.putBoolean("isRemember", isRemember);

        if (isRemember) {
            // Lưu email vào SharedPreferences nếu isRemember là true
            editor.putString("email", email);
            Log.d("LoginActivity", "Saved email: " + email);  // Log để kiểm tra xem email có thực sự được lưu không
        } else {
            // Nếu không lưu email, xóa email khỏi SharedPreferences
            editor.putString("email", email);
            //editor.remove("email");
            Log.d("LoginActivity", "Removed email from SharedPreferences");
        }

        // Áp dụng thay đổi
        editor.apply();
    }


    /**
     * Tải trạng thái đăng nhập
     */
    private void loadLoginState() {
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        boolean isRemember = sharedPreferences.getBoolean("isRemember", false);
        if (isRemember) {
            String email = sharedPreferences.getString("email", "");
            edtEmail.setText(email);
            cbSave.setChecked(true);
        }
    }

    /**
     * Điều hướng tới Activity khác
     */
    private void navigateToActivity(String message, Class<?> targetActivity) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, targetActivity);
        startActivity(intent);
        finish();
    }
}
