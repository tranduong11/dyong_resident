package com.scan.dyong_resident.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.scan.dyong_resident.ContractManagementActivity;
import com.scan.dyong_resident.ManageRoomActivity;
import com.scan.dyong_resident.R;
import com.scan.dyong_resident.RoomActivity;

public class ContractManagementFragment extends Fragment {

    Button btn_danhSachHopDong, btn_danhSachPhong, btn_quanLyPhong;

    public ContractManagementFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contract_management, container, false);

        btn_danhSachHopDong = rootView.findViewById(R.id.btn_danhSachHopDong);
        btn_danhSachPhong = rootView.findViewById(R.id.btn_danhSachPhong);
        btn_quanLyPhong = rootView.findViewById(R.id.btn_quanLyPhong);

        btn_danhSachHopDong.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ContractManagementActivity.class);
            startActivity(intent);
        });
        btn_danhSachPhong.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RoomActivity.class);
            startActivity(intent);
        });
        btn_quanLyPhong.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ManageRoomActivity.class);
            startActivity(intent);
        });
        return rootView;
    }
}