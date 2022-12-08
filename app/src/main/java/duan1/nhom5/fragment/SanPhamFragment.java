package duan1.nhom5.fragment;

import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import duan1.nhom5.Adapter.LoaiSanPhamAdapter;
import duan1.nhom5.Adapter.SanPhamAdapter;
import duan1.nhom5.DAO.LoaiSanPhamDAO;
import duan1.nhom5.DAO.SanPhamDAO;
import duan1.nhom5.Entity.LoaiSanPham;
import duan1.nhom5.Entity.SanPham;
import duan1.nhom5.MainActivity;
import duan1.nhom5.R;


public class SanPhamFragment extends Fragment {
    SearchView searchView;
    RecyclerView recyclerView;
    ImageView addsp;
    ImageView backsp;
    SanPhamDAO sanPhamDAO;
    List<SanPham> list;
    SanPhamAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_san_pham, container, false);
        searchView = view.findViewById(R.id.search_viewsp);
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


        recyclerView = view.findViewById(R.id.rcv_sanpham);
        backsp = view.findViewById(R.id.backsanpham);
        backsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        addsp = view.findViewById(R.id.img_addsp);
        sanPhamDAO = new SanPhamDAO(getActivity());
        list = new ArrayList<>();


        list.addAll(sanPhamDAO.selectAll());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SanPhamAdapter(getActivity(), list, getDSLoaisach());
        recyclerView.setAdapter(adapter);

        addsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_them();
            }
        });

        return view;
    }

    private void filterList(String text) {
        List<SanPham> filterlist = new ArrayList<>();
        for (SanPham sanPham : list) {
            if (sanPham.getTenSanPham().toLowerCase().contains(text.toLowerCase())) {
                filterlist.add(sanPham);
            }
        }
        if (filterlist.isEmpty()) {

        } else {
            adapter.setFill_List(filterlist);
        }
    }

    private void dialog_them() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(LayoutInflater.from(getActivity()).inflate(R.layout.dialog_themsanpham, null));
        AlertDialog dialog = builder.create();
        dialog.show();

        EditText tensp = dialog.findViewById(R.id.edt_tensp);
        EditText edtprice = dialog.findViewById(R.id.edt_giaban);
        Spinner spnloaisp = dialog.findViewById(R.id.spn_loaisp);
        Button btnadd = dialog.findViewById(R.id.btnthemsp);
        Button btncannel = dialog.findViewById(R.id.btnHuythemsp);

        ArrayList<HashMap<String, Object>> listHMSanPham = getDSLoaisach();
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),  // context
                listHMSanPham,                                   // list hashmap
                android.R.layout.simple_list_item_1,              // layout hiển thị
                new String[]{"tenloaisp"},                      // giá trị cần hiển thị (key trong hashmap
                new int[]{android.R.id.text1});                   // đưa giá trị cần hiển thị lên widget nào trong layout simple_list_item_1
        spnloaisp.setAdapter(adapter);

        btncannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = tensp.getText().toString().trim();
                int giaban = Integer.parseInt(edtprice.getText().toString().trim());
                HashMap<String, Object> hashMap = (HashMap<String, Object>) spnloaisp.getSelectedItem();
                int maloai = (int) hashMap.get("maloaisp");
                if (ten.isEmpty() || giaban == 0 || maloai == 0) {
                    Toast.makeText(getActivity(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (sanPhamDAO.insert(new SanPham(ten, giaban, maloai))) {
                        Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        list.clear();
                        list.addAll(sanPhamDAO.selectAll());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });
    }

    private ArrayList<HashMap<String, Object>> getDSLoaisach() {
        ArrayList<HashMap<String, Object>> listHashMap = new ArrayList<>();
        LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO(getActivity());
        List<LoaiSanPham> list = loaiSanPhamDAO.selectAll();
        for (LoaiSanPham loaiSanPham : list) {
            HashMap<String, Object> hashmap = new HashMap<>();
            hashmap.put("maloaisp", loaiSanPham.getMaLoaiSP());
            hashmap.put("tenloaisp", loaiSanPham.getTenLoai());
            listHashMap.add(hashmap);
        }
        return listHashMap;
    }

}