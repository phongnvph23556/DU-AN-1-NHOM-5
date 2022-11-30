package duan1.nhom5.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import duan1.nhom5.Entity.LoaiSanPham;
import duan1.nhom5.Entity.NhanVien;
import duan1.nhom5.R;
import duan1.nhom5.fragment.LoaiSanPhamFragment;

public class LoaiSanPhamAdapter extends RecyclerView.Adapter<LoaiSanPhamAdapter.UserViewHolder> {
    private Context context;
    ArrayList<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
    LoaiSanPhamFragment fragment;


    public LoaiSanPhamAdapter(Context context, ArrayList<LoaiSanPham> loaiSanPhamList, LoaiSanPhamFragment fragment) {
        this.context = context;
        this.loaiSanPhamList = loaiSanPhamList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loaisanpham, parent, false);
        return new UserViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        LoaiSanPham loaiSanPham = loaiSanPhamList.get(position);
        if (loaiSanPham == null) {
            return;
        }
        holder.maloaisp1.setText("mã loại sản phẩm" + loaiSanPham.getMaLoaiSP());
        holder.tenloai.setText("Tên loại: " + loaiSanPham.getTenLoai());
        holder.namsx.setText("Năm sản xuất: " + loaiSanPham.getNamSX());
        holder.hangsx.setText("Hãng sản xuất " + loaiSanPham.getHangSX());

        //xóa
        holder.img_delete_loaisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(loaiSanPham.getMaLoaiSP());
            }
        });

        //cập nhật
        holder.img_update_loaisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.suaLoaisp();
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
        private TextView maloaisp1, tenloai, namsx, hangsx;
        ImageView img_delete_loaisp, img_update_loaisp;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            maloaisp1 = itemView.findViewById(R.id.tv_maloaisp1);
            tenloai = itemView.findViewById(R.id.tv_tenloaisp);
            namsx = itemView.findViewById(R.id.tv_namsanxuat);
            hangsx = itemView.findViewById(R.id.tv_hangsx);
            img_delete_loaisp = itemView.findViewById(R.id.img_delete_loaisp);
            img_update_loaisp = itemView.findViewById(R.id.img_updateloaisp);
        }
    }


}
