package duan1.nhom5.fragment;

import android.app.AlertDialog;
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
    KhachHang khachHang;
    ImageView cancel_khachhang, img_addkh;
    EditText edt_tenkh, edt_namsinhkh, edt_diachikh, edt_sdtkh;
    Button btn_themkh, btn_huythemkh;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_khach_hang, container, false);
        backkhachhang = v.findViewById(R.id.backkhachhang);
        rcv_khachhang = v.findViewById(R.id.rcv_khachhang);
        khachHangDAO = new KhachHangDAO(getActivity());
        img_addkh = v.findViewById(R.id.img_addkh);

        capNhatRCV();
        img_addkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(0, 0);
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcv_khachhang.setLayoutManager(layoutManager);


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

    public void capNhatRCV() {
        list = (ArrayList<KhachHang>) khachHangDAO.selectAll();
        khachHangAdapter = new KhachHangAdapter(getActivity(), list, this);
        rcv_khachhang.setAdapter(khachHangAdapter);
    }

    public void openDialog(final int type, int position) {

        khachHang = new KhachHang();
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_themkhachhang);
        btn_themkh = dialog.findViewById(R.id.btn_themkh);
        btn_huythemkh = dialog.findViewById(R.id.btnhuythemkh);
        cancel_khachhang = dialog.findViewById(R.id.img_cancelkh);

        edt_tenkh = dialog.findViewById(R.id.edtHoTenKH);
        edt_namsinhkh = dialog.findViewById(R.id.edtNS);
        edt_diachikh = dialog.findViewById(R.id.edtDiachiKH);
        edt_sdtkh = dialog.findViewById(R.id.edtSDTKH);

        btn_huythemkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_tenkh.setText("");
                edt_namsinhkh.setText("");
                edt_diachikh.setText("");
                edt_sdtkh.setText("");
            }
        });


        cancel_khachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if (type != 0) {
            khachHang = list.get(position);
            edt_tenkh.setText(khachHang.getHoTenKH());
            edt_namsinhkh.setText(String.valueOf(khachHang.getNamSinhKH()));
            edt_diachikh.setText(khachHang.getDiaChiKH());
            edt_sdtkh.setText(String.valueOf(khachHang.getSDT()));
        }

        btn_themkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                khachHang.setHoTenKH(edt_tenkh.getText().toString().trim());
                khachHang.setNamSinhKH(edt_namsinhkh.getText().toString().trim());
                khachHang.setDiaChiKH(edt_diachikh.getText().toString().trim());
                khachHang.setSDT(edt_sdtkh.getText().toString().trim());
                if (validate() > 0) {
                    //type =0 sẽ insert ngược lại sẽ update
                    if (type == 0) {

                        if (khachHangDAO.insert(khachHang) > 0) {
                            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();

                    } else {

                        if (khachHangDAO.update(khachHang) > 0) {
                            Toast.makeText(getActivity(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                }
                capNhatRCV();

            }
        });
        dialog.show();


    }

    public void xoa(final int MaKH) {
        //Sử dụng Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Gọi function Delete
                khachHangDAO.delete(MaKH);
                Toast.makeText(getActivity(),"Xóa thành công",Toast.LENGTH_SHORT).show();
                //cập nhật lại rcv;
                capNhatRCV();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }


    public int validate() {
        int check = 1;
        if (edt_tenkh.getText().length() == 0 || edt_namsinhkh.getText().length() == 0 || edt_diachikh.getText().length() == 0 || edt_sdtkh.getText().length() == 0) {
            Toast.makeText(getActivity(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}
