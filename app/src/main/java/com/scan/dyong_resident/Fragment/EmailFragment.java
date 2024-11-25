package com.scan.dyong_resident.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scan.dyong_resident.Adapter.EmailAdapter;
import com.scan.dyong_resident.R;
import com.scan.dyong_resident.models.Email;

import java.util.ArrayList;
import java.util.List;

public class EmailFragment extends Fragment {

    public EmailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout cho Fragment
        View rootView = inflater.inflate(R.layout.fragment_email, container, false);

        // Thiết lập RecyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Dữ liệu mẫu
        List<Email> messages = new ArrayList<>();
        messages.add(new Email("THÔNG BÁO VỀ VIỆC ĐIỀU CHỈNH GIÁ...",
                "Kính gửi: Quý khách hàng, trước tiên...",
                "31-10-2024 15:56"));
        messages.add(new Email("THÔNG BÁO HÓA ĐƠN",
                "Bạn có hóa đơn cần thanh toán...",
                "31-10-2024 09:24"));
        messages.add(new Email("LỜI NHẮC NHỞ VỀ VIỆC PHẢN HỒI...",
                "Kính gửi: Quý cư dân Dyoung...",
                "17-10-2024 16:25"));
        messages.add(new Email("XÁC NHẬN THANH TOÁN",
                "Chúng tôi đã nhận được khoản thanh toán...",
                "05-10-2024 19:26"));

        // Gắn Adapter cho RecyclerView
        EmailAdapter adapter = new EmailAdapter(messages);
        recyclerView.setAdapter(adapter);

        return rootView;

    }
}
