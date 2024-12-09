package com.scan.dyong_resident;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.scan.dyong_resident.database.BillHelper;
import com.scan.dyong_resident.database.RoomHelper;
import com.scan.dyong_resident.models.Bill;
import com.scan.dyong_resident.models.Room;

import java.util.ArrayList;
import java.util.List;

public class AddBillActivity extends AppCompatActivity {

    private EditText edtChuHopDong, edtSoLuong, edtKyThanhToan, edtNgayLap, edtHanThanhToan,
            edtTienPhong, edtTienDien, edtTienNuoc, edtTienMang, edtTienThangMay, edtTienRac;
    private Spinner edtPhong;
    private TextView btnCreateBill;
    private BillHelper billHelper;
    private RoomHelper roomHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);

        billHelper = new BillHelper(this);
        roomHelper = new RoomHelper(this);

        edtChuHopDong = findViewById(R.id.edt_chuHopDong);
        edtSoLuong = findViewById(R.id.edt_soLuong);
        edtKyThanhToan = findViewById(R.id.edt_kyThanhToan);
        edtNgayLap = findViewById(R.id.edt_ngayLap);
        edtHanThanhToan = findViewById(R.id.edt_hanThanhToan);
        edtTienPhong = findViewById(R.id.edt_tienPhong);
        edtTienDien = findViewById(R.id.edt_tienDien);
        edtTienNuoc = findViewById(R.id.edt_tienNuoc);
        edtTienMang = findViewById(R.id.edt_tienMang);
        edtTienThangMay = findViewById(R.id.edt_tienThangMay);
        edtTienRac = findViewById(R.id.edt_tienRac);

        edtPhong = findViewById(R.id.edt_phong);

        btnCreateBill = findViewById(R.id.btn_taoHoaDon);

        btnCreateBill.setOnClickListener(v -> createBill());

        loadRoomData();
    }

    private void loadRoomData() {
        List<Room> roomsForRent = roomHelper.getRoomsForRent();

        List<String> roomNames = new ArrayList<>();
        for (Room room : roomsForRent) {
            roomNames.add(room.getTenPhong());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roomNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtPhong.setAdapter(adapter);
    }

    private void createBill() {
        String phong = edtPhong.getSelectedItem().toString().trim();
        String chuHopDong = edtChuHopDong.getText().toString().trim();
        String soLuong = edtSoLuong.getText().toString().trim();
        String kyThanhToan = edtKyThanhToan.getText().toString().trim();
        String ngayLap = edtNgayLap.getText().toString().trim();
        String hanThanhToan = edtHanThanhToan.getText().toString().trim();
        String tienPhong = edtTienPhong.getText().toString().trim();
        String tienDien = edtTienDien.getText().toString().trim();
        String tienNuoc = edtTienNuoc.getText().toString().trim();
        String tienMang = edtTienMang.getText().toString().trim();
        String tienThangMay = edtTienThangMay.getText().toString().trim();
        String tienRac = edtTienRac.getText().toString().trim();

        if (TextUtils.isEmpty(phong) || TextUtils.isEmpty(chuHopDong) || TextUtils.isEmpty(soLuong)) {
            Toast.makeText(this, "Vui lòng nhập thông tin cơ bản.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(tienPhong) || TextUtils.isEmpty(tienDien) || TextUtils.isEmpty(tienNuoc) ||
                TextUtils.isEmpty(tienMang) || TextUtils.isEmpty(tienThangMay) || TextUtils.isEmpty(tienRac)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ các thông tin về tiền.", Toast.LENGTH_SHORT).show();
            return;
        }

        Bill bill = new Bill(
                -1,
                phong,
                chuHopDong,
                kyThanhToan,
                ngayLap,
                hanThanhToan,
                Integer.parseInt(soLuong),
                Double.parseDouble(tienPhong),
                Double.parseDouble(tienDien),
                Double.parseDouble(tienNuoc),
                Double.parseDouble(tienMang),
                Double.parseDouble(tienThangMay),
                Double.parseDouble(tienRac)
        );

        boolean isInserted = billHelper.addBill(bill);

        if (isInserted) {
            Toast.makeText(this, "Tạo hóa đơn thành công!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Lỗi khi tạo hóa đơn.", Toast.LENGTH_SHORT).show();
        }
    }
}
