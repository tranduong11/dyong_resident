package com.scan.dyong_resident.Adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.scan.dyong_resident.Fragment.NotPaidFragment;
import com.scan.dyong_resident.Fragment.PaidFragment;

public class TabAdapter extends FragmentStateAdapter {

    public TabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PaidFragment();
            case 1:
                return new NotPaidFragment();
            default:
                return new PaidFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

