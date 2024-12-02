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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tvChiTiet = view.findViewById(R.id.tvChiTiet);

        return view;
    }
}