package com.scan.dyong_resident.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scan.dyong_resident.R;

public class AddContractFragment extends Fragment {


    public AddContractFragment() {
        // Required empty public constructor
    }

    public static AddContractFragment newInstance(String param1, String param2) {
        AddContractFragment fragment = new AddContractFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_contract, container, false);
    }
}