package duan1.nhom5.Adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import duan1.nhom5.DAO.KhachHangDAO;
import duan1.nhom5.DAO.LoaiSanPhamDAO;
import duan1.nhom5.Entity.KhachHang;
import duan1.nhom5.Entity.LoaiSanPham;
import duan1.nhom5.Entity.SanPham;
import duan1.nhom5.R;
import duan1.nhom5.fragment.LoaiSanPhamFragment;

public class LoaiSanPhamAdapter extends RecyclerView.Adapter<LoaiSanPhamAdapter.UserViewHolder> {
    private Context context;
    private List<LoaiSanPham> loaiSanPhamList;
    LoaiSanPhamDAO loaiSanPhamDAO;

    public void setFill_List(List<LoaiSanPham> fillList) {
        this.loaiSanPhamList = fillList;
        notifyDataSetChanged();
    }


    public LoaiSanPhamAdapter(Context context, List<LoaiSanPham> loaiSanPhamList) {
        this.context = context;
        this.loaiSanPhamList = loaiSanPhamList;
        loaiSanPhamDAO = new LoaiSanPhamDAO(context);
    }

    @NonNull
    @Override
    public LoaiSanPhamAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loaisanpham, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSanPhamAdapter.UserViewHolder holder, int position) {
        holder.tenloaisp.setText(loaiSanPhamList.get(position).getTenLoai());
        int stt = position;
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_detail(stt);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (loaiSanPhamList != null) {
            return loaiSanPhamList.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView tenloaisp;
        CardView cardView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tenloaisp = itemView.findViewById(R.id.tv_tenloaisp);
            cardView = itemView.findViewById(R.id.cardview_loaisp);

        }
    }

    public void dialog_detail(int position) {
        Dialog dialogDetail = new Dialog(context);
        dialogDetail.setContentView(R.layout.dialog_detail_loaisp);
        dialogDetail.show();

        TextView ma_loaisp = dialogDetail.findViewById(R.id.detail_maloaisp);
        TextView ten_loaisp = dialogDetail.findViewById(R.id.detail_tenloaisp);
        TextView namsx = dialogDetail.findViewById(R.id.detail_namsx);
        TextView hangsx = dialogDetail.findViewById(R.id.detail_hangsx);
        Button xoa = dialogDetail.findViewById(R.id.btn_detal_xoaloaisp);
        Button sua = dialogDetail.findViewById(R.id.btn_detal_sualoaisp);

        ma_loaisp.setText("Mã loại sản phẩm : " + loaiSanPhamList.get(position).getMaLoaiSP());
        ten_loaisp.setText("Tên loại: " + loaiSanPhamList.get(position).getTenLoai());
        namsx.setText("Năm sản xuất : " + loaiSanPhamList.get(position).getNamSX());
        hangsx.setText("Hãng sản xuất : " + loaiSanPhamList.get(position).getHangSX());


        //code xóa
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Bạn có chắc chắn xóa không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (loaiSanPhamDAO.delete(loaiSanPhamList.get(position).getMaLoaiSP())) {
                                    Toast.makeText(context, "thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    loaiSanPhamList.clear();
                                    loaiSanPhamList.addAll(loaiSanPhamDAO.selectAll());
                                    notifyDataSetChanged();
                                    dialogDetail.dismiss();
                                } else {
                                    Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                                    dialogDetail.dismiss();
                                }
                            }
                        })
                        .setNegativeButton("không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialogDetail.dismiss();
                            }
                        })
                        .show();
            }
        });


        //code sửa
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDetail.dismiss();
                Dialog dialog = new Dialog(context);
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

                tenloaisp.setText(loaiSanPhamList.get(position).getTenLoai());
                namsx.setText(loaiSanPhamList.get(position).getNamSX());
                hangsx.setText(loaiSanPhamList.get(position).getHangSX());

                them.setText("Thay đổi");


                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ten_loaisp.setText("");
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
                            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        } else {
                            if (loaiSanPhamDAO.update(new LoaiSanPham(loaiSanPhamList.get(position).getMaLoaiSP(), tenloai, nam_sx, hang_sx))) {
                                Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                loaiSanPhamList.clear();
                                loaiSanPhamList.addAll(loaiSanPhamDAO.selectAll());
                                notifyDataSetChanged();
                            } else {
                                Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }
                    }
                });
            }
        });

    }
}