package com.scan.dyong_resident;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.scan.dyong_resident.models.Contract;

public class ContractDetail extends AppCompatActivity {

    TextView tvRoom, tvOwner,tvNgayBatDau, tvNgayKetThuc, tvTienNha, tvTienCoc, tvInternet,
            tvNuocUong, tvNuocUongTheoNguoi, tvTienDien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contract_detail);

        tvRoom = findViewById(R.id.txt_toaNha);
        tvOwner = findViewById(R.id.txt_phong);
        tvNgayBatDau = findViewById(R.id.txt_dateBatDau);
        tvNgayKetThuc = findViewById(R.id.dateKetThuc);
        tvTienNha = findViewById(R.id.txt_tienNha);
        tvTienCoc = findViewById(R.id.txt_tienCoc);
        tvInternet = findViewById(R.id.txt_internet);
        tvNuocUong = findViewById(R.id.txt_nuocUong);
        tvNuocUongTheoNguoi = findViewById(R.id.txt_tienNuocTheoNguoi);
        tvTienDien = findViewById(R.id.txt_hoTenChuPhong);

        Contract contract = (Contract) getIntent().getSerializableExtra("contract_detail");

        if (contract != null) {
            tvRoom.setText("Phòng: " + contract.getRoom());
            tvOwner.setText("Chủ hợp đồng: " + contract.getOwner());
            tvNgayBatDau.setText("Ngày bắt đầu: " + contract.getNgayBatDau());
            tvNgayKetThuc.setText("Ngày kết thúc: " + contract.getNgayKetThuc());
            tvTienNha.setText("Tiền nhà: " + contract.getTienNha() + " VND");
            tvTienCoc.setText("Tiền cọc: " + contract.getTienCoc() + " VND");
            tvInternet.setText("Internet: " + contract.getInternet() + " VND");
            tvNuocUong.setText("Nước uống: " + contract.getNuocUong() + " VND");
            tvNuocUongTheoNguoi.setText("Nước uống theo người: " + contract.getNuocUongTheoNguoi() + " VND");
        }
    }
}
