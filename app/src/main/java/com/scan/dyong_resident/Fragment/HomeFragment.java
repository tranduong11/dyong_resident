package com.scan.dyong_resident.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.scan.dyong_resident.R;

public class HomeFragment extends Fragment {
    TextView tvChiTiet;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tvChiTiet = view.findViewById(R.id.tvChiTiet);
        tvChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển sang Fragment khác
                Fragment detailFragment = new DetailContractFragment();
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, detailFragment) // Thay fragment_container bằng ID container chính của bạn
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }


}

