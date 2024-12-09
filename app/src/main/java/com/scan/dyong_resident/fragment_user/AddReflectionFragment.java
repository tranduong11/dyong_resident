package com.scan.dyong_resident.fragment_user;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.scan.dyong_resident.R;
import com.scan.dyong_resident.database.ReflectionHelper;

public class AddReflectionFragment extends Fragment {

    private EditText edtTenPhong, edtViTriSoTang, edtNguoiTao, edtNoiDungPhanAnh;
    private TextView btnThemPhanAnh;
    private ReflectionHelper reflectionHelper;

    public AddReflectionFragment() {
    }

    public static AddReflectionFragment newInstance(String param1, String param2) {
        AddReflectionFragment fragment = new AddReflectionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reflectionHelper = new ReflectionHelper(getContext());  // Khởi tạo DatabaseHelper
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Ánh xạ các view
        View rootView = inflater.inflate(R.layout.add_reflection_fragment, container, false);

        edtTenPhong = rootView.findViewById(R.id.edt_tenPhong);
        edtViTriSoTang = rootView.findViewById(R.id.edt_viTriSoTang);
        edtNguoiTao = rootView.findViewById(R.id.edt_nguoiTao);
        edtNoiDungPhanAnh = rootView.findViewById(R.id.edt_noiDungPhanAnh);
        btnThemPhanAnh = rootView.findViewById(R.id.btn_themPhanAnh);

        // Xử lý sự kiện khi nhấn nút "Thêm phản ánh"
        btnThemPhanAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPhanAnh();
            }
        });

        return rootView;
    }

    // Phương thức để thêm phản ánh vào cơ sở dữ liệu
    private void addPhanAnh() {
        String tenPhong = edtTenPhong.getText().toString();
        String viTriSoTang = edtViTriSoTang.getText().toString();
        String nguoiTao = edtNguoiTao.getText().toString();
        String noiDung = edtNoiDungPhanAnh.getText().toString();

        // Kiểm tra nếu có trường nào trống
        if (tenPhong.isEmpty() || viTriSoTang.isEmpty() || nguoiTao.isEmpty() || noiDung.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lưu thông tin phản ánh vào SQLite
        SQLiteDatabase db = reflectionHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten_phong", tenPhong);
        values.put("vi_tri_so_tang", viTriSoTang);
        values.put("nguoi_tao", nguoiTao);
        values.put("noi_dung", noiDung);

        // Chèn thông tin vào bảng phản ánh
        long result = db.insert("phananh", null, values);

        if (result != -1) {
            Toast.makeText(getContext(), "Thêm phản ánh thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Lỗi khi thêm phản ánh", Toast.LENGTH_SHORT).show();
        }
    }
}
