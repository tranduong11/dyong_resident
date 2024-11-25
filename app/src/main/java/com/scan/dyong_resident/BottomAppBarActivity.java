package com.scan.dyong_resident;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.scan.dyong_resident.Fragment.HomeFragment;
import com.scan.dyong_resident.Fragment.ProfileFragment;
import com.scan.dyong_resident.Fragment.EmailFragment;

public class BottomAppBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomappbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.action_home) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.action_email) {
                selectedFragment = new EmailFragment();
            } else if (item.getItemId() == R.id.action_add) {
                selectedFragment = new ProfileFragment();
            } else if (item.getItemId() == R.id.action_bill) {
            } else if (item.getItemId() == R.id.action_profile) {
            }

            // Thay thế fragment nếu có fragment được chọn
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true; // Trả về true để sự kiện được xử lý
        });

        // Đặt mục mặc định là HomeFragment khi Activity được tạo lần đầu tiên
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.action_home); // Đặt "Trang chủ" làm mục mặc định
        }
    }
}
