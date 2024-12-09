package com.scan.dyong_resident.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.scan.dyong_resident.Fragment.BillFragment;
import com.scan.dyong_resident.R;
import com.scan.dyong_resident.models.Bill;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder> {

    private List<Bill> billList;
    private BillFragment listener;

    public interface OnBillActionListener {
        void onEditClick(Bill bill);
        void onDeleteClick(Bill bill);
        void onBillClick(Bill bill);
    }

    public BillAdapter(List<Bill> billList, BillFragment listener) {
        this.billList = billList;
        this.listener = listener;
    }

    @Override
    public BillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_bill, parent, false);
        return new BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BillViewHolder holder, int position) {
        Bill bill = billList.get(position);

        holder.txtPhong.setText(bill.getPhong());
        holder.txtChuHopDong.setText(bill.getChuHopDong());
        holder.txtSoLuong.setText(String.valueOf(bill.getSoLuong()));

        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        holder.txtThanhToan.setText(format.format(calculateTotal(bill)));

        holder.icUpdate.setOnClickListener(v -> listener.onEditClick(bill));
        holder.icDelete.setOnClickListener(v -> listener.onDeleteClick(bill));

        holder.itemView.setOnClickListener(v -> listener.onBillClick(bill));
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }

    public static class BillViewHolder extends RecyclerView.ViewHolder {

        TextView txtPhong, txtChuHopDong, txtSoLuong, txtThanhToan;
        ImageView icUpdate, icDelete;

        public BillViewHolder(View itemView) {
            super(itemView);
            txtPhong = itemView.findViewById(R.id.txt_phong);
            txtChuHopDong = itemView.findViewById(R.id.txt_chuHopDong);
            txtSoLuong = itemView.findViewById(R.id.txt_soLuong);
            txtThanhToan = itemView.findViewById(R.id.txt_thanhToan);
            icUpdate = itemView.findViewById(R.id.ic_update);
            icDelete = itemView.findViewById(R.id.ic_delete);
        }
    }

    private double calculateTotal(Bill bill) {
        return bill.getTienPhong() + bill.getTienDien() + bill.getTienNuoc() +
                bill.getTienMang() + bill.getTienThangMay() + bill.getTienRac();
    }
}
