package com.scan.dyong_resident.fragment_user;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.NumberFormat;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.scan.dyong_resident.R;
import com.scan.dyong_resident.database.BillHelper;
import com.scan.dyong_resident.models.Bill;

public class BillUserFragment extends Fragment {
    private TextView txtNgayXuat, txtTuNgay, txtDenNgay, txtSoNguoiO, txtPhong,
            txtTienPhong, txtTienDien, txtTienNuoc, txtTienMang, txtTienThangMay,
            txtTienRac, txtTongTien, txtTongSoTien, txt_tranThaiThanhToan;
    private ImageView qrCodeImage;
    private String phong = "401";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bill_user_fragment, container, false);

//        if (getArguments() != null) {
//            phong = getArguments().getString("room", "401");
//            Log.d(phong, "so phong la dong 31: "+ phong);
//        }

        txtNgayXuat = view.findViewById(R.id.txt_ngayXuat);
        txtTuNgay = view.findViewById(R.id.txt_tuNgay);
        txtDenNgay = view.findViewById(R.id.txt_denNgay);
        txtSoNguoiO = view.findViewById(R.id.txt_soNguoiO);
        txtPhong = view.findViewById(R.id.txt_phong);
        txtTienPhong = view.findViewById(R.id.txt_tienPhong);
        txtTienDien = view.findViewById(R.id.txt_tienDien);
        txtTienNuoc = view.findViewById(R.id.txt_tienNuoc);
        txtTienMang = view.findViewById(R.id.txt_tienMang);
        txtTienThangMay = view.findViewById(R.id.txt_tienThangMay);
        txtTienRac = view.findViewById(R.id.txt_tienRac);
        txtTongTien = view.findViewById(R.id.txt_tongTien);
        txtTongSoTien = view.findViewById(R.id.txt_tongSoTien);
        qrCodeImage = view.findViewById(R.id.qrCodeImage);
        txt_tranThaiThanhToan = view.findViewById(R.id.txt_tranThaiThanhToan);

        BillHelper billHelper = new BillHelper(getContext());
        Bill bill = billHelper.getBillByRoom(phong);

        if (bill != null) {
            txt_tranThaiThanhToan.setText("Hóa đơn chưa thanh toán");
            txt_tranThaiThanhToan.setTextColor(ContextCompat.getColor(getContext(), R.color.colorChuaThanhToan));
            txtNgayXuat.setText(bill.getNgayLap());
            txtTuNgay.setText(bill.getKyThanhToan());
            txtDenNgay.setText(bill.getHanThanhToan());
            txtSoNguoiO.setText(String.valueOf(bill.getSoLuong()));
            txtPhong.setText(bill.getPhong());

            txtTienPhong.setText(formatCurrency(bill.getTienPhong()));
            txtTienDien.setText(formatCurrency(bill.getTienDien()));
            txtTienNuoc.setText(formatCurrency(bill.getTienNuoc()));
            txtTienMang.setText(formatCurrency(bill.getTienMang()));
            txtTienThangMay.setText(formatCurrency(bill.getTienThangMay()));
            txtTienRac.setText(formatCurrency(bill.getTienRac()));

            double totalAmount = bill.getTienPhong() + bill.getTienDien() + bill.getTienNuoc() +
                    bill.getTienMang() + bill.getTienThangMay() + bill.getTienRac();
            txtTongTien.setText(formatCurrency(totalAmount));
            txtTongSoTien.setText(formatCurrency(totalAmount));

        }else{
            txt_tranThaiThanhToan.setText("Hóa đơn đã thanh toán");
            txt_tranThaiThanhToan.setTextColor(ContextCompat.getColor(getContext(), R.color.colorDaThanhToan));
            txtNgayXuat.setText("");
            txtTuNgay.setText("");
            txtDenNgay.setText("");
            txtSoNguoiO.setText("");
            txtPhong.setText("");

            txtTienPhong.setText("");
            txtTienDien.setText("");
            txtTienNuoc.setText("");
            txtTienMang.setText("");
            txtTienThangMay.setText("");
            txtTienRac.setText("");
            txtTongTien.setText("");
            txtTongSoTien.setText("");
        }

        return view;
    }

    private String formatCurrency(double amount) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        return numberFormat.format(amount);
    }
}


