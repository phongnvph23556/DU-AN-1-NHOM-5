package duan1.nhom5.Adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import duan1.nhom5.DAO.NhanVienDAO;
import duan1.nhom5.Entity.NhanVien;
import duan1.nhom5.Entity.SanPham;
import duan1.nhom5.R;
import duan1.nhom5.fragment.NhanVienFragment;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.UserViewHolder> {
    private Context context;
    private List<NhanVien> nhanVienList;
    NhanVienDAO nhanVienDAO;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void setFill_List(List<NhanVien> fillList) {
        this.nhanVienList = fillList;
        notifyDataSetChanged();
    }

    public NhanVienAdapter(Context context, List<NhanVien> nhanVienList) {
        this.context = context;
        this.nhanVienList = nhanVienList;
        nhanVienDAO = new NhanVienDAO(context);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nhanvien, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        holder.manv.setText("Mã nhân viên: " + nhanVienList.get(position).getMaNV());
        holder.hotennv.setText(nhanVienList.get(position).getHoTenNV());
        if( nhanVienList.get(position).getTaiKhoanNV().equals("admin")){
            holder.cardView.setVisibility(View.GONE);
        }

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
        if (nhanVienList != null) {
            return nhanVienList.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView manv, hotennv;
        LinearLayout cardView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            manv = itemView.findViewById(R.id.tv_maNV);
            hotennv = itemView.findViewById(R.id.tv_hotennv);
            cardView = itemView.findViewById(R.id.cardviewnv);

        }
    }

    public void dialog_detail(int position) {
        Dialog dialogDetail = new Dialog(context);
        dialogDetail.setContentView(R.layout.dialog_detailnv);
        dialogDetail.show();

        TextView manv = dialogDetail.findViewById(R.id.detail_manv);
        TextView tennv = dialogDetail.findViewById(R.id.detail_tennv);
        TextView tknv = dialogDetail.findViewById(R.id.detail_tknv);
        TextView mknv = dialogDetail.findViewById(R.id.detail_mknv);
        Button xoa = dialogDetail.findViewById(R.id.btn_detal_xoanv);
        Button sua = dialogDetail.findViewById(R.id.btn_detal_suanv);

        manv.setText("Mã nhân viên: " + nhanVienList.get(position).getMaNV());
        tennv.setText("Tên: " + nhanVienList.get(position).getHoTenNV());
        tknv.setText("Tài khoản: " + nhanVienList.get(position).getTaiKhoanNV());
        mknv.setText("Mật khẩu: " + nhanVienList.get(position).getMatKhauNV());


        //code xóa
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Bạn có chắc chắn xóa không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (nhanVienDAO.delete(String.valueOf(nhanVienList.get(position).getMaNV()))) {
                                    Toast.makeText(context, "thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    nhanVienList.clear();
                                    nhanVienList.addAll(nhanVienDAO.selectAll());
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
                dialog.setContentView(R.layout.dialog_themnhanvien);
                dialog.show();

                EditText manv=dialog.findViewById(R.id.edtMaNV);
                EditText name = dialog.findViewById(R.id.edtTenNV);
                EditText tknv = dialog.findViewById(R.id.edttaikhoanNV);
                EditText mknv = dialog.findViewById(R.id.edtmatkhauNV);
                Button them = dialog.findViewById(R.id.btn_themnv);
                Button huy = dialog.findViewById(R.id.btnhuythemnv);

                manv.setText(nhanVienList.get(position).getMaNV());
                name.setText(nhanVienList.get(position).getHoTenNV());
                tknv.setText(nhanVienList.get(position).getTaiKhoanNV());
                mknv.setText(nhanVienList.get(position).getMatKhauNV());
                them.setText("Thay đổi");

                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

//                them.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String ma= manv.getText().toString().trim();
//                        String ten = name.getText().toString().trim();
//                        String tk=tknv.getText().toString().trim();
//                        String mk=mknv.getText().toString().trim();
//                        if (ma.isEmpty()||ten.isEmpty() || tk.isEmpty() || mk.isEmpty()) {
//                            Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
//                        } else {
//                            if (nhanVienDAO.update(new NhanVien(nhanVienList.get(position).getMaNV(), ten, ngay, diachi))) {
//                                Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
//                                dialog.dismiss();
//                                nhanVienList.clear();
//                                nhanVienList.addAll(nhanVienDAO.selectAll());
//                                notifyDataSetChanged();
//                            } else {
//                                Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
//                                dialog.dismiss();
//                            }
//                        }
//                    }
//                });
            }
        });

    }
}
