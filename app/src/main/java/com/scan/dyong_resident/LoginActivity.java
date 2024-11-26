package com.scan.dyong_resident;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    TextView tvLogin, tvDK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_screen);
        init();
        tvLogin.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, BottomAppBarActivity.class);
            startActivity(intent);
        });
        tvDK.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });

    }
    public void init(){
        tvLogin = findViewById(R.id.tvlogin);
        tvDK = findViewById(R.id.tvDK);
    }

}