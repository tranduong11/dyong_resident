package com.scan.dyong_resident.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scan.dyong_resident.Adapter.EmailAdapter;
import com.scan.dyong_resident.Adapter.ReflectionAdapter;
import com.scan.dyong_resident.R;
import com.scan.dyong_resident.database.ReflectionHelper;
import com.scan.dyong_resident.models.Email;
import com.scan.dyong_resident.models.Reflection;

import java.util.ArrayList;
import java.util.List;

public class EmailFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReflectionAdapter adapter;
    private List<Reflection> reflectionList;

    public EmailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_email, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ReflectionHelper dbHelper = new ReflectionHelper(getContext());
        reflectionList = dbHelper.getAllReflections();

        adapter = new ReflectionAdapter(reflectionList);
        recyclerView.setAdapter(adapter);

        return rootView;

    }
}


