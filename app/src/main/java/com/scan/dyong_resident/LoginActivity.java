package com.scan.dyong_resident;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    TextView tvLogin, tvDK;
    EditText edtPhone, edtPass;
    CheckBox cbSave;
    String userRegister = "", passRegister = "";
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
            edtPhone.setText(phone);
            edtPass.setText(pass);
            cbSave.setChecked(isRemember);

            userRegister = phone;
            passRegister = pass;
        }

        tvLogin.setOnClickListener(v -> {
            String phone = edtPhone.getText().toString();
            String pass = edtPass.getText().toString();

            if (phone.length()>0 && pass.length()>0 && phone.equals(userRegister) && pass.equals(passRegister)){
                if (cbSave.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isRemember",true);
                    editor.putString("phone",phone);
                    editor.putString("pass",pass);
                    editor.apply();

                }
                else {
                    SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.apply();
                }

                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, BottomAppBarActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
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
    public void init(){
        tvLogin = findViewById(R.id.tvlogin);
        tvDK = findViewById(R.id.tvDK);
        edtPhone = findViewById(R.id.edtPhone);
        edtPass = findViewById(R.id.edtPass);
        cbSave = findViewById(R.id.cbSave);

    }

}