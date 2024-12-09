package com.scan.dyong_resident.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scan.dyong_resident.Adapter.RoomAdapter;
import com.scan.dyong_resident.R;
import com.scan.dyong_resident.database.RoomHelper;
import com.scan.dyong_resident.models.Room;

import java.util.ArrayList;
import java.util.List;

public class NotPaidFragment extends Fragment {

    private RecyclerView recyclerView;
    private RoomAdapter roomAdapter;
    private RoomHelper roomHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.not_paid_fragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewNotPaidRooms);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        roomHelper = new RoomHelper(getContext());

        List<Room> allRooms = roomHelper.getAllRooms();
        List<Room> unpaidRooms = new ArrayList<>();

        for (Room room : allRooms) {
            if (room.getTrangThai().equals("Phòng còn trống")) {
                unpaidRooms.add(room);
            }
        }

        roomAdapter = new RoomAdapter(unpaidRooms);
        recyclerView.setAdapter(roomAdapter);

        return view;
    }
}
