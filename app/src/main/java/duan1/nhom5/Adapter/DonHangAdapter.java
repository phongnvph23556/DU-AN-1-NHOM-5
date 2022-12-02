package duan1.nhom5.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import duan1.nhom5.DAO.DonHangDAO;
import duan1.nhom5.Entity.DonHang;
import duan1.nhom5.Entity.KhachHang;
import duan1.nhom5.Entity.SanPham;
import duan1.nhom5.R;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.UserViewHolder>{
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/mm/yyyy");
    private Context context;
    List<DonHang> donHangList;


    public DonHangAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<DonHang> list) {
        this.donHangList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        DonHang donHang = donHangList.get(position);
        if (donHang == null) {
            return;
        }
        holder.madh.setText(String.valueOf(donHang.getMaDH()));
        holder.makh.setText(String.valueOf(donHang.getMaKH()));
        holder.manv.setText(String.valueOf(donHang.getMaNV()));
        holder.masp.setText(String.valueOf(donHang.getMaSanPham()));
        holder.tienban.setText(String.valueOf(donHang.getTienBan()));
        holder.ngayban.setText(String.valueOf(donHang.getNgay()));

        holder.thanhtoan.setText(String.valueOf(donHang.getThanhToan()));

        if(donHang.getThanhToan()==1){
            holder.thanhtoan.setTextColor(Color.BLUE);
            holder.thanhtoan.setText("Đã thanh toán");
        }else {
            holder.thanhtoan.setTextColor(Color.RED);
            holder.thanhtoan.setText("Chưa thanh toán");
        }

    }

    @Override
    public int getItemCount() {
        if (donHangList != null) {
            return donHangList.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView madh,makh,manv,masp,tienban,ngayban,thanhtoan;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            madh=itemView.findViewById(R.id.tv_madh);
            makh=itemView.findViewById(R.id.tv_makh);
            manv=itemView.findViewById(R.id.tv_manv);
            masp=itemView.findViewById(R.id.masp);
            tienban=itemView.findViewById(R.id.tv_tienban);
            ngayban=itemView.findViewById(R.id.tv_tienban);
            thanhtoan=itemView.findViewById(R.id.tv_thanhtoan);
        }
    }

}
