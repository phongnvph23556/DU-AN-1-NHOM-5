package duan1.nhom5.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import duan1.nhom5.Adapter.KhachHangAdapter;
import duan1.nhom5.Adapter.LoaiSanPhamAdapter;
import duan1.nhom5.Adapter.NhanVienAdapter;
import duan1.nhom5.DAO.KhachHangDAO;
import duan1.nhom5.DAO.LoaiSanPhamDAO;
import duan1.nhom5.DAO.NhanVienDAO;
import duan1.nhom5.Entity.KhachHang;
import duan1.nhom5.Entity.LoaiSanPham;
import duan1.nhom5.Entity.NhanVien;
import duan1.nhom5.MainActivity;
import duan1.nhom5.R;


public class KhachHangFragment extends Fragment {
    private ImageView backkhachhang;
    private RecyclerView rcv_khachhang;
    private KhachHangDAO khachHangDAO;
    private KhachHangAdapter khachHangAdapter;
    List<KhachHang> list;
    ImageView imgadd_kh, img_cancelkh;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_khach_hang, container, false);
        backkhachhang = v.findViewById(R.id.backkhachhang);
        rcv_khachhang = v.findViewById(R.id.rcv_khachhang);
        khachHangDAO = new KhachHangDAO(getActivity());
        imgadd_kh = v.findViewById(R.id.img_addkh);
        list = khachHangDAO.selectAll();


        imgadd_kh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_themKH();
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcv_khachhang.setLayoutManager(layoutManager);
        khachHangAdapter = new KhachHangAdapter(getActivity(), list);
        rcv_khachhang.setAdapter(khachHangAdapter);


        //về màn hình chính
        backkhachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    public void dialog_themKH() {


        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_themkhachhang);
        dialog.show();

        EditText name = dialog.findViewById(R.id.edtHoTenKH);
        EditText date = dialog.findViewById(R.id.edtNS);
        EditText address = dialog.findViewById(R.id.edtDiachiKH);
        EditText sdt = dialog.findViewById(R.id.edtSDTKH);
        Button them = dialog.findViewById(R.id.btn_themkh);
        Button huy = dialog.findViewById(R.id.btnhuythemkh);
        ImageView cancelkh = dialog.findViewById(R.id.img_cancelkh);

        cancelkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        date.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                datePickerDialog.show();
            }
        });

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                date.setText("");
                address.setText("");
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = name.getText().toString().trim();
                String ngay = date.getText().toString().trim();
                String diachi = address.getText().toString().trim();
                String sodt = sdt.getText().toString().trim();
                if (ten.isEmpty() || ngay.isEmpty() || diachi.isEmpty() || sodt.isEmpty()) {
                    Toast.makeText(getActivity(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (khachHangDAO.insert(new KhachHang(ten, ngay, diachi, sodt))) {
                        Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        list.clear();
                        list.addAll(khachHangDAO.selectAll());
                        khachHangAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });

    }


}
