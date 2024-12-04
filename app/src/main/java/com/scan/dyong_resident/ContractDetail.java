package com.scan.dyong_resident;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.scan.dyong_resident.models.Contract;
import com.scan.dyong_resident.R;

public class ContractDetail extends AppCompatActivity {

    TextView tvRoom, tvOwner, tvQuantity, tvNgayKy, tvNgayBatDau, tvNgayKetThuc, tvTienNha, tvTienCoc, tvInternet,
            tvNuocUong, tvNuocUongTheoNguoi, tvTienDien, tvMayGiat, tvKhac,
            tvToaNha, tvPhong, tvDiaChi, tvDateBatDau, tvDateKetThuc, tvTienNhaFee, tvTienCocFee, tvInternetFee,
            tvNuocUongFee, tvNuocTheoNguoiFee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);  // Nếu bạn sử dụng fragment_home.xml

        // Các TextView cho địa chỉ và phí
        tvToaNha = findViewById(R.id.txt_toaNha);
        tvPhong = findViewById(R.id.txt_phong);
        tvDiaChi = findViewById(R.id.txt_diaChi);
        tvDateBatDau = findViewById(R.id.txt_dateBatDau);
        tvDateKetThuc = findViewById(R.id.dateKetThuc);
        tvTienNhaFee = findViewById(R.id.txt_tienNha);
        tvTienCocFee = findViewById(R.id.txt_tienCoc);
        tvInternetFee = findViewById(R.id.txt_internet);
        tvNuocUongFee = findViewById(R.id.txt_nuocUong);
        tvNuocTheoNguoiFee = findViewById(R.id.txt_tienNuocTheoNguoi);

        // Lấy thông tin hợp đồng từ Intent
        Contract contract = (Contract) getIntent().getSerializableExtra("contractDetails");

        if (contract != null) {
            // Hiển thị thông tin hợp đồng lên các TextView
            tvRoom.setText("Phòng: " + contract.getRoom());
            tvOwner.setText("Chủ hợp đồng: " + contract.getOwner());
            tvQuantity.setText("Số lượng: " + contract.getQuantity());
            tvNgayKy.setText("Ngày ký: " + contract.getNgayKy());
            tvNgayBatDau.setText("Ngày bắt đầu: " + contract.getNgayBatDau());
            tvNgayKetThuc.setText("Ngày kết thúc: " + contract.getNgayKetThuc());
            tvTienNha.setText("Tiền nhà: " + contract.getTienNha() + " VND");
            tvTienCoc.setText("Tiền cọc: " + contract.getTienCoc() + " VND");
            tvInternet.setText("Internet: " + contract.getInternet() + " VND");
            tvNuocUong.setText("Nước uống: " + contract.getNuocUong() + " VND");
            tvNuocUongTheoNguoi.setText("Nước uống theo người: " + contract.getNuocUongTheoNguoi() + " VND");
            tvTienDien.setText("Tiền điện: " + contract.getTienDien() + " VND");
            tvMayGiat.setText("Máy giặt: " + contract.getMayGiat() + " VND");
            tvKhac.setText("Khác: " + contract.getKhac());

            // Các TextView cho địa chỉ và phí
            tvToaNha.setText(contract.getRoom());
            tvDateBatDau.setText(contract.getNgayBatDau());
            tvDateKetThuc.setText(contract.getNgayKetThuc());
            tvTienNhaFee.setText(contract.getTienNha() + " VND");
            tvTienCocFee.setText(contract.getTienCoc() + " VND");
            tvInternetFee.setText(contract.getInternet() + " VND");
            tvNuocUongFee.setText(contract.getNuocUong() + " VND");
            tvNuocTheoNguoiFee.setText(contract.getNuocUongTheoNguoi() + " VND");
        }
    }
}
