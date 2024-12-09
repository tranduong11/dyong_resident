package com.scan.dyong_resident;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BillDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billdetails);

        // Lấy dữ liệu từ Intent
        String phong = getIntent().getStringExtra("phong");
        String chuHopDong = getIntent().getStringExtra("chuHopDong");
        String kyThanhToan = getIntent().getStringExtra("kyThanhToan");
        String ngayLap = getIntent().getStringExtra("ngayLap");
        String hanThanhToan = getIntent().getStringExtra("hanThanhToan");
        int soLuong = getIntent().getIntExtra("soLuong", 0);
        double tienPhong = getIntent().getDoubleExtra("tienPhong", 0);
        double tienDien = getIntent().getDoubleExtra("tienDien", 0);
        double tienNuoc = getIntent().getDoubleExtra("tienNuoc", 0);
        double tienMang = getIntent().getDoubleExtra("tienMang", 0);
        double tienThangMay = getIntent().getDoubleExtra("tienThangMay", 0);
        double tienRac = getIntent().getDoubleExtra("tienRac", 0);

        // Các TextView để hiển thị dữ liệu
        TextView txtPhong = findViewById(R.id.txt_phong);
        TextView txtNgayLap = findViewById(R.id.txt_ngayXuat);
        TextView txtHanThanhToan = findViewById(R.id.txt_denNgay);
        TextView txtTienPhong = findViewById(R.id.txt_tienPhong);
        TextView txtTienDien = findViewById(R.id.txt_tienDien);
        TextView txtTienNuoc = findViewById(R.id.txt_tienNuoc);
        TextView txtTienMang = findViewById(R.id.txt_tienMang);
        TextView txtTienThangMay = findViewById(R.id.txt_tienThangMay);
        TextView txtTienRac = findViewById(R.id.txt_tienRac);
        TextView txt_tongSoTien = findViewById(R.id.txt_tongSoTien);

        // Hiển thị dữ liệu vào các TextView
        txtPhong.setText(phong);
        txtNgayLap.setText(ngayLap);
        txtHanThanhToan.setText(hanThanhToan);
        txtTienPhong.setText(String.format("%,.2f VND", tienPhong));
        txtTienDien.setText(String.format("%,.2f VND", tienDien));
        txtTienNuoc.setText(String.format("%,.2f VND", tienNuoc));
        txtTienMang.setText(String.format("%,.2f VND", tienMang));
        txtTienThangMay.setText(String.format("%,.2f VND", tienThangMay));
        txtTienRac.setText(String.format("%,.2f VND", tienRac));

        // Tính tổng tiền
        double tongTien = tienPhong + tienDien + tienNuoc + tienMang + tienThangMay + tienRac;
        TextView txtTongTien = findViewById(R.id.txt_tongTien);
        txtTongTien.setText(String.format("%,.2f VND", tongTien));
        txt_tongSoTien.setText(String.format("%,.2f VND", tongTien));
    }
}
