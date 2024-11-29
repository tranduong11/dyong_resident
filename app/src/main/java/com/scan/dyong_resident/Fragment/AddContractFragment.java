package com.scan.dyong_resident.Fragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.scan.dyong_resident.R;
import com.scan.dyong_resident.database.ContractHelper;

public class AddContractFragment extends Fragment {

    private EditText edtPhong, edtDate, edtDateStart, edtDateEnd, edtNote, edtTienThue, edtChuKy, edtTinhTien,
            edtTienCoc, edtKhuyenMai, edtSoThang, edtTienGiam, edtKhac;

    private Button btn_them_hop_dong;

    public AddContractFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_contract, container, false);

        // Khởi tạo các EditText
        edtPhong = view.findViewById(R.id.edt_phong);
        edtDate = view.findViewById(R.id.edt_date);
        edtDateStart = view.findViewById(R.id.edt_date_start);
        edtDateEnd = view.findViewById(R.id.edt_date_end);
        edtNote = view.findViewById(R.id.edt_note);
        edtTienThue = view.findViewById(R.id.edt_tienThue);
        edtChuKy = view.findViewById(R.id.edt_chu_ky);
        edtTinhTien = view.findViewById(R.id.edt_tinhTien);
        edtTienCoc = view.findViewById(R.id.edt_tienCoc);
        edtKhuyenMai = view.findViewById(R.id.edt_khuyenMai);
        edtSoThang = view.findViewById(R.id.edt_soThang);
        edtTienGiam = view.findViewById(R.id.edt_tienGiam);
        edtKhac = view.findViewById(R.id.edt_khac);

        View btnCreateContract = view.findViewById(R.id.btn_them_hop_dong);
        btnCreateContract.setOnClickListener(v -> createContract());

        return view;
    }

    private void createContract() {
        // Lấy giá trị từ các EditText
        String phong = edtPhong.getText().toString();
        String date = edtDate.getText().toString();
        String startDate = edtDateStart.getText().toString();
        String endDate = edtDateEnd.getText().toString();
        String note = edtNote.getText().toString();
        String tienThue = edtTienThue.getText().toString();
        String chuKy = edtChuKy.getText().toString();
        String tinhTien = edtTinhTien.getText().toString();
        String tienCoc = edtTienCoc.getText().toString();
        String khuyenMai = edtKhuyenMai.getText().toString();
        String soThang = edtSoThang.getText().toString();
        String tienGiam = edtTienGiam.getText().toString();
        String khac = edtKhac.getText().toString();

        // Kiểm tra xem các trường có rỗng không
        if (phong.isEmpty() || date.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo ContentValues để lưu vào SQLite
        ContentValues values = new ContentValues();
        values.put(ContractHelper.COLUMN_ROOM, phong);
        values.put(ContractHelper.COLUMN_DATE, date);
        values.put(ContractHelper.COLUMN_START_DATE, startDate);
        values.put(ContractHelper.COLUMN_END_DATE, endDate);
        values.put(ContractHelper.COLUMN_NOTE, note);
        values.put(ContractHelper.COLUMN_RENT, tienThue);
        values.put(ContractHelper.COLUMN_PAYMENT_CYCLE, chuKy);
        values.put(ContractHelper.COLUMN_START_PAYMENT, tinhTien);
        values.put(ContractHelper.COLUMN_DEPOSIT, tienCoc);
        values.put(ContractHelper.COLUMN_PROMO, khuyenMai);
        values.put(ContractHelper.COLUMN_PROMO_MONTHS, soThang);
        values.put(ContractHelper.COLUMN_DISCOUNT, tienGiam);
        values.put(ContractHelper.COLUMN_OTHER, khac);

        ContractHelper dbHelper = new ContractHelper(getContext());
        dbHelper.addContract(values);

        // Thông báo thành công
        Toast.makeText(getContext(), "Hợp đồng đã được tạo!", Toast.LENGTH_SHORT).show();
    }
}
