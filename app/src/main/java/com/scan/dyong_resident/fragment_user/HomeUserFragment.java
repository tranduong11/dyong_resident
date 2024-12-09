package com.scan.dyong_resident.fragment_user;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.scan.dyong_resident.R;
import com.scan.dyong_resident.database.ContractHelper;
import com.scan.dyong_resident.database.RoleHelper;
import com.scan.dyong_resident.models.Contract;

public class HomeUserFragment extends Fragment {

    private TextView txtAddress, txtStartDate, txtEndDate, txtRent, txtDeposit, txtInternet, txtWater, txt_phong;
    private String userEmail;
    private RoleHelper roleHelper;

    public HomeUserFragment() {
    }

    public static BillUserFragment newInstance() {
        return new BillUserFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_user_fragment, container, false);

        txtAddress = rootView.findViewById(R.id.txt_diaChi);
        txtStartDate = rootView.findViewById(R.id.txt_dateBatDau);
        txtEndDate = rootView.findViewById(R.id.dateKetThuc);
        txtRent = rootView.findViewById(R.id.txt_tienNha);
        txtDeposit = rootView.findViewById(R.id.txt_tienCoc);
        txtInternet = rootView.findViewById(R.id.txt_internet);
        txtWater = rootView.findViewById(R.id.txt_nuocUong);
        txt_phong = rootView.findViewById(R.id.txt_phong);

        userEmail = loadUserEmailFromPreferences();
        Log.d("HomeUserFragment", "User Email: " + userEmail);

        roleHelper = new RoleHelper(getContext());

        String userRole = roleHelper.getUserRole(userEmail);

        String phong = txt_phong.getText().toString();


        Bundle bundle = new Bundle();
        bundle.putString("room", phong);

        BillUserFragment billUserFragment = new BillUserFragment();
        billUserFragment.setArguments(bundle);

        txt_phong = rootView.findViewById(R.id.txt_phong);


        ContractHelper contractHelper = new ContractHelper(getContext());
        Contract contract = null;

        if (userRole != null && userRole.equals("admin")) {
            contract = contractHelper.getContractByEmail(userEmail);
        } else if (userRole != null && userRole.equals("user")) {
            contract = contractHelper.getContractByEmail(userEmail);
        }

        if (contract != null) {
            txtStartDate.setText(contract.getNgayBatDau());
            txtEndDate.setText(contract.getNgayKetThuc());
            txt_phong.setText(contract.getRoom());
            txtRent.setText(String.format("%.0f VND", contract.getTienNha()));
            txtDeposit.setText(String.format("%.0f VND", contract.getTienCoc()));
            txtInternet.setText(String.format("%.0f VND", contract.getInternet()));
            txtWater.setText(String.format("%.0f VND", contract.getNuocUong()));
        } else {
            txtAddress.setText("Không tìm thấy hợp đồng cho người dùng này");
        }

        return rootView;
    }

    private String loadUserEmailFromPreferences() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", getActivity().MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");  // Lấy email đã lưu
        Log.d("HomeUserFragment", "Email from SharedPreferences: " + email);  // In ra email để kiểm tra
        return email;
    }
}
