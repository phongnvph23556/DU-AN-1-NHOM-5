package duan1.nhom5.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import duan1.nhom5.Adapter.DonHangAdapter;
import duan1.nhom5.DAO.DonHangDAO;
import duan1.nhom5.DAO.KhachHangDAO;
import duan1.nhom5.DAO.NhanVienDAO;
import duan1.nhom5.DAO.SanPhamDAO;
import duan1.nhom5.Entity.DonHang;
import duan1.nhom5.Entity.KhachHang;
import duan1.nhom5.Entity.NhanVien;
import duan1.nhom5.Entity.SanPham;
import duan1.nhom5.MainActivity;
import duan1.nhom5.R;

public class DonHangFragment extends Fragment {
    SearchView searchView;
    RecyclerView recyclerView;
    ImageView imgadd_donhang;
    ImageView backdonhang;
    DonHangDAO donHangDAO;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    List<DonHang> list;
    DonHangAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_don_hang, container, false);

//        searchView = view.findViewById(R.id.search_viewdh);
//        searchView.clearFocus();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filterList(newText);
//                return false;
//            }
//        });

        recyclerView = view.findViewById(R.id.rcv_donhang);
        imgadd_donhang = view.findViewById(R.id.img_themdonhang);
        backdonhang = view.findViewById(R.id.backdonhang);
        backdonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        donHangDAO = new DonHangDAO(getActivity());
        list = new ArrayList<>();
        list.addAll(donHangDAO.selectAll());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new DonHangAdapter(getActivity(), list, getDSKhachHang(), getDSSanPham(), getDSNhanVien());
        recyclerView.setAdapter(adapter);


        imgadd_donhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_them();
            }
        });

        return view;
    }

