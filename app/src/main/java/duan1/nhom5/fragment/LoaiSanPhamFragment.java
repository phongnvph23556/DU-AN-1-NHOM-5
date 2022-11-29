package duan1.nhom5.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import duan1.nhom5.Adapter.LoaiSanPhamAdapter;
import duan1.nhom5.DAO.LoaiSanPhamDAO;
import duan1.nhom5.Entity.LoaiSanPham;
import duan1.nhom5.MainActivity;
import duan1.nhom5.R;


public class LoaiSanPhamFragment extends Fragment {
    private LoaiSanPhamAdapter loaiSanPhamAdapter;
    private LoaiSanPhamDAO loaiSanPhamDAO;
    private RecyclerView rcv_loaisp;
    ImageView backloaisp, add_loaisp, cancel_loaisp;
    EditText edt_maloaisp, edt_tenloaisp, edt_namsx, edt_hangsx;
    Button btn_themloaisp, btnhuythemloaisp;
    List<LoaiSanPham> list=new ArrayList<>();


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
        View v = inflater.inflate(R.layout.fragment_loai_san_pham, container, false);
        backloaisp = v.findViewById(R.id.backloaisp);
        loaiSanPhamAdapter = new LoaiSanPhamAdapter(getActivity());
        loaiSanPhamDAO = new LoaiSanPhamDAO(getActivity());
        rcv_loaisp = v.findViewById(R.id.rcv_loaisp);
        add_loaisp = v.findViewById(R.id.img_addloaisp);


        add_loaisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_ThemLoaisp();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcv_loaisp.setLayoutManager(layoutManager);
        loaiSanPhamAdapter.setData(loaiSanPhamDAO.selectAll());
        rcv_loaisp.setAdapter(loaiSanPhamAdapter);


        backloaisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return v;

    }


    public void dialog_ThemLoaisp() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_themloaisp);
        dialog.show();

        btn_themloaisp = dialog.findViewById(R.id.btn_themloaisp);
        btnhuythemloaisp = dialog.findViewById(R.id.btn_huythemloaisp);
        btnhuythemloaisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_tenloaisp.setText("");
                edt_namsx.setText("");
                edt_hangsx.setText("");
            }
        });
        edt_maloaisp = dialog.findViewById(R.id.edt_maloaisp);
        edt_tenloaisp = dialog.findViewById(R.id.edt_tenloaisp);
        edt_namsx = dialog.findViewById(R.id.edt_namsx);
        edt_hangsx = dialog.findViewById(R.id.edt_hangsx);
        cancel_loaisp = dialog.findViewById(R.id.cancelthemloaisp);

        cancel_loaisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_themloaisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//             int maloaisp= Integer.parseInt(edt_maloaisp.getText().toString().trim());
                edt_maloaisp.setEnabled(true);


                String tenloaisp = edt_tenloaisp.getText().toString().trim();
                String namsx = edt_namsx.getText().toString().trim();
                String hangsx = edt_hangsx.getText().toString().trim();
                if (tenloaisp.isEmpty() || namsx.isEmpty() || hangsx.isEmpty()) {
                    Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                } else {
                    if (loaiSanPhamDAO.insert(new LoaiSanPham(tenloaisp, namsx, hangsx))) {
                        Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        list.clear();
                        loaiSanPhamAdapter.setData(loaiSanPhamDAO.selectAll());
                    } else {
                        Toast.makeText(getActivity(), "Thêm thất bại rồi", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });


    }
}