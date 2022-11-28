package duan1.nhom5.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import duan1.nhom5.Entity.NhanVien;
import duan1.nhom5.Entity.SanPham;
import duan1.nhom5.R;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.UserViewHolder>{
    private Context context;
    List<SanPham> sanPhamList;

    public SanPhamAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<SanPham> list) {
        this.sanPhamList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        SanPham sanPham = sanPhamList.get(position);
        if (sanPham == null) {
            return;
        }
        holder.masp.setText(String.valueOf(sanPham.getMaSanPham()));
        holder.maloaisp.setText(String.valueOf(sanPham.getMaLoaiSP()));
        holder.tensp.setText(sanPham.getTenSanPham());
        holder.giaban.setText(String.valueOf(sanPham.getGiaBan()));
    }

    @Override
    public int getItemCount() {
        if (sanPhamList != null) {
            return sanPhamList.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
private TextView masp,maloaisp,tensp,giaban;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            masp=itemView.findViewById(R.id.tv_masp);
            maloaisp=itemView.findViewById(R.id.tv_maloaisp);
            tensp=itemView.findViewById(R.id.tv_tensp);
            giaban=itemView.findViewById(R.id.tv_giabansp);
        }
    }

}
