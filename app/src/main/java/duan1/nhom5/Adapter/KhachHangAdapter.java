package duan1.nhom5.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import duan1.nhom5.Entity.KhachHang;
import duan1.nhom5.R;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.UserViewHolder>{
 private Context context;
 private List<KhachHang> khachHangList;

    public KhachHangAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<KhachHang> list){
        this.khachHangList=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khachhang,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
   KhachHang khachHang=khachHangList.get(position);
        if(khachHang==null){
            return;
        }
        holder.makh.setText(String.valueOf(khachHang.getMaKH()));
        holder.tenkh.setText(khachHang.getHoTenKH());
        holder.namsinhkh.setText(String.valueOf(khachHang.getNamSinhKH()));
        holder.diachikh.setText(khachHang.getDiaChiKH());
        holder.sdtkh.setText(String.valueOf(khachHang.getSDT()));


    }

    @Override
    public int getItemCount() {
        if(khachHangList!=null){
            return khachHangList.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView makh, tenkh, namsinhkh, diachikh, sdtkh;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            makh = itemView.findViewById(R.id.makh);
            tenkh = itemView.findViewById(R.id.tenkh);
            namsinhkh = itemView.findViewById(R.id.namsinhkh);
            diachikh = itemView.findViewById(R.id.diachikh);
            sdtkh = itemView.findViewById(R.id.sdtkh);

        }
    }
}
