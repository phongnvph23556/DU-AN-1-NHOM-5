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


public class NhanVienFragment extends Fragment {
    ImageView backnhanvien;


    public NhanVienFragment() {
        // Required empty public constructor
    }

    public static NhanVienFragment newInstance() {
        NhanVienFragment fragment = new NhanVienFragment();

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
        View v= inflater.inflate(R.layout.fragment_nhan_vien, container, false);
        backnhanvien=v.findViewById(R.id.backnhanvien);
        backnhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}