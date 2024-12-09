package com.scan.dyong_resident.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.scan.dyong_resident.AddBillActivity;
import com.scan.dyong_resident.AddContractActivity;
import com.scan.dyong_resident.AddRoomActivity;
import com.scan.dyong_resident.R;

public class AddContractFragment extends Fragment {

    Button btn_taoHopDong, btn_taoHoaDon, btn_themPhongTro;

    public AddContractFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_operation, container, false);

        btn_taoHopDong = rootView.findViewById(R.id.btn_taoHopDong);
        btn_taoHoaDon = rootView.findViewById(R.id.btn_taoHoaDon);
        btn_themPhongTro = rootView.findViewById(R.id.btn_themPhongTro);

        btn_taoHopDong.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddContractActivity.class);
            startActivity(intent);
        });

        btn_taoHoaDon.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddBillActivity.class);
            startActivity(intent);
        });
        btn_themPhongTro.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddRoomActivity.class);
            startActivity(intent);
        });
        return rootView;
    }
}
