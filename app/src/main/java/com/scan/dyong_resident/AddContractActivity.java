//package com.scan.dyong_resident;
//
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.widget.ArrayAdapter;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import com.scan.dyong_resident.database.ContractHelper;
//import com.scan.dyong_resident.database.RoomHelper;
//import com.scan.dyong_resident.models.Contract;
//import com.scan.dyong_resident.models.Room;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AddContractActivity extends AppCompatActivity {
//
//    private EditText edtChuHopDong, edtSoLuong, edtDate, edtDateStart, edtDateEnd, edtTienNha,
//            edtTienCoc, edtInternet, edtNuocUong, edtNuocUongTheoNguoi, edtTienDien, edtMayGiat, edtKhac, edtNguoiThue;
//
//    private Spinner edtPhong;
//    private TextView btnCreateContract;
//    private ContractHelper contractHelper;
//    private RoomHelper roomHelper;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_add_contract);
//
//        contractHelper = new ContractHelper(this);
//        roomHelper = new RoomHelper(this);
//
//        edtPhong = findViewById(R.id.edt_phong);
//        edtChuHopDong = findViewById(R.id.edt_chuHopDong);
//        edtSoLuong = findViewById(R.id.edt_soLuong);
//        edtDate = findViewById(R.id.edt_date);
//        edtDateStart = findViewById(R.id.edt_date_start);
//        edtDateEnd = findViewById(R.id.edt_date_end);
//        edtTienNha = findViewById(R.id.edt_tienNha);
//        edtTienCoc = findViewById(R.id.edt_tienCoc);
//        edtInternet = findViewById(R.id.edt_Internet);
//        edtNuocUong = findViewById(R.id.edt_nuocUong);
//        edtNuocUongTheoNguoi = findViewById(R.id.edt_nuocUongTheoNguoi);
//        edtTienDien = findViewById(R.id.edt_tienDien);
//        edtMayGiat = findViewById(R.id.edt_mayGiat);
//        edtKhac = findViewById(R.id.edt_khac);
//        edtNguoiThue = findViewById(R.id.edt_nguoiThue);  // Thêm EditText cho người thuê
//
//        btnCreateContract = findViewById(R.id.tv_create_contract);
//
//        btnCreateContract.setOnClickListener(v -> createContract());
//        loadRoomData();
//    }
//
//    private void loadRoomData() {
//        List<Room> roomsForRent = roomHelper.getRoomNotPaid();
//
//        List<String> roomNames = new ArrayList<>();
//        for (Room room : roomsForRent) {
//            roomNames.add(room.getTenPhong());
//        }
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roomNames);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        edtPhong.setAdapter(adapter);
//    }
//
//    private void createContract() {
//        String phong = edtPhong.getSelectedItem().toString().trim();
//        String chuHopDong = edtChuHopDong.getText().toString().trim();
//        String soLuong = edtSoLuong.getText().toString().trim();
//        String date = edtDate.getText().toString().trim();
//        String dateStart = edtDateStart.getText().toString().trim();
//        String dateEnd = edtDateEnd.getText().toString().trim();
//        String tienNha = edtTienNha.getText().toString().trim();
//        String tienCoc = edtTienCoc.getText().toString().trim();
//        String internet = edtInternet.getText().toString().trim();
//        String nuocUong = edtNuocUong.getText().toString().trim();
//        String nuocUongTheoNguoi = edtNuocUongTheoNguoi.getText().toString().trim();
//        String tienDien = edtTienDien.getText().toString().trim();
//        String mayGiat = edtMayGiat.getText().toString().trim();
//        String khac = edtKhac.getText().toString().trim();
//        String nguoiThue = edtNguoiThue.getText().toString().trim();  // Lấy giá trị từ edtNguoiThue
//
//        if (TextUtils.isEmpty(phong) || TextUtils.isEmpty(chuHopDong) || TextUtils.isEmpty(soLuong) || TextUtils.isEmpty(nguoiThue)) {
//            Toast.makeText(this, "Vui lòng nhập thông tin cơ bản.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        Contract contract = new Contract(
//                -1,
//                phong,
//                chuHopDong,
//                Integer.parseInt(soLuong),
//                date,
//                dateStart,
//                dateEnd,
//                Double.parseDouble(tienNha),
//                Double.parseDouble(tienCoc),
//                Double.parseDouble(internet),
//                Double.parseDouble(nuocUong),
//                Double.parseDouble(nuocUongTheoNguoi),
//                Double.parseDouble(tienDien),
//                Double.parseDouble(mayGiat),
//                khac,
//                nguoiThue  // Thêm người thuê vào constructor
//        );
//        boolean isInserted = contractHelper.addContract(contract);
//
//        if (isInserted) {
//            Toast.makeText(this, "Tạo hợp đồng thành công!", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Lỗi khi tạo hợp đồng.", Toast.LENGTH_SHORT).show();
//        }
//    }
//}
package com.scan.dyong_resident;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.scan.dyong_resident.database.ContractHelper;
import com.scan.dyong_resident.database.RoomHelper;
import com.scan.dyong_resident.models.Contract;
import com.scan.dyong_resident.models.Room;

