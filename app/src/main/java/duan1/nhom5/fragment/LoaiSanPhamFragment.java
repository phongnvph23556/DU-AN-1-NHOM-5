package duan1.nhom5.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Instrumentation;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import duan1.nhom5.Adapter.KhachHangAdapter;
import duan1.nhom5.Adapter.LoaiSanPhamAdapter;
import duan1.nhom5.DAO.KhachHangDAO;
import duan1.nhom5.DAO.LoaiSanPhamDAO;
import duan1.nhom5.Entity.KhachHang;
import duan1.nhom5.Entity.LoaiSanPham;
import duan1.nhom5.Entity.SanPham;
import duan1.nhom5.MainActivity;
import duan1.nhom5.R;


public class LoaiSanPhamFragment extends Fragment {
    SearchView searchView;
    private ImageView backloaisp;
    private RecyclerView rcv_loaisp;
    private LoaiSanPhamDAO loaiSanPhamDAO;
    private LoaiSanPhamAdapter loaiSanPhamAdapter;
    List<LoaiSanPham> list;
    ImageView imgadd_loaisp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_loai_san_pham, container, false);
        searchView = v.findViewById(R.id.search_viewloaisp);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

        backloaisp = v.findViewById(R.id.backloaisp);
        rcv_loaisp = v.findViewById(R.id.rcv_loaisp);
        loaiSanPhamDAO = new LoaiSanPhamDAO(getActivity());
        imgadd_loaisp = v.findViewById(R.id.img_addloaisp);
        list = loaiSanPhamDAO.selectAll();


        imgadd_loaisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_themLoaiSP();
            }
        });


        LinearLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        rcv_loaisp.setLayoutManager(layoutManager);
        loaiSanPhamAdapter = new LoaiSanPhamAdapter(getActivity(), list);
        rcv_loaisp.setAdapter(loaiSanPhamAdapter);


        //về màn hình chính
        backloaisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    private void filterList(String text) {
        List<LoaiSanPham> filterlist = new ArrayList<>();
        for (LoaiSanPham loaiSanPham : list) {
            if (loaiSanPham.getTenLoai().toLowerCase().contains(text.toLowerCase())) {
                filterlist.add(loaiSanPham);
            }
        }
        if (filterlist.isEmpty()) {

        } else {
            loaiSanPhamAdapter.setFill_List(filterlist);
        }
    }

    public void dialog_themLoaiSP() {


        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_themloaisp);
        dialog.show();


        EditText tenloaisp = dialog.findViewById(R.id.edt_tenloaisp);
        EditText namsx = dialog.findViewById(R.id.edt_namsx);
        EditText hangsx = dialog.findViewById(R.id.edt_hangsx);

        Button them = dialog.findViewById(R.id.btn_themloaisp);
        Button huy = dialog.findViewById(R.id.btn_huythemloaisp);
        ImageView cancelkh = dialog.findViewById(R.id.cancelthemloaisp);

        cancelkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenloaisp.setText("");
                namsx.setText("");
                hangsx.setText("");
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = tenloaisp.getText().toString().trim();
                String nam_sx = namsx.getText().toString().trim();
                String hang_sx = hangsx.getText().toString().trim();

                if (tenloai.isEmpty() || nam_sx.isEmpty() || hang_sx.isEmpty()) {
                    Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (loaiSanPhamDAO.insert(new LoaiSanPham(tenloai, nam_sx, hang_sx))) {
                        Toast.makeText(getActivity(), "Thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        list.clear();
                        list.addAll(loaiSanPhamDAO.selectAll());
                        loaiSanPhamAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getActivity(), "Thất bại", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });

    }


}