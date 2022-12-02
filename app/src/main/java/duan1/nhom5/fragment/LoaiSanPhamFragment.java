package duan1.nhom5.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
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
import java.util.List;

import duan1.nhom5.Adapter.LoaiSanPhamAdapter;
import duan1.nhom5.DAO.LoaiSanPhamDAO;
import duan1.nhom5.Entity.KhachHang;
import duan1.nhom5.Entity.LoaiSanPham;
import duan1.nhom5.MainActivity;
import duan1.nhom5.R;


public class LoaiSanPhamFragment extends Fragment {
    private LoaiSanPhamAdapter loaiSanPhamAdapter;
    private LoaiSanPhamDAO loaiSanPhamDAO;
    private RecyclerView rcv_loaisp;
    ArrayList<LoaiSanPham> list;
    LoaiSanPham loaiSanPham;
    ImageView backloaisp, add_loaisp, cancel_loaisp;
    EditText edt_tenloaisp, edt_namsx, edt_hangsx;
    Button btn_themloaisp, btnhuythemloaisp;


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
        loaiSanPhamDAO = new LoaiSanPhamDAO(getActivity());
        rcv_loaisp = v.findViewById(R.id.rcv_loaisp);
        add_loaisp = v.findViewById(R.id.img_addloaisp);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcv_loaisp.setLayoutManager(layoutManager);

        capNhatRCV();

        add_loaisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_ThemLoaiSP(0, 0);
            }
        });


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

    public void capNhatRCV() {
        list = (ArrayList<LoaiSanPham>) loaiSanPhamDAO.selectAll();
        loaiSanPhamAdapter = new LoaiSanPhamAdapter(getActivity(), list, this);
        rcv_loaisp.setAdapter(loaiSanPhamAdapter);
    }

    public void Dialog_ThemLoaiSP(final int type, int position) {

        loaiSanPham = new LoaiSanPham();
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_themloaisp);
        btn_themloaisp = dialog.findViewById(R.id.btn_themloaisp);
        btnhuythemloaisp = dialog.findViewById(R.id.btn_huythemloaisp);
        cancel_loaisp = dialog.findViewById(R.id.cancelthemloaisp);

        edt_tenloaisp = dialog.findViewById(R.id.edt_tenloaisp);
        edt_namsx = dialog.findViewById(R.id.edt_namsx);
        edt_hangsx = dialog.findViewById(R.id.edt_hangsx);

        btnhuythemloaisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_tenloaisp.setText("");
                edt_namsx.setText("");
                edt_hangsx.setText("");
            }
        });


        cancel_loaisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if (type != 0) {
            loaiSanPham = list.get(position);
            edt_tenloaisp.setText(loaiSanPham.getTenLoai());
            edt_namsx.setText(String.valueOf(loaiSanPham.getNamSX()));
            edt_hangsx.setText(loaiSanPham.getHangSX());
        }

        btn_themloaisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loaiSanPham.setTenLoai(edt_tenloaisp.getText().toString().trim());
                loaiSanPham.setNamSX(edt_namsx.getText().toString());
                loaiSanPham.setHangSX(edt_hangsx.getText().toString());
                if (validate() > 0) {
                    //type =0 sẽ insert ngược lại sẽ update
                    if (type == 0) {

                        if (loaiSanPhamDAO.insert(loaiSanPham) > 0) {
                            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();

                    } else {

                        if (loaiSanPhamDAO.update(loaiSanPham) > 0) {
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


    public void xoa(int MaLoaiSP) {
        //Sử dụng Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Gọi function Delete
                loaiSanPhamDAO.delete(MaLoaiSP);
                Toast.makeText(getActivity(), "Xóa thành công", Toast.LENGTH_SHORT).show();
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
        if (edt_tenloaisp.getText().length() == 0 || edt_namsx.getText().length() == 0 || edt_hangsx.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}