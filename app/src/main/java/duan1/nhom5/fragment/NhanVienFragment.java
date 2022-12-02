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

import duan1.nhom5.Adapter.KhachHangAdapter;
import duan1.nhom5.Adapter.NhanVienAdapter;
import duan1.nhom5.DAO.NhanVienDAO;
import duan1.nhom5.Entity.KhachHang;
import duan1.nhom5.Entity.NhanVien;
import duan1.nhom5.MainActivity;
import duan1.nhom5.R;


public class NhanVienFragment extends Fragment {
    private ImageView backnhanvien;
    private RecyclerView rcv_nhanvien;
    private NhanVienDAO nhanVienDAO;
    private NhanVienAdapter nhanVienAdapter;
    List<NhanVien> list;
    NhanVien nhanVien;
    ImageView imgadd_nv, img_cancelnv;
    EditText edt_tennv, edt_namsinhnv, edt_diachinv;
    Button btnthemnv, btnhuythemnv;


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
        View v = inflater.inflate(R.layout.fragment_nhan_vien, container, false);
        backnhanvien = v.findViewById(R.id.backnhanvien);
        rcv_nhanvien = v.findViewById(R.id.rcv_nhanvien);
        nhanVienDAO = new NhanVienDAO(getActivity());
        imgadd_nv = v.findViewById(R.id.img_addnv);

        capNhatRCV();

        imgadd_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_ThemNV(0, 0);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcv_nhanvien.setLayoutManager(layoutManager);


        backnhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    public void capNhatRCV() {
        list = (ArrayList<NhanVien>) nhanVienDAO.selectAll();
        nhanVienAdapter = new NhanVienAdapter(getActivity(), list, this);
        rcv_nhanvien.setAdapter(nhanVienAdapter);
    }

    public void Dialog_ThemNV(final int type, int position) {

        nhanVien = new NhanVien();
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_themnhanvien);
        btnthemnv = dialog.findViewById(R.id.btn_themnv);
        btnhuythemnv = dialog.findViewById(R.id.btnhuythemnv);
        img_cancelnv = dialog.findViewById(R.id.img_cancelnv);

        edt_tennv = dialog.findViewById(R.id.edtTenNV);
        edt_namsinhnv = dialog.findViewById(R.id.edtnsNV);
        edt_diachinv = dialog.findViewById(R.id.edtDiachiNV);


        btnhuythemnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_tennv.setText("");
                edt_namsinhnv.setText("");
                edt_diachinv.setText("");

            }
        });


        img_cancelnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if (type != 0) {
            nhanVien = list.get(position);
            edt_tennv.setText(nhanVien.getHoTenNV());
            edt_namsinhnv.setText(nhanVien.getNamSinhNV());
            edt_diachinv.setText(nhanVien.getDiaChiNV());
        }

        btnthemnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nhanVien.setHoTenNV(edt_tennv.getText().toString().trim());
                nhanVien.setNamSinhNV(edt_namsinhnv.getText().toString().trim());
                if (validate() > 0) {
                    //type =0 sẽ insert ngược lại sẽ update
                    if (type == 0) {

                        if (nhanVienDAO.insert(nhanVien) > 0) {
                            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();

                    } else {

                        if (nhanVienDAO.update(nhanVien) > 0) {
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

    public void xoa(final int MaNV) {
        //Sử dụng Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Gọi function Delete
                nhanVienDAO.delete(String.valueOf(MaNV));
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
        if (edt_tennv.getText().length() == 0 || edt_namsinhnv.getText().length() == 0 || edt_diachinv.getText().length() == 0) {
            Toast.makeText(getActivity(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }


}