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

import duan1.nhom5.Adapter.LoaiSanPhamAdapter;
import duan1.nhom5.Adapter.SanPhamAdapter;
import duan1.nhom5.DAO.SanPhamDAO;
import duan1.nhom5.Entity.LoaiSanPham;
import duan1.nhom5.Entity.SanPham;
import duan1.nhom5.MainActivity;
import duan1.nhom5.R;


public class SanPhamFragment extends Fragment {
    private RecyclerView rcv_sanpham;
    private ImageView backsanpham, img_addsp, cancel_themsp;
    private SanPhamDAO sanPhamDAO;
    private ArrayList<SanPham> list;
    private SanPhamAdapter sanPhamAdapter;
    private SanPham sanPham;
    private EditText edmasp, Edmalsp, edTensp, edGiaban;
    private Button btnThemsp, btnHuysp;
    public static SanPhamFragment newInstance() {
        SanPhamFragment fragment = new SanPhamFragment();

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
        View v = inflater.inflate(R.layout.fragment_san_pham, container, false);
        backsanpham = v.findViewById(R.id.backsanpham);
        rcv_sanpham=v.findViewById(R.id.rcv_sanpham);
        btnThemsp = v.findViewById(R.id.btnLuusanphan);
        btnHuysp = v.findViewById(R.id.btnHuysanphan);
        img_addsp=v.findViewById(R.id.img_addsp);
        sanPhamDAO=new SanPhamDAO(getActivity());



        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rcv_sanpham.setLayoutManager(layoutManager);


        capNhatRCV();


        img_addsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_addSanpham();
            }
        });



        backsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    public void capNhatRCV() {
        list = (ArrayList<SanPham>) sanPhamDAO.selectAll();
        sanPhamAdapter = new SanPhamAdapter(getActivity(), list, this);
        rcv_sanpham.setAdapter(sanPhamAdapter);
    }

    public void dialog_addSanpham(){
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_themsanpham);


        btnThemsp = dialog.findViewById(R.id.btnLuusanphan);
        btnHuysp = dialog.findViewById(R.id.btnHuysanphan);


        btnHuysp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edmasp.setText("");
                Edmalsp.setText("");
                edTensp.setText("");
                edGiaban.setText("");
            }
        });

        edmasp = dialog.findViewById(R.id.edmasp);
        Edmalsp = dialog.findViewById(R.id.edt_maloaisp);
        edTensp = dialog.findViewById(R.id.edTenSP);
        edGiaban= dialog.findViewById(R.id.edGiaban);
        cancel_themsp = dialog.findViewById(R.id.cancel_themsanpham);

        //tắt dialog thêm
        cancel_themsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        edmasp.setEnabled(false);//tắt nhập với mã loại sp



        btnThemsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanPham = new SanPham();

                sanPham.setMaLoaiSP(Integer.parseInt(Edmalsp.getText().toString()));
                sanPham.setTenSanPham(edTensp.getText().toString());
                sanPham.setGiaBan(Integer.parseInt(edGiaban.getText().toString()));

                if (validate() > 0){
                    if (sanPhamDAO.insert(sanPham) > 0) {
                        Toast.makeText(getActivity(), "Thêm thành công nha", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Thêm thất bại nha", Toast.LENGTH_SHORT).show();
                    }
                    capNhatRCV();
                    dialog.cancel();
                }
            }
        });
        dialog.show();
    }

    public void xoa(int Masanpham) {
        //Sử dụng Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Gọi function Delete
                sanPhamDAO.delete(Masanpham);

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
        if (edmasp.getText().length() == 0 || Edmalsp.getText().length() == 0 || edTensp.getText().length() == 0 || edGiaban.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }


}