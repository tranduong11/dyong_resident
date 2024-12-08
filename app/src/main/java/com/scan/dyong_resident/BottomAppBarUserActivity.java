package com.scan.dyong_resident;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.scan.dyong_resident.Fragment.AddContractFragment;
import com.scan.dyong_resident.Fragment.BillFragment;
import com.scan.dyong_resident.Fragment.ContractManagementFragment;
import com.scan.dyong_resident.Fragment.EmailFragment;
import com.scan.dyong_resident.Fragment.ProfileFragment;

public class BottomAppBarUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomappbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.action_home) {
//                selectedFragment = new ContractManagementFragment();
            } else if (item.getItemId() == R.id.action_email) {
//                selectedFragment = new EmailFragment();
            } else if (item.getItemId() == R.id.action_add) {
//                selectedFragment = new AddContractFragment();
            } else if (item.getItemId() == R.id.action_bill) {
//                selectedFragment = new BillFragment();
            } else if (item.getItemId() == R.id.action_profile) {
                selectedFragment = new ProfileFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });

        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.action_home);
        }
    }
}