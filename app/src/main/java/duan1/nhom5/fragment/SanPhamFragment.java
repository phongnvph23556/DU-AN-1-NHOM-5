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

import duan1.nhom5.Adapter.SanPhamAdapter;
import duan1.nhom5.DAO.SanPhamDAO;
import duan1.nhom5.MainActivity;
import duan1.nhom5.R;


public class SanPhamFragment extends Fragment {
    private RecyclerView rcv_sanpham;
    private ImageView backsanpham;
    private SanPhamDAO sanPhamDAO;
    private SanPhamAdapter sanPhamAdapter;

    public static SanPhamFragment newInstance() {
        SanPhamFragment fragment = new SanPhamFragment();

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
        View v = inflater.inflate(R.layout.fragment_san_pham, container, false);
        backsanpham = v.findViewById(R.id.backsanpham);
        rcv_sanpham=v.findViewById(R.id.rcv_sanpham);
        sanPhamDAO=new SanPhamDAO(getActivity());
        sanPhamAdapter=new SanPhamAdapter(getActivity());

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rcv_sanpham.setLayoutManager(layoutManager);
        sanPhamAdapter.setData(sanPhamDAO.selectAll());
        rcv_sanpham.setAdapter(sanPhamAdapter);



        backsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}