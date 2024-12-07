package com.scan.dyong_resident;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.scan.dyong_resident.database.ContractHelper;
import com.scan.dyong_resident.models.Contract;

public class AddContractActivity extends AppCompatActivity {

    private EditText edtPhong, edtChuHopDong, edtSoLuong, edtDate, edtDateStart, edtDateEnd, edtTienNha,
            edtTienCoc, edtInternet, edtNuocUong, edtNuocUongTheoNguoi, edtTienDien, edtMayGiat, edtKhac;
    private TextView btnCreateContract;
    private ContractHelper contractHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_contract);

        contractHelper = new ContractHelper(this);

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
        btnCreateContract = findViewById(R.id.tv_create_contract);

        btnCreateContract.setOnClickListener(v -> createContract());
    }

    private void createContract() {
        String phong = edtPhong.getText().toString().trim();
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

        if (TextUtils.isEmpty(phong) || TextUtils.isEmpty(chuHopDong) || TextUtils.isEmpty(soLuong)) {
            Toast.makeText(this, "Vui lòng nhập thông tin cơ bản.", Toast.LENGTH_SHORT).show();
            return;
        }

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
                khac
        );
        boolean isInserted = contractHelper.addContract(contract);

        if (isInserted) {
            Toast.makeText(this, "Tạo hợp đồng thành công!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Lỗi khi tạo hợp đồng.", Toast.LENGTH_SHORT).show();
        }
    }
}
