package duan1.nhom5.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import duan1.nhom5.DAO.TopDAO;
import duan1.nhom5.Entity.Top;
import duan1.nhom5.R;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder>{

    Context context;
    List<Top> list;
    TopDAO topDAO;

    public TopAdapter(Context context, List<Top> list) {
        this.context = context;
        this.list = list;
        this.topDAO = new TopDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_top, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvStt.setText(String.valueOf(position + 1));
        holder.tvName.setText(String.valueOf(list.get(position).getTenSanPham()));
        holder.tvGiaban.setText(String.valueOf(list.get(position).getGiaBan()) + " VND");
        holder.tvSoluong.setText(String.valueOf(list.get(position).getSoLuong()));

        if (position == 0)
            holder.tvStt.setBackgroundColor(context.getResources().getColor(R.color.red));
        else if (position == 1)
            holder.tvStt.setBackgroundColor(context.getResources().getColor(R.color.orange));
        else if(position==2){
            holder.tvStt.setBackgroundColor(context.getResources().getColor(R.color.yellow));
        }
        else holder.tvStt.setBackgroundColor(context.getResources().getColor(R.color.green));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvStt, tvName, tvGiaban, tvSoluong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStt = itemView.findViewById(R.id.item_tv_stt_top);
            tvName = itemView.findViewById(R.id.item_tv_tensp);
            tvGiaban = itemView.findViewById(R.id.item_tv_giaban);
            tvSoluong = itemView.findViewById(R.id.item_tv_soluong);
        }
    }
}
