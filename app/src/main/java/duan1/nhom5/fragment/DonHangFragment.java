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

import duan1.nhom5.Adapter.DonHangAdapter;
import duan1.nhom5.DAO.DonHangDAO;
import duan1.nhom5.DAO.KhachHangDAO;
import duan1.nhom5.MainActivity;
import duan1.nhom5.R;

public class DonHangFragment extends Fragment {
    private RecyclerView rcv_donhang;
    ImageView backdonhang, themdonhang;
    private DonHangAdapter donHangAdapter;
    private DonHangDAO donHangDAO;


    public static DonHangFragment newInstance() {
        DonHangFragment fragment = new DonHangFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_don_hang, container, false);
        backdonhang = v.findViewById(R.id.backdonhang);
        rcv_donhang = v.findViewById(R.id.rcv_donhang);
        themdonhang = v.findViewById(R.id.img_themdonhang);
        donHangAdapter = new DonHangAdapter(getActivity());
        donHangDAO = new DonHangDAO(getActivity());


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcv_donhang.setLayoutManager(layoutManager);

        donHangAdapter.setData(donHangDAO.selectAll());
        rcv_donhang.setAdapter(donHangAdapter);


        backdonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return v;


    }
}