import java.util.ArrayList;
import java.util.List;

public class AddContractActivity extends AppCompatActivity {

    private EditText edtChuHopDong, edtSoLuong, edtDate, edtDateStart, edtDateEnd, edtTienNha,
            edtTienCoc, edtInternet, edtNuocUong, edtNuocUongTheoNguoi, edtTienDien, edtMayGiat, edtKhac, edtNguoiThue, edtEmailNguoiThue;

    private Spinner edtPhong;
    private TextView btnCreateContract;
    private ContractHelper contractHelper;
    private RoomHelper roomHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_contract);

        contractHelper = new ContractHelper(this);
        roomHelper = new RoomHelper(this);

        edtPhong = findViewById(R.id.edt_phong);
        edtChuHopDong = findViewById(R.id.edt_chuHopDong);
        edtSoLuong = findViewById(R.id.edt_soLuong);
        edtDate = findViewById(R.id.edt_date);
        edtDateStart = findViewById(R.id.edt_date_start);
        edtDateEnd = findViewById(R.id.edt_date_end);
        edtTienNha = findViewById(R.id.edt_tienNha);
        edtTienCoc = findViewById(R.id.edt_tienCoc);
        edtInternet = findViewById(R.id.edt_Internet);
        edtNuocUong = findViewById(R.id.edt_nuocUong);
        edtNuocUongTheoNguoi = findViewById(R.id.edt_nuocUongTheoNguoi);
        edtTienDien = findViewById(R.id.edt_tienDien);
        edtMayGiat = findViewById(R.id.edt_mayGiat);
        edtKhac = findViewById(R.id.edt_khac);
        edtNguoiThue = findViewById(R.id.edt_nguoiThue);  // Thêm EditText cho người thuê
        edtEmailNguoiThue = findViewById(R.id.edt_emailNguoiThue);  // Đảm bảo sử dụng đúng ID

        btnCreateContract = findViewById(R.id.tv_create_contract);

        btnCreateContract.setOnClickListener(v -> createContract());
        loadRoomData();
    }

    private void loadRoomData() {
        List<Room> roomsForRent = roomHelper.getRoomNotPaid();

        List<String> roomNames = new ArrayList<>();
        for (Room room : roomsForRent) {
            roomNames.add(room.getTenPhong());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roomNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtPhong.setAdapter(adapter);
    }

    private void createContract() {
        String phong = edtPhong.getSelectedItem().toString().trim();
        String chuHopDong = edtChuHopDong.getText().toString().trim();
        String soLuong = edtSoLuong.getText().toString().trim();
        String date = edtDate.getText().toString().trim();
        String dateStart = edtDateStart.getText().toString().trim();
        String dateEnd = edtDateEnd.getText().toString().trim();
        String tienNha = edtTienNha.getText().toString().trim();
        String tienCoc = edtTienCoc.getText().toString().trim();
        String internet = edtInternet.getText().toString().trim();
        String nuocUong = edtNuocUong.getText().toString().trim();
        String nuocUongTheoNguoi = edtNuocUongTheoNguoi.getText().toString().trim();
        String tienDien = edtTienDien.getText().toString().trim();
        String mayGiat = edtMayGiat.getText().toString().trim();
        String khac = edtKhac.getText().toString().trim();
        String nguoiThue = edtNguoiThue.getText().toString().trim();  // Lấy giá trị từ edtNguoiThue
        String emailNguoiThue = edtEmailNguoiThue.getText().toString().trim();  // Lấy giá trị từ edtEmailNguoiThue

        // Kiểm tra xem các thông tin quan trọng có bị thiếu không
        if (TextUtils.isEmpty(phong) || TextUtils.isEmpty(chuHopDong) || TextUtils.isEmpty(soLuong) || TextUtils.isEmpty(nguoiThue) || TextUtils.isEmpty(emailNguoiThue)) {
            Toast.makeText(this, "Vui lòng nhập thông tin cơ bản.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo đối tượng Contract với các giá trị nhập vào
        Contract contract = new Contract(
                -1,
                phong,
                chuHopDong,
                Integer.parseInt(soLuong),
                date,
                dateStart,
                dateEnd,
                Double.parseDouble(tienNha),
                Double.parseDouble(tienCoc),
                Double.parseDouble(internet),
                Double.parseDouble(nuocUong),
                Double.parseDouble(nuocUongTheoNguoi),
                Double.parseDouble(tienDien),
                Double.parseDouble(mayGiat),
                khac,
                nguoiThue,  // Thêm người thuê vào constructor
                emailNguoiThue  // Thêm email người thuê vào constructor
        );

        // Lưu hợp đồng vào cơ sở dữ liệu
        boolean isInserted = contractHelper.addContract(contract);

        // Thông báo kết quả
        if (isInserted) {
            Toast.makeText(this, "Tạo hợp đồng thành công!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Lỗi khi tạo hợp đồng.", Toast.LENGTH_SHORT).show();
        }
    }
}
