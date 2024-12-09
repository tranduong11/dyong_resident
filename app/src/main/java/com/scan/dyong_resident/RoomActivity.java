package com.scan.dyong_resident;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scan.dyong_resident.Adapter.RoomAdapter;
import com.scan.dyong_resident.database.RoomHelper;
import com.scan.dyong_resident.models.Room;
import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RoomAdapter roomAdapter;
    private RoomHelper roomHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room);

        recyclerView = findViewById(R.id.recyclerViewContract);
        roomHelper = new RoomHelper(this);

        List<Room> roomList = roomHelper.getAllRooms();

        roomAdapter = new RoomAdapter(roomList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(roomAdapter);
    }
}
