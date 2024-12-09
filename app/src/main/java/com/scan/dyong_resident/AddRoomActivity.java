package com.scan.dyong_resident;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.scan.dyong_resident.database.RoomHelper;
import com.scan.dyong_resident.models.Room;

public class AddRoomActivity extends AppCompatActivity {

    private EditText edtTenPhong, edtViTriSoTang, edtMoTa;
    private Spinner spinnerTrangThai;
    private TextView btnThemPhong;
    private RoomHelper roomHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);

        roomHelper = new RoomHelper(this);

        edtTenPhong = findViewById(R.id.edt_tenPhong);
        edtViTriSoTang = findViewById(R.id.edt_viTriSoTang);
        spinnerTrangThai = findViewById(R.id.spinner_trangThai);
        edtMoTa = findViewById(R.id.edt_moTa);

        btnThemPhong = findViewById(R.id.btn_themPhong);

        String[] trangThaiOptions = new String[]{"Phòng còn trống", "Phòng đã cho thuê"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, trangThaiOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTrangThai.setAdapter(adapter);

        btnThemPhong.setOnClickListener(v -> addRoom());
    }

    private void addRoom() {
        String tenPhong = edtTenPhong.getText().toString().trim();
        String viTriSoTang = edtViTriSoTang.getText().toString().trim();
        String trangThai = spinnerTrangThai.getSelectedItem().toString().trim();
        String moTa = edtMoTa.getText().toString().trim();

        if (TextUtils.isEmpty(tenPhong) || TextUtils.isEmpty(viTriSoTang) || TextUtils.isEmpty(trangThai)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin phòng.", Toast.LENGTH_SHORT).show();
            return;
        }

        Room room = new Room(
                -1,
                tenPhong,
                viTriSoTang,
                trangThai,
                moTa
        );

        boolean isInserted = roomHelper.addRoom(room);

        if (isInserted) {
            Toast.makeText(this, "Thêm phòng thành công!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Lỗi khi thêm phòng.", Toast.LENGTH_SHORT).show();
        }
    }
}