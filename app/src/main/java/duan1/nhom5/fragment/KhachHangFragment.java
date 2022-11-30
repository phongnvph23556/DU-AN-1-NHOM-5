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

import duan1.nhom5.Adapter.KhachHangAdapter;
import duan1.nhom5.Adapter.LoaiSanPhamAdapter;
import duan1.nhom5.DAO.KhachHangDAO;
import duan1.nhom5.DAO.LoaiSanPhamDAO;
import duan1.nhom5.Entity.KhachHang;
import duan1.nhom5.Entity.LoaiSanPham;
import duan1.nhom5.MainActivity;
import duan1.nhom5.R;


public class KhachHangFragment extends Fragment {
    private ImageView backkhachhang;
    private RecyclerView rcv_khachhang;
    private KhachHangAdapter khachHangAdapter;
    private KhachHangDAO khachHangDAO;
    ArrayList<KhachHang> list;
    private KhachHang khachHang;
    ImageView cancel_khachhang;
    EditText edt_makh, edt_tenkh, edt_namsinhkh, edt_diachikh, edt_sdtkh;
    Button btn_themkh, btn_huythemkh;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_khach_hang, container, false);
        backkhachhang = v.findViewById(R.id.backkhachhang);
        rcv_khachhang = v.findViewById(R.id.rcv_khachhang);
        khachHangDAO = new KhachHangDAO(getActivity());
        capNhatRCV();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcv_khachhang.setLayoutManager(layoutManager);


        backkhachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    public void capNhatRCV() {
        list = (ArrayList<KhachHang>) khachHangDAO.selectAll();
        khachHangAdapter = new KhachHangAdapter(getActivity(), list, this);
        rcv_khachhang.setAdapter(khachHangAdapter);
    }

    public void dialog_ThemLoaisp() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_themkhachhang);

        btn_themkh = dialog.findViewById(R.id.btn_themkh);
        btn_huythemkh = dialog.findViewById(R.id.btnhuythemkh);

        btn_huythemkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_tenkh.setText("");
                edt_namsinhkh.setText("");
                edt_diachikh.setText("");
                edt_sdtkh.setText("");
            }
        });
        edt_makh = dialog.findViewById(R.id.edtMaKh);
        edt_tenkh = dialog.findViewById(R.id.edtHoTenKH);
        edt_namsinhkh = dialog.findViewById(R.id.edtNS);
        edt_diachikh = dialog.findViewById(R.id.edtDiachiKH);
        edt_sdtkh = dialog.findViewById(R.id.edtSDT);
        cancel_khachhang=dialog.findViewById(R.id.img_canceladdkh);

        //tắt dialog thêm
        cancel_khachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        edt_makh.setEnabled(false);//tắt nhập với mã loại sp


        btn_themkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khachHang = new KhachHang();
                khachHang.setHoTenKH(edt_tenkh.getText().toString());
                khachHang.setNamSinhKH(Integer.parseInt(edt_namsinhkh.getText().toString()));
                khachHang.setDiaChiKH(edt_diachikh.getText().toString());
                khachHang.setSDT(Integer.parseInt(edt_sdtkh.getText().toString()));



                    if (khachHangDAO.insert(khachHang) > 0) {
                        Toast.makeText(getActivity(), "Thêm thành công nha", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Thêm thất bại nha", Toast.LENGTH_SHORT).show();
                    }

                capNhatRCV();
                dialog.cancel();
            }
        });
        dialog.show();
    }
}