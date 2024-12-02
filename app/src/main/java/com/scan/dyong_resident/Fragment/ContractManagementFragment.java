package com.scan.dyong_resident.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scan.dyong_resident.Adapter.ContractAdapter;
import com.scan.dyong_resident.R;
import com.scan.dyong_resident.models.Contract;

import java.util.ArrayList;
import java.util.List;

public class ContractManagementFragment extends Fragment {

    private RecyclerView recyclerView;
    private ContractAdapter adapter;
    private List<Contract> contractList;

    public ContractManagementFragment() {
    }

    public static ContractManagementFragment newInstance() {
        return new ContractManagementFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contract_management, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        contractList = new ArrayList<>();
        contractList.add(new Contract("DD0205-0102", "Nguyễn Trần Trung Duy", 4));
        contractList.add(new Contract("DD0205-0103", "Lê Văn A", 3));
        contractList.add(new Contract("DD0205-0104", "Phạm Thị B", 5));
        contractList.add(new Contract("DD0205-0105", "Trần Văn C", 2));
        contractList.add(new Contract("DD0205-0106", "Nguyễn Thị D", 6));
        contractList.add(new Contract("DD0205-0107", "Lê Văn E", 4));
        contractList.add(new Contract("DD0205-0108", "Phạm Thị F", 3));

        adapter = new ContractAdapter(contractList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
