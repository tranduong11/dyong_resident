package com.scan.dyong_resident.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scan.dyong_resident.R;
import com.scan.dyong_resident.models.Contract;

import java.util.List;

public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.ContractViewHolder> {

    private List<Contract> contractList;

    public ContractAdapter(List<Contract> contractList) {
        this.contractList = contractList;
    }

    @NonNull
    @Override
    public ContractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_contract, parent, false);
        return new ContractViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContractViewHolder holder, int position) {
        Contract contract = contractList.get(position);
        holder.tvRoom.setText("Phòng: " + contract.getRoom());
        holder.tvOwner.setText("Chủ hợp đồng: " + contract.getOwner());
        holder.tvQuantity.setText("Số lượng: " + contract.getQuantity() + " người");

        holder.ivEdit.setOnClickListener(v -> {
        });

        holder.ivDelete.setOnClickListener(v -> {
        });
    }

    @Override
    public int getItemCount() {
        return contractList.size();
    }

    public static class ContractViewHolder extends RecyclerView.ViewHolder {
        TextView tvRoom, tvOwner, tvQuantity;
        ImageView ivEdit, ivDelete;

        public ContractViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoom = itemView.findViewById(R.id.tv_room);
            tvOwner = itemView.findViewById(R.id.tv_owner);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
            ivEdit = itemView.findViewById(R.id.iv_edit);
            ivDelete = itemView.findViewById(R.id.iv_delete);
        }
    }
}

