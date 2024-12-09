package com.scan.dyong_resident.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.scan.dyong_resident.R;
import com.scan.dyong_resident.models.Room;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private List<Room> roomList;

    public RoomAdapter(List<Room> roomList) {
        this.roomList = roomList;
    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RoomViewHolder holder, int position) {
        Room room = roomList.get(position);

        holder.txtTenPhong.setText(room.getTenPhong());
        holder.txtTrangThai.setText(room.getTrangThai());
        holder.txtViTriSoTang.setText(room.getViTriSoTang());

        holder.icUpdate.setOnClickListener(v -> {
        });

        holder.icDelete.setOnClickListener(v -> {
        });
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenPhong, txtTrangThai, txtViTriSoTang;
        ImageView icUpdate, icDelete;

        public RoomViewHolder(View itemView) {
            super(itemView);
            txtTenPhong = itemView.findViewById(R.id.txt_tenPhong);
            txtTrangThai = itemView.findViewById(R.id.txt_trangThai);
            txtViTriSoTang = itemView.findViewById(R.id.txt_viTriSoTang);
            icUpdate = itemView.findViewById(R.id.ic_update);
            icDelete = itemView.findViewById(R.id.ic_delete);
        }
    }
}