//    private void filterList(String text) {
//        List<SanPham> filterlist = new ArrayList<>();
//        for (SanPham sanPham : list) {
//            if (sanPham.getTenSanPham().toLowerCase().contains(text.toLowerCase())) {
//                filterlist.add(sanPham);
//            }
//        }
//        if (filterlist.isEmpty()) {
//
//        } else {
//            adapter.setFill_List(filterlist);
//        }
//    }

    private void dialog_them() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(LayoutInflater.from(getActivity()).inflate(R.layout.dialog_themdonhang, null));
        AlertDialog dialog = builder.create();
        dialog.show();

        Spinner spnkhachhang = dialog.findViewById(R.id.dialog_spn_kh_donhang);
        Spinner spnsanpham = dialog.findViewById(R.id.dialog_spn_sp_dh);
        Spinner spnnhanvien = dialog.findViewById(R.id.dialog_spn_nv_dh);
        TextView tvtienban = dialog.findViewById(R.id.dialog_tvtienban_dh);
        Button btnthem = dialog.findViewById(R.id.dialog_themdh);
        Button btnhuy = dialog.findViewById(R.id.dialog_huythemdh);

        ArrayList<HashMap<String, Object>> listHMKhachHang = getDSKhachHang();
        SimpleAdapter adapterKhachhang = new SimpleAdapter(getActivity(),
                listHMKhachHang,
                android.R.layout.simple_list_item_1,
                new String[]{"HoTenKH"},
                new int[]{android.R.id.text1});
        spnkhachhang.setAdapter(adapterKhachhang);

        ArrayList<HashMap<String, Object>> listHMSanPham = getDSSanPham();
        SimpleAdapter adapterSanpham = new SimpleAdapter(getActivity(),
                listHMSanPham,
                android.R.layout.simple_list_item_1,
                new String[]{"TenSanPham"},
                new int[]{android.R.id.text1});
        spnsanpham.setAdapter(adapterSanpham);

        ArrayList<HashMap<String, Object>> listHMNhanVien = getDSNhanVien();
        SimpleAdapter adapterNhanvien = new SimpleAdapter(getActivity(),
                listHMNhanVien,
                android.R.layout.simple_list_item_1,
                new String[]{"HoTenNV"},
                new int[]{android.R.id.text1});
        spnnhanvien.setAdapter(adapterNhanvien);
        //set tiền lên đơn
        spnsanpham.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                HashMap<String, Object> hashMapSpGiaThue = (HashMap<String, Object>) spnsanpham.getSelectedItem();
                tvtienban.setText("" + hashMapSpGiaThue.get("GiaBan") + " VND");
                return false;
            }
        });

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> hashMapSanpham = (HashMap<String, Object>) spnsanpham.getSelectedItem();
                HashMap<String, Object> hashMapKhachhang = (HashMap<String, Object>) spnkhachhang.getSelectedItem();
                HashMap<String, Object> hashMapNhanvien = (HashMap<String, Object>) spnnhanvien.getSelectedItem();
                Date ngay = Calendar.getInstance().getTime();
                int thanhtoan = 0;
                int tienban = (int) hashMapSanpham.get("GiaBan");
                int makhachhang = (int) hashMapKhachhang.get("MaKH");
                int masanpham = (int) hashMapSanpham.get("MaSanPham");
                String manhanvien = (String) hashMapNhanvien.get("MaNV");
                if (donHangDAO.insert(new DonHang(makhachhang, masanpham, tienban, ngay, thanhtoan, manhanvien))) {
                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    list.clear();
                    list.addAll(donHangDAO.selectAll());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

    }


    private ArrayList<HashMap<String, Object>> getDSSanPham() {
        ArrayList<HashMap<String, Object>> listHashMap = new ArrayList<>();
        SanPhamDAO sanPhamDAO = new SanPhamDAO(getActivity());
        List<SanPham> list = sanPhamDAO.selectAll();
        for (SanPham sanPham : list) {
            HashMap<String, Object> hashmap = new HashMap<>();
            hashmap.put("MaSanPham", sanPham.getMaSanPham());
            hashmap.put("TenSanPham", sanPham.getTenSanPham());
            hashmap.put("GiaBan", sanPham.getGiaBan());
            hashmap.put("MaLoai", sanPham.getMaLoaiSP());
            hashmap.put("TenLoai", sanPham.getTenLoaii());
            listHashMap.add(hashmap);
        }
        return listHashMap;
    }

    private ArrayList<HashMap<String, Object>> getDSKhachHang() {
        ArrayList<HashMap<String, Object>> listHashMap = new ArrayList<>();
        KhachHangDAO khachHangDAO = new KhachHangDAO(getActivity());
        List<KhachHang> list = khachHangDAO.selectAll();
        for (KhachHang khachHang : list) {
            HashMap<String, Object> hashmap = new HashMap<>();
            hashmap.put("MaKH", khachHang.getMaKH());
            hashmap.put("HoTenKH", khachHang.getHoTenKH());
            hashmap.put("NamSinhKH", khachHang.getNamSinhKH());
            hashmap.put("DiaChiKH", khachHang.getDiaChiKH());
            hashmap.put("SDT", khachHang.getSDT());
            listHashMap.add(hashmap);
        }
        return listHashMap;
    }

    private ArrayList<HashMap<String, Object>> getDSNhanVien() {
        ArrayList<HashMap<String, Object>> listHashMap = new ArrayList<>();
        NhanVienDAO nhanVienDAO = new NhanVienDAO(getActivity());
        List<NhanVien> list = nhanVienDAO.selectAll();
        for (NhanVien nhanVien : list) {
            HashMap<String, Object> hashmap = new HashMap<>();
            hashmap.put("MaNV", nhanVien.getMaNV());
            hashmap.put("HoTenNV", nhanVien.getHoTenNV());
            hashmap.put("TaiKhoanNV", nhanVien.getTaiKhoanNV());
            hashmap.put("MatKhauNV", nhanVien.getMatKhauNV());
            listHashMap.add(hashmap);
        }
        return listHashMap;
    }

}