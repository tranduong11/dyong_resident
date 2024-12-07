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

import com.scan.dyong_resident.Adapter.BillAdapter;
import com.scan.dyong_resident.BillDetail;
import com.scan.dyong_resident.R;
import com.scan.dyong_resident.database.BillHelper;
import com.scan.dyong_resident.models.Bill;

import java.util.List;

public class BillFragment extends Fragment  {

    private RecyclerView recyclerView;
    private BillAdapter adapter;
    private List<Bill> billList;
    private BillHelper billHelper;

    public BillFragment() {}

    public static BillFragment newInstance() {
        return new BillFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bill_fragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewBill);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        billHelper = new BillHelper(getContext());

        billList = billHelper.getAllBills();

        adapter = new BillAdapter(billList, this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void onEditClick(Bill bill) {
        showUpdateDialog(bill);
    }

    private void showUpdateDialog(Bill bill) {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_update_bill, null);

        EditText edtPhong = dialogView.findViewById(R.id.edt_phong);
        EditText edtChuHopDong = dialogView.findViewById(R.id.edt_chuHopDong);
        EditText edtKyThanhToan = dialogView.findViewById(R.id.edt_kyThanhToan);
        EditText edtNgayLap = dialogView.findViewById(R.id.edt_ngayLap);
        EditText edtHanThanhToan = dialogView.findViewById(R.id.edt_hanThanhToan);
        EditText edtSoLuong = dialogView.findViewById(R.id.edt_soLuong);
        EditText edtTienPhong = dialogView.findViewById(R.id.edt_tienPhong);
        EditText edtTienDien = dialogView.findViewById(R.id.edt_tienDien);
        EditText edtTienNuoc = dialogView.findViewById(R.id.edt_tienNuoc);
        EditText edtTienMang = dialogView.findViewById(R.id.edt_tienMang);
        EditText edtTienThangMay = dialogView.findViewById(R.id.edt_tienThangMay);
        EditText edtTienRac = dialogView.findViewById(R.id.edt_tienRac);

        edtPhong.setText(bill.getPhong());
        edtChuHopDong.setText(bill.getChuHopDong());
        edtKyThanhToan.setText(bill.getKyThanhToan());
        edtNgayLap.setText(bill.getNgayLap());
        edtHanThanhToan.setText(bill.getHanThanhToan());
        edtSoLuong.setText(String.valueOf(bill.getSoLuong()));
        edtTienPhong.setText(String.valueOf(bill.getTienPhong()));
        edtTienDien.setText(String.valueOf(bill.getTienDien()));
        edtTienNuoc.setText(String.valueOf(bill.getTienNuoc()));
        edtTienMang.setText(String.valueOf(bill.getTienMang()));
        edtTienThangMay.setText(String.valueOf(bill.getTienThangMay()));
        edtTienRac.setText(String.valueOf(bill.getTienRac()));

        new AlertDialog.Builder(getContext())
                .setTitle("Cập nhật hóa đơn")
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String phong = edtPhong.getText().toString().trim();
                        String chuHopDong = edtChuHopDong.getText().toString().trim();
                        String kyThanhToan = edtKyThanhToan.getText().toString().trim();
                        String ngayLap = edtNgayLap.getText().toString().trim();
                        String hanThanhToan = edtHanThanhToan.getText().toString().trim();
                        String soLuong = edtSoLuong.getText().toString().trim();
                        String tienPhong = edtTienPhong.getText().toString().trim();
                        String tienDien = edtTienDien.getText().toString().trim();
                        String tienNuoc = edtTienNuoc.getText().toString().trim();
                        String tienMang = edtTienMang.getText().toString().trim();
                        String tienThangMay = edtTienThangMay.getText().toString().trim();
                        String tienRac = edtTienRac.getText().toString().trim();

                        if (TextUtils.isEmpty(phong) || TextUtils.isEmpty(chuHopDong) || TextUtils.isEmpty(kyThanhToan)) {
                            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        bill.setPhong(phong);
                        bill.setChuHopDong(chuHopDong);
                        bill.setKyThanhToan(kyThanhToan);
                        bill.setNgayLap(ngayLap);
                        bill.setHanThanhToan(hanThanhToan);
                        bill.setSoLuong(Integer.parseInt(soLuong));
                        bill.setTienPhong(Double.parseDouble(tienPhong));
                        bill.setTienDien(Double.parseDouble(tienDien));
                        bill.setTienNuoc(Double.parseDouble(tienNuoc));
                        bill.setTienMang(Double.parseDouble(tienMang));
                        bill.setTienThangMay(Double.parseDouble(tienThangMay));
                        bill.setTienRac(Double.parseDouble(tienRac));

                        boolean isUpdated = billHelper.updateBill(bill);
                        if (isUpdated) {
                            Toast.makeText(getContext(), "Cập nhật hóa đơn thành công!", Toast.LENGTH_SHORT).show();
                            billList.set(billList.indexOf(bill), bill);
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), "Cập nhật hóa đơn thất bại.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    public void onDeleteClick(Bill bill) {
        new AlertDialog.Builder(getContext())
                .setMessage("Bạn có chắc chắn muốn xóa hóa đơn này?")
                .setCancelable(false)
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        billHelper.deleteBill(bill);

                        billList.remove(bill);
                        adapter.notifyDataSetChanged();

                        Toast.makeText(getContext(), "Hóa đơn đã được xóa.", Toast.LENGTH_SHORT).show();
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

    public void onBillClick(Bill bill) {
        Intent intent = new Intent(getActivity(), BillDetail.class);

        // Truyền các dữ liệu của Bill thay vì đối tượng Bill
        intent.putExtra("phong", bill.getPhong());
        intent.putExtra("chuHopDong", bill.getChuHopDong());
        intent.putExtra("kyThanhToan", bill.getKyThanhToan());
        intent.putExtra("ngayLap", bill.getNgayLap());
        intent.putExtra("hanThanhToan", bill.getHanThanhToan());
        intent.putExtra("soLuong", bill.getSoLuong());
        intent.putExtra("tienPhong", bill.getTienPhong());
        intent.putExtra("tienDien", bill.getTienDien());
        intent.putExtra("tienNuoc", bill.getTienNuoc());
        intent.putExtra("tienMang", bill.getTienMang());
        intent.putExtra("tienThangMay", bill.getTienThangMay());
        intent.putExtra("tienRac", bill.getTienRac());

        // Start BillDetail Activity
        startActivity(intent);
    }

}
