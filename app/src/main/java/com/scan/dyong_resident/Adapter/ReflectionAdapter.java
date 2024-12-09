package com.scan.dyong_resident.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.scan.dyong_resident.R;
import com.scan.dyong_resident.models.Reflection;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReflectionAdapter extends RecyclerView.Adapter<ReflectionAdapter.ReflectionViewHolder> {

    private List<Reflection> reflections;

    public ReflectionAdapter(List<Reflection> reflections) {
        this.reflections = reflections;
    }

    @Override
    public ReflectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reflection, parent, false);
        return new ReflectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReflectionViewHolder holder, int position) {
        Reflection reflection = reflections.get(position);
        holder.tenPhong.setText(reflection.getTenPhong());
        holder.viTriSoTang.setText(reflection.getViTriSoTang());
        holder.nguoiTao.setText(reflection.getNguoiTao());
        holder.noiDung.setText(reflection.getNoiDung());

        // Hiển thị ngày giờ hiện tại
        updateCurrentDateTime(holder.date);
    }

    @Override
    public int getItemCount() {
        return reflections.size();
    }

    private void updateCurrentDateTime(TextView dateTextView) {
        // Lấy ngày giờ hiện tại
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
        String currentDateTime = dateFormat.format(new Date());

        // Hiển thị lên TextView
        dateTextView.setText(currentDateTime);
    }

    public static class ReflectionViewHolder extends RecyclerView.ViewHolder {

        TextView tenPhong, viTriSoTang, nguoiTao, noiDung, date;

        public ReflectionViewHolder(View itemView) {
            super(itemView);
            tenPhong = itemView.findViewById(R.id.txt_tenPhong);
            viTriSoTang = itemView.findViewById(R.id.txt_viTriSoTang);
            nguoiTao = itemView.findViewById(R.id.txt_nguoiTao);
            noiDung = itemView.findViewById(R.id.txt_noiDung);
            date = itemView.findViewById(R.id.date);  // Ánh xạ TextView cho ngày tháng
        }
    }
}
