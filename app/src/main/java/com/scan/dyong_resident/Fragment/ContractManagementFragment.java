package com.scan.dyong_resident.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scan.dyong_resident.Adapter.ContractAdapter;
import com.scan.dyong_resident.ContractDetail;
import com.scan.dyong_resident.R;
import com.scan.dyong_resident.database.ContractHelper;
import com.scan.dyong_resident.models.Contract;

import java.util.List;

public class ContractManagementFragment extends Fragment implements ContractAdapter.OnContractActionListener {

    private RecyclerView recyclerView;
    private ContractAdapter adapter;
    private List<Contract> contractList;
    private ContractHelper contractHelper;

    public ContractManagementFragment() {}

    public static ContractManagementFragment newInstance() {
        return new ContractManagementFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contract_management, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewContract);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        contractHelper = new ContractHelper(getContext());

        contractList = contractHelper.getAllContracts();

        // Khởi tạo Adapter và set Listener cho nó
        adapter = new ContractAdapter(contractList, this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onEditClick(Contract contract) {
        showUpdateDialog(contract);
    }

    private void showUpdateDialog(Contract contract) {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_update_contract, null);

        EditText edtPhong = dialogView.findViewById(R.id.edt_phong);
        EditText edtChuHopDong = dialogView.findViewById(R.id.edt_chuHopDong);
        EditText edtSoLuong = dialogView.findViewById(R.id.edt_soLuong);
        EditText edtDate = dialogView.findViewById(R.id.edt_date);
        EditText edtDateStart = dialogView.findViewById(R.id.edt_date_start);
        EditText edtDateEnd = dialogView.findViewById(R.id.edt_date_end);
        EditText edtTienNha = dialogView.findViewById(R.id.edt_tienNha);
        EditText edtTienCoc = dialogView.findViewById(R.id.edt_tienCoc);
        EditText edtInternet = dialogView.findViewById(R.id.edt_Internet);
        EditText edtNuocUong = dialogView.findViewById(R.id.edt_nuocUong);
        EditText edtNuocUongTheoNguoi = dialogView.findViewById(R.id.edt_nuocUongTheoNguoi);
        EditText edtTienDien = dialogView.findViewById(R.id.edt_tienDien);
        EditText edtMayGiat = dialogView.findViewById(R.id.edt_mayGiat);
        EditText edtKhac = dialogView.findViewById(R.id.edt_khac);

        edtPhong.setText(contract.getRoom());
        edtChuHopDong.setText(contract.getOwner());
        edtSoLuong.setText(String.valueOf(contract.getQuantity()));
        edtDate.setText(contract.getNgayKy());
        edtDateStart.setText(contract.getNgayBatDau());
        edtDateEnd.setText(contract.getNgayKetThuc());
        edtTienNha.setText(String.valueOf(contract.getTienNha()));
        edtTienCoc.setText(String.valueOf(contract.getTienCoc()));
        edtInternet.setText(String.valueOf(contract.getInternet()));
        edtNuocUong.setText(String.valueOf(contract.getNuocUong()));
        edtNuocUongTheoNguoi.setText(String.valueOf(contract.getNuocUongTheoNguoi()));
        edtTienDien.setText(String.valueOf(contract.getTienDien()));
        edtMayGiat.setText(String.valueOf(contract.getMayGiat()));
        edtKhac.setText(contract.getKhac());

        new AlertDialog.Builder(getContext())
                .setTitle("Cập nhật hợp đồng")
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
                            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        contract.setRoom(phong);
                        contract.setOwner(chuHopDong);
                        contract.setQuantity(Integer.parseInt(soLuong));
                        contract.setNgayKy(date);
                        contract.setNgayBatDau(dateStart);
                        contract.setNgayKetThuc(dateEnd);
                        contract.setTienNha(Double.parseDouble(tienNha));
                        contract.setTienCoc(Double.parseDouble(tienCoc));
                        contract.setInternet(Double.parseDouble(internet));
                        contract.setNuocUong(Double.parseDouble(nuocUong));
                        contract.setNuocUongTheoNguoi(Double.parseDouble(nuocUongTheoNguoi));
                        contract.setTienDien(Double.parseDouble(tienDien));
                        contract.setMayGiat(Double.parseDouble(mayGiat));
                        contract.setKhac(khac);

                        boolean isUpdated = contractHelper.updateContract(contract);
                        if (isUpdated) {
                            Toast.makeText(getContext(), "Cập nhật hợp đồng thành công!", Toast.LENGTH_SHORT).show();
                            contractList.set(contractList.indexOf(contract), contract);
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), "Cập nhật hợp đồng thất bại.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    @Override
    public void onDeleteClick(Contract contract) {
        new AlertDialog.Builder(getContext())
                .setMessage("Bạn có chắc chắn muốn xóa hợp đồng này?")
                .setCancelable(false)
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        contractHelper.deleteContract(contract);

                        contractList.remove(contract);
                        adapter.notifyDataSetChanged();

                        Toast.makeText(getContext(), "Hợp đồng đã được xóa.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    @Override
    public void onContractClick(Contract contract) {
        Intent intent = new Intent(getActivity(), ContractDetail.class);
        intent.putExtra("contract_detail", contract);
        startActivity(intent);
    }
}
