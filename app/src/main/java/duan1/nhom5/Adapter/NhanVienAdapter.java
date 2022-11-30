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
import duan1.nhom5.R;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.UserViewHolder> {
    private Context context;
    private List<NhanVien> nhanVienList;

    public NhanVienAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<NhanVien> list) {
        this.nhanVienList = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nhanvien, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        NhanVien nhanVien = nhanVienList.get(position);
        if (nhanVien == null) {
            return;
        }
        holder.manv.setText(String.valueOf(nhanVien.getMaNV()));
        holder.hotennv.setText(nhanVien.getHoTenNV());
        holder.namsinhnv.setText(String.valueOf(nhanVien.getNamSinhNV()));
        holder.diachinv.setText(nhanVien.getDiaChiNV());
    }

    @Override
    public int getItemCount() {
        if (nhanVienList != null) {
            return nhanVienList.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView manv, hotennv, namsinhnv, diachinv;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            manv = itemView.findViewById(R.id.tv_manv);
            hotennv = itemView.findViewById(R.id.tv_hotennv);
            namsinhnv = itemView.findViewById(R.id.tv_namsinhnv);
            diachinv = itemView.findViewById(R.id.tv_diachinv);

        }
    }
}
