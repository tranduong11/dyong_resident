package com.scan.dyong_resident;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.scan.dyong_resident.Adapter.TabAdapter;

import androidx.viewpager2.widget.ViewPager2;

public class ManageRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_room);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);

        TabAdapter tabAdapter = new TabAdapter(this);
        viewPager.setAdapter(tabAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Phòng đã cho thuê");
                    break;
                case 1:
                    tab.setText("Phòng còn trống");
                    break;
            }
        }).attach();
    }
}
