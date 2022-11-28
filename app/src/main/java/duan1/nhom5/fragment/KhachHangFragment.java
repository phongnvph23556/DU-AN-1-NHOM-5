package duan1.nhom5.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import duan1.nhom5.Adapter.KhachHangAdapter;
import duan1.nhom5.DAO.KhachHangDAO;
import duan1.nhom5.MainActivity;
import duan1.nhom5.R;


public class KhachHangFragment extends Fragment {
    private ImageView backkhachhang;
    private RecyclerView rcv_khachhang;
    private KhachHangAdapter khachHangAdapter;
    private KhachHangDAO khachHangDAO;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_khach_hang, container, false);
        backkhachhang = v.findViewById(R.id.backkhachhang);
        rcv_khachhang = v.findViewById(R.id.rcv_khachhang);
        khachHangAdapter=new KhachHangAdapter(getActivity());
        khachHangDAO=new KhachHangDAO(getActivity());

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rcv_khachhang.setLayoutManager(layoutManager);

        khachHangAdapter.setData(khachHangDAO.selectAll());
        rcv_khachhang.setAdapter(khachHangAdapter);






















        backkhachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}