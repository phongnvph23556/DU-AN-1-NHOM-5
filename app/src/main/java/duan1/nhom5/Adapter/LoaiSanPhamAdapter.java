package duan1.nhom5.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import duan1.nhom5.Entity.LoaiSanPham;
import duan1.nhom5.Entity.NhanVien;
import duan1.nhom5.R;

public class LoaiSanPhamAdapter extends RecyclerView.Adapter<LoaiSanPhamAdapter.UserViewHolder>{
    private Context context;
    List<LoaiSanPham> loaiSanPhamList;

    public LoaiSanPhamAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<LoaiSanPham> list) {
        this.loaiSanPhamList = list;
        notifyDataSetChanged();
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
        holder.maloaisp1.setText(String.valueOf("mã loại sản phẩm"+loaiSanPham.getMaLoaiSP()));
        holder.tenloai.setText("Tên loại: "+loaiSanPham.getTenLoai());
        holder.namsx.setText(String.valueOf(loaiSanPham.getNamSX()));
        holder.hangsx.setText("Hãng sản xuất "+loaiSanPham.getHangSX());
    }

    @Override
    public int getItemCount() {
        if (loaiSanPhamList != null) {
            return loaiSanPhamList.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView maloaisp1,tenloai,namsx,hangsx;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            maloaisp1=itemView.findViewById(R.id.tv_maloaisp1);
            tenloai=itemView.findViewById(R.id.tv_tenloaisp);
            namsx=itemView.findViewById(R.id.tv_namsanxuat);
            hangsx=itemView.findViewById(R.id.tv_hangsx);
        }
    }
}
