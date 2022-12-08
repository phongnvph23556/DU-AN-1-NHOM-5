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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import duan1.nhom5.DAO.KhachHangDAO;
import duan1.nhom5.DAO.NhanVienDAO;
import duan1.nhom5.Entity.KhachHang;
import duan1.nhom5.Entity.NhanVien;
import duan1.nhom5.Entity.SanPham;
import duan1.nhom5.R;
import duan1.nhom5.fragment.KhachHangFragment;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.UserViewHolder> {
    private Context context;
    private List<KhachHang> khachHangList;
    KhachHangDAO khachHangDAO;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void setFill_List(List<KhachHang> fillList){
        this.khachHangList=fillList;
        notifyDataSetChanged();
    }


    public KhachHangAdapter(Context context, List<KhachHang> nhanVienList) {
        this.context = context;
        this.khachHangList = nhanVienList;
        khachHangDAO=new KhachHangDAO(context);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khachhang, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.makh.setText("Mã khách hàng: " + khachHangList.get(position).getMaKH());
        holder.hotenkh.setText(khachHangList.get(position).getHoTenKH());
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
        if (khachHangList != null) {
            return khachHangList.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView makh, hotenkh;
        CardView cardView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            makh = itemView.findViewById(R.id.tv_maKh);
            hotenkh = itemView.findViewById(R.id.tv_hotenKH);
            cardView = itemView.findViewById(R.id.cardviewKH);

        }
    }

    public void dialog_detail(int position) {
        Dialog dialogDetail = new Dialog(context);
        dialogDetail.setContentView(R.layout.dialog_detail_kh);
        dialogDetail.show();

        TextView makh = dialogDetail.findViewById(R.id.detail_makh);
        TextView tenkh = dialogDetail.findViewById(R.id.detail_tenkh);
        TextView ngay = dialogDetail.findViewById(R.id.detail_ngaysinhkh);
        TextView diachikh = dialogDetail.findViewById(R.id.detail_diachikh);
        TextView sdtkh = dialogDetail.findViewById(R.id.detail_sdtkh);
        Button xoa = dialogDetail.findViewById(R.id.btn_detal_xoakh);
        Button sua = dialogDetail.findViewById(R.id.btn_detal_suakh);

        makh.setText("Mã nhân viên: " + khachHangList.get(position).getMaKH());
        tenkh.setText("Tên: " + khachHangList.get(position).getHoTenKH());
        ngay.setText("Ngày sinh: " + khachHangList.get(position).getNamSinhKH());
        diachikh.setText("Địa chỉ: " + khachHangList.get(position).getDiaChiKH());
        sdtkh.setText("Số điện thoại: " + khachHangList.get(position).getSDT());


        //code xóa
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Bạn có chắc chắn xóa không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (khachHangDAO.delete(khachHangList.get(position).getMaKH())) {
                                    Toast.makeText(context, "thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    khachHangList.clear();
                                    khachHangList.addAll(khachHangDAO.selectAll());
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
                dialog.setContentView(R.layout.dialog_themkhachhang);
                dialog.show();

                EditText name = dialog.findViewById(R.id.edtHoTenKH);
                EditText date = dialog.findViewById(R.id.edtNS);
                EditText address = dialog.findViewById(R.id.edtDiachiKH);
                EditText sdt = dialog.findViewById(R.id.edtSDTKH);
                Button them = dialog.findViewById(R.id.btn_themkh);
                Button huy = dialog.findViewById(R.id.btnhuythemkh);
                ImageView cancelkh = dialog.findViewById(R.id.img_cancelkh);
                cancelkh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                name.setText(khachHangList.get(position).getHoTenKH());
                date.setText(khachHangList.get(position).getNamSinhKH());
                address.setText(khachHangList.get(position).getDiaChiKH());
                sdt.setText(khachHangList.get(position).getSDT());
                them.setText("Thay đổi");

                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(year, month, dayOfMonth);
                                date.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                        datePickerDialog.show();
                    }
                });

                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name.setText("");
                        date.setText("");
                        address.setText("");
                        sdt.setText("");
                    }
                });

                them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = name.getText().toString().trim();
                        String ngay = date.getText().toString().trim();
                        String diachi = address.getText().toString().trim();
                        String sodt = sdt.getText().toString().trim();
                        if (ten.isEmpty() || ngay.isEmpty() || diachi.isEmpty() || sodt.isEmpty()) {
                            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        } else {
                            if (khachHangDAO.update(new KhachHang(khachHangList.get(position).getMaKH(), ten, ngay, diachi, sodt))) {
                                Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                khachHangList.clear();
                                khachHangList.addAll(khachHangDAO.selectAll());
                                notifyDataSetChanged();
                            } else {
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
