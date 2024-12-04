package com.scan.dyong_resident.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.scan.dyong_resident.R;
import com.scan.dyong_resident.database.ContractHelper;
import com.scan.dyong_resident.models.Contract;

public class AddContractFragment extends Fragment {

    private EditText edtPhong, edtChuHopDong, edtSoLuong, edtDate, edtDateStart, edtDateEnd, edtTienNha,
            edtTienCoc, edtInternet, edtNuocUong, edtNuocUongTheoNguoi, edtTienDien, edtMayGiat, edtKhac;
    private TextView btnCreateContract;
    private ContractHelper contractHelper;

    public AddContractFragment() {
    }

    public static AddContractFragment newInstance(String param1, String param2) {
        AddContractFragment fragment = new AddContractFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contractHelper = new ContractHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_contract, container, false);

        edtPhong = rootView.findViewById(R.id.edt_phong);
        edtChuHopDong = rootView.findViewById(R.id.edt_chuHopDong);
        edtSoLuong = rootView.findViewById(R.id.edt_soLuong);
        edtDate = rootView.findViewById(R.id.edt_date);
        edtDateStart = rootView.findViewById(R.id.edt_date_start);
        edtDateEnd = rootView.findViewById(R.id.edt_date_end);
        edtTienNha = rootView.findViewById(R.id.edt_tienNha);
        edtTienCoc = rootView.findViewById(R.id.edt_tienCoc);
        edtInternet = rootView.findViewById(R.id.edt_Internet);
        edtNuocUong = rootView.findViewById(R.id.edt_nuocUong);
        edtNuocUongTheoNguoi = rootView.findViewById(R.id.edt_nuocUongTheoNguoi);
        edtTienDien = rootView.findViewById(R.id.edt_tienDien);
        edtMayGiat = rootView.findViewById(R.id.edt_mayGiat);
        edtKhac = rootView.findViewById(R.id.edt_khac);
        btnCreateContract = rootView.findViewById(R.id.tv_create_contract);

        btnCreateContract.setOnClickListener(v -> createContract());

        return rootView;
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
            Toast.makeText(getContext(), "Vui lòng nhập thông tin cơ bản.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo đối tượng Contract với id = -1 (id sẽ được gán tự động khi insert vào DB)
        Contract contract = new Contract(
                -1, // id = -1, sẽ được tự động gán khi thêm vào DB
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


//        Contract contract = new Contract(
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
//                khac
//        );

        boolean isInserted = contractHelper.addContract(contract);

        if (isInserted) {
            Toast.makeText(getContext(), "Tạo hợp đồng thành công!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Lỗi khi tạo hợp đồng.", Toast.LENGTH_SHORT).show();
        }
    }
}
