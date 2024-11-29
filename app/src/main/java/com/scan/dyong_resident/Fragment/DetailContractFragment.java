package com.scan.dyong_resident.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scan.dyong_resident.R;


public class DetailContractFragment extends Fragment {

    private TextView tvContractContent;


    public DetailContractFragment() {
        // Required empty public constructor
    }


    public static DetailContractFragment newInstance(String param1, String param2) {
        DetailContractFragment fragment = new DetailContractFragment();
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
        View view =  inflater.inflate(R.layout.fragment_detail_contract, container, false);


        return view;
    }
}