package com.scan.dyong_resident.fragment_user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scan.dyong_resident.Adapter.ReflectionAdapter;
import com.scan.dyong_resident.R;
import com.scan.dyong_resident.database.ReflectionHelper;
import com.scan.dyong_resident.models.Reflection;

import java.util.List;

public class ReflectionFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReflectionAdapter adapter;
    private List<Reflection> reflectionList;

    public ReflectionFragment() {
    }

    public static ReflectionFragment newInstance() {
        return new ReflectionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reflection, container, false);

        // Ánh xạ RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerViewPhanAnh);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Lấy dữ liệu từ cơ sở dữ liệu và cập nhật RecyclerView
        ReflectionHelper dbHelper = new ReflectionHelper(getContext());
        reflectionList = dbHelper.getAllReflections();

        // Cập nhật Adapter cho RecyclerView
        adapter = new ReflectionAdapter(reflectionList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
