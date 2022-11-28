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

import duan1.nhom5.Adapter.LoaiSanPhamAdapter;
import duan1.nhom5.DAO.LoaiSanPhamDAO;
import duan1.nhom5.MainActivity;
import duan1.nhom5.R;


public class LoaiSanPhamFragment extends Fragment {
    private LoaiSanPhamAdapter loaiSanPhamAdapter;
    private LoaiSanPhamDAO loaiSanPhamDAO;
    private RecyclerView rcv_loaisp;
    ImageView backloaisp;

    public LoaiSanPhamFragment() {
        // Required empty public constructor
    }

    public static LoaiSanPhamFragment newInstance() {
        LoaiSanPhamFragment fragment = new LoaiSanPhamFragment();

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
        View v= inflater.inflate(R.layout.fragment_loai_san_pham, container, false);
        backloaisp=v.findViewById(R.id.backloaisp);
        loaiSanPhamAdapter=new LoaiSanPhamAdapter(getActivity());
        loaiSanPhamDAO=new LoaiSanPhamDAO(getActivity());
        rcv_loaisp=v.findViewById(R.id.rcv_loaisp);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rcv_loaisp.setLayoutManager(layoutManager);
        loaiSanPhamAdapter.setData(loaiSanPhamDAO.selectAll());
        rcv_loaisp.setAdapter(loaiSanPhamAdapter);



        backloaisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}