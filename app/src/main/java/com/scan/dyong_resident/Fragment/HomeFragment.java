package com.scan.dyong_resident.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.scan.dyong_resident.database.ContractHelper;
import com.scan.dyong_resident.R;
import com.scan.dyong_resident.models.ContractData;

public class HomeFragment extends Fragment {

    private TextView txtToaNha, txtPhong, txtDiaChi, txtNgayBatDau, txtNgayKetThuc;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Khởi tạo các TextView
        txtToaNha = view.findViewById(R.id.txt_toaNha);
        txtPhong = view.findViewById(R.id.txt_phong);
        txtDiaChi = view.findViewById(R.id.txt_diaChi);
        txtNgayBatDau = view.findViewById(R.id.txt_ngayBatDau);
        txtNgayKetThuc = view.findViewById(R.id.txt_ngayKetThuc);

        // Lấy thông tin hợp đồng từ cơ sở dữ liệu và hiển thị lên giao diện
        loadContractData();

        return view;
    }

    private void loadContractData() {
        ContractHelper dbHelper = new ContractHelper(getContext());

        // Nếu có ID hợp đồng, ở đây ta lấy hợp đồng với ID = 1
        int contractId = 1;
        ContractData contract = dbHelper.getContractById(contractId);

        if (contract != null) {
            // Cập nhật giao diện với dữ liệu hợp đồng
            txtToaNha.setText(contract.getRoom());
            txtPhong.setText(contract.getRoom());
            txtDiaChi.setText("Địa chỉ chi tiết: " + contract.getRoom());
            txtNgayBatDau.setText(contract.getStartDate());
            txtNgayKetThuc.setText(contract.getEndDate());
        } else {
            // Nếu không tìm thấy hợp đồng, hiển thị thông báo
            Toast.makeText(getContext(), "Không tìm thấy hợp đồng!", Toast.LENGTH_SHORT).show();
        }
    }
}
