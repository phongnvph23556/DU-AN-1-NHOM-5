package duan1.nhom5.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import duan1.nhom5.Adapter.KhachHangAdapter;
import duan1.nhom5.Adapter.NhanVienAdapter;
import duan1.nhom5.DAO.NhanVienDAO;
import duan1.nhom5.Entity.KhachHang;
import duan1.nhom5.Entity.NhanVien;
import duan1.nhom5.Entity.SanPham;
import duan1.nhom5.MainActivity;
import duan1.nhom5.R;


public class NhanVienFragment extends Fragment {
    SearchView searchView;
    private ImageView backnhanvien;
    private RecyclerView rcv_nhanvien;
    private NhanVienDAO nhanVienDAO;
    private NhanVienAdapter nhanVienAdapter;
    List<NhanVien> list;
    ImageView imgadd_nv, img_cancelnv;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nhan_vien, container, false);

        searchView = v.findViewById(R.id.search_viewnv);
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


        backnhanvien = v.findViewById(R.id.backnhanvien);
        rcv_nhanvien = v.findViewById(R.id.rcv_nhanvien);
        nhanVienDAO = new NhanVienDAO(getActivity());
        imgadd_nv = v.findViewById(R.id.img_addnv);
        list = nhanVienDAO.selectAll();

        imgadd_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_themNV();
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcv_nhanvien.setLayoutManager(layoutManager);
        nhanVienAdapter = new NhanVienAdapter(getActivity(), list);
        rcv_nhanvien.setAdapter(nhanVienAdapter);


        backnhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    private void filterList(String text) {
        List<NhanVien> filterlist = new ArrayList<>();
        for (NhanVien nhanVien : list) {
            if (nhanVien.getHoTenNV().toLowerCase().contains(text.toLowerCase())) {
                filterlist.add(nhanVien);
            }
        }
        if (filterlist.isEmpty()) {

        } else {
            nhanVienAdapter.setFill_List(filterlist);
        }
    }


    public void dialog_themNV() {


        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_themnhanvien);
        dialog.show();

        EditText name = dialog.findViewById(R.id.edtTenNV);
        EditText date = dialog.findViewById(R.id.edtnsNV);
        EditText address = dialog.findViewById(R.id.edtDiachiNV);
        Button them = dialog.findViewById(R.id.btn_themnv);
        Button huy = dialog.findViewById(R.id.btnhuythemnv);

        img_cancelnv = dialog.findViewById(R.id.img_cancelnv);
        img_cancelnv.setOnClickListener(new View.OnClickListener() {
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
                if (ten.isEmpty() || ngay.isEmpty() || diachi.isEmpty()) {
                    Toast.makeText(getActivity(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (nhanVienDAO.insert(new NhanVien(ten, ngay, diachi))) {
                        Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        list.clear();
                        list.addAll(nhanVienDAO.selectAll());
                        nhanVienAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });

    }

}