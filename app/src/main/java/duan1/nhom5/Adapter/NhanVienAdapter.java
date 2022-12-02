package duan1.nhom5.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import duan1.nhom5.Entity.NhanVien;
import duan1.nhom5.R;
import duan1.nhom5.fragment.NhanVienFragment;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.UserViewHolder> {
    private Context context;
    private List<NhanVien> nhanVienList;
    private NhanVienFragment nhanVienFragment;


    public NhanVienAdapter(Context context, List<NhanVien> nhanVienList, NhanVienFragment nhanVienFragment) {
        this.context = context;
        this.nhanVienList = nhanVienList;
        this.nhanVienFragment = nhanVienFragment;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nhanvien, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        int st = position;
        NhanVien nhanVien = nhanVienList.get(position);
        if (nhanVien == null) {
            return;
        }
        holder.manv.setText(String.valueOf(nhanVien.getMaNV()));
        holder.hotennv.setText(nhanVien.getHoTenNV());
        holder.namsinhnv.setText(String.valueOf(nhanVien.getNamSinhNV()));
        holder.diachinv.setText(nhanVien.getDiaChiNV());
        holder.img_updatenv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nhanVienFragment.Dialog_ThemNV(1, st);
            }
        });
        holder.deletenv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nhanVienFragment.xoa(nhanVien.getMaNV());
            }
        });
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
        private ImageView img_updatenv, deletenv;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            manv = itemView.findViewById(R.id.tv_manv);
            hotennv = itemView.findViewById(R.id.tv_hotennv);
            namsinhnv = itemView.findViewById(R.id.tv_namsinhnv);
            diachinv = itemView.findViewById(R.id.tv_diachinv);

            img_updatenv = itemView.findViewById(R.id.img_updatenv);
            deletenv = itemView.findViewById(R.id.img_deletenv);


        }
    }
}
