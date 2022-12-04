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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import duan1.nhom5.DAO.NhanVienDAO;
import duan1.nhom5.Entity.NhanVien;
import duan1.nhom5.R;
import duan1.nhom5.fragment.NhanVienFragment;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.UserViewHolder> {
    private Context context;
    private List<NhanVien> nhanVienList;
    NhanVienDAO nhanVienDAO;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

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
        holder.manv.setText(String.valueOf(nhanVienList.get(position).getMaNV()));
        holder.hotennv.setText(nhanVienList.get(position).getHoTenNV());
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
        CardView cardView;

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
        TextView ngay = dialogDetail.findViewById(R.id.detail_ngaysinhnv);
        TextView diachinv = dialogDetail.findViewById(R.id.detail_diachinv);
        Button xoa = dialogDetail.findViewById(R.id.btn_detal_xoanv);
        Button sua = dialogDetail.findViewById(R.id.btn_detal_suanv);

        manv.setText("Mã nhân viên: " + nhanVienList.get(position).getMaNV());
        tennv.setText("Tên: " + nhanVienList.get(position).getHoTenNV());
        ngay.setText("Ngày sinh: " + nhanVienList.get(position).getNamSinhNV());
        diachinv.setText("Địa chỉ: " + nhanVienList.get(position).getDiaChiNV());


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

                EditText name = dialog.findViewById(R.id.edtTenNV);
                EditText date = dialog.findViewById(R.id.edtnsNV);
                EditText address = dialog.findViewById(R.id.edtDiachiNV);
                Button them = dialog.findViewById(R.id.btn_themnv);
                Button huy = dialog.findViewById(R.id.btnhuythemnv);

                name.setText(nhanVienList.get(position).getHoTenNV());
                date.setText(nhanVienList.get(position).getNamSinhNV());
                address.setText(nhanVienList.get(position).getDiaChiNV());
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
                        dialog.dismiss();
                    }
                });

                them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = name.getText().toString().trim();
                        String ngay = date.getText().toString().trim();
                        String diachi = address.getText().toString().trim();
                        if (ten.isEmpty() || ngay.isEmpty() || diachi.isEmpty()) {
                            Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                        } else {
                            if (nhanVienDAO.update(new NhanVien(nhanVienList.get(position).getMaNV(), ten, ngay, diachi))) {
                                Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                nhanVienList.clear();
                                nhanVienList.addAll(nhanVienDAO.selectAll());
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
