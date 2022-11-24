package duan1.nhom5.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import duan1.nhom5.MainActivity;
import duan1.nhom5.R;


public class LoaiSanPhamFragment extends Fragment {
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