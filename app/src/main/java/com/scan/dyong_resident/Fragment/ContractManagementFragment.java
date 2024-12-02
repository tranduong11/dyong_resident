package com.scan.dyong_resident.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scan.dyong_resident.Adapter.ContractAdapter;
import com.scan.dyong_resident.R;
import com.scan.dyong_resident.database.ContractHelper;
import com.scan.dyong_resident.models.Contract;

import java.util.List;

public class ContractManagementFragment extends Fragment {

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

        adapter = new ContractAdapter(contractList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
