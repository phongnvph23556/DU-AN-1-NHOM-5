package duan1.nhom5.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import duan1.nhom5.DAO.SanPhamDAO;
import duan1.nhom5.Entity.NhanVien;
import duan1.nhom5.Entity.SanPham;
import duan1.nhom5.R;
import duan1.nhom5.fragment.SanPhamFragment;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder>{

    Context context;
    List<SanPham> list;
    SanPhamDAO sanPhamDAO;
    ArrayList<HashMap<String, Object>> listHMSP;

    public void setFill_List(List<SanPham> fillList){
        this.list=fillList;
        notifyDataSetChanged();
    }
    public SanPhamAdapter(Context context, List<SanPham> list, ArrayList<HashMap<String, Object>> listHMSanPham ) {
        this.context = context;
        this.list = list;
        this.listHMSP = listHMSanPham;
        sanPhamDAO = new SanPhamDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sanpham, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvtensanpham.setText(list.get(position).getTenSanPham());
        holder.tvgiabansanpham.setText(list.get(position).getGiaBan()+" VNĐ");
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
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView tvtensanpham,tvgiabansanpham;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_sp);
            tvtensanpham = itemView.findViewById(R.id.tv_tensp);
            tvgiabansanpham=itemView.findViewById(R.id.tv_giaban);
        }
    }

    public void dialog_detail(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(LayoutInflater.from(context).inflate(R.layout.dialog_detail_sp, null));
        AlertDialog dialogDetail = builder.create();
        dialogDetail.show();

        TextView tvmasp = dialogDetail.findViewById(R.id.detail_masp);
        TextView tvtensp = dialogDetail.findViewById(R.id.detail_tensp);
        TextView tvgia = dialogDetail.findViewById(R.id.detail_giabansp);
        TextView tvloaisp = dialogDetail.findViewById(R.id.detail_loaisanpham);
        Button btnxoa = dialogDetail.findViewById(R.id.btn_detal_xoasp);
        Button btnsua = dialogDetail.findViewById(R.id.btn_detal_suasp);

        tvmasp.setText("Mã sản phẩm :" + list.get(position).getMaSanPham());
        tvtensp.setText("Tên sản phẩm :" + list.get(position).getTenSanPham());
        tvgia.setText("Giá bán : " + list.get(position).getGiaBan() + " VND");
        tvloaisp.setText("Loại sản phẩm : " + list.get(position).getTenLoaii());

        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Bạn có chắc chắn xóa không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (sanPhamDAO.delete(list.get(position).getMaSanPham())) {
                                    Toast.makeText(context, "thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    list.clear();
                                    list.addAll(sanPhamDAO.selectAll());
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

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDetail.dismiss();
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_themsanpham);
                dialog.show();

                EditText tensp = dialog.findViewById(R.id.edt_tensp);
                EditText edtprice = dialog.findViewById(R.id.edt_giaban);
                Spinner spnloaisp = dialog.findViewById(R.id.spn_loaisp);
                Button  btnadd = dialog.findViewById(R.id.btnthemsp);
                Button btncannel = dialog.findViewById(R.id.btnHuythemsp);

                tensp.setText(list.get(position).getTenSanPham());
                edtprice.setText(String.valueOf(list.get(position).getGiaBan()));
//                spnloaisp.setSelection(list.get(position).getMaLoaiSP());
                btnadd.setText("Thay đổi");

                SimpleAdapter adapter = new SimpleAdapter(context,  // context
                        listHMSP,                                   // list hashmap
                        android.R.layout.simple_list_item_1,              // layout hiển thị
                        new String[]{"tenloaisp"},                      // giá trị cần hiển thị (key trong hashmap
                        new int[]{android.R.id.text1});                   // đưa giá trị cần hiển thị lên widget nào trong layout simple_list_item_1
                spnloaisp.setAdapter(adapter);
                //lấy vị trí loại sách
                int index = 0, stt = -1;
                for (HashMap<String, Object> item: listHMSP){
                    if ((int) item.get("maloaisp")  == list.get(position).getMaLoaiSP()){
                        stt = index;
                    }
                    index++;
                }
                spnloaisp.setSelection(stt);


                btncannel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = tensp.getText().toString().trim();
                        int giaban = Integer.parseInt(edtprice.getText().toString().trim());
                        HashMap<String, Object> hashMap = (HashMap<String, Object>) spnloaisp.getSelectedItem();
                        int maloai = (int) hashMap.get("maloaisp");
                        if (ten.isEmpty() || giaban == 0 || maloai == 0){
                            Toast.makeText(context, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                        }else {
                            if (sanPhamDAO.update(new SanPham(list.get(position).getMaSanPham(),ten, giaban , maloai))){
                                Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                list.clear();
                                list.addAll(sanPhamDAO.selectAll());
                                notifyDataSetChanged();
                            }else {
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
