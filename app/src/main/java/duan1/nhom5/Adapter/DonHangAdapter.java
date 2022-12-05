package duan1.nhom5.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import duan1.nhom5.DAO.DonHangDAO;
import duan1.nhom5.DAO.KhachHangDAO;
import duan1.nhom5.DAO.NhanVienDAO;
import duan1.nhom5.DAO.SanPhamDAO;
import duan1.nhom5.Entity.DonHang;
import duan1.nhom5.Entity.KhachHang;
import duan1.nhom5.Entity.SanPham;
import duan1.nhom5.R;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {
    Context context;
    List<DonHang> list;

    DonHangDAO donHangDAO;
    KhachHangDAO khachHangDAO;
    SanPhamDAO sanPhamDAO;
    NhanVienDAO nhanVienDAO;

    List<HashMap<String, Object>> listHMKhachhang;
    List<HashMap<String, Object>> listHMSanPham;
    List<HashMap<String, Object>> listHMNhanVien;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public DonHangAdapter(Context context, List<DonHang> list, List<HashMap<String, Object>> listHMKhachHang, List<HashMap<String, Object>> listHMSanPham, List<HashMap<String, Object>> listHMnhanvien) {
        this.context = context;
        this.list = list;
        this.donHangDAO = new DonHangDAO(context);
        this.khachHangDAO = new KhachHangDAO(context);
        this.sanPhamDAO = new SanPhamDAO(context);
        this.nhanVienDAO = new NhanVienDAO(context);
        this.listHMKhachhang = listHMKhachHang;
        this.listHMSanPham = listHMSanPham;
        this.listHMNhanVien = listHMnhanvien;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_donhang, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        list=new ArrayList<>();
        holder.tvtenkh.setText(khachHangDAO.getID(list.get(position).getMaKH()).getHoTenKH());
        holder.tvtensp.setText(sanPhamDAO.getID(list.get(position).getMaSanPham()).getTenSanPham());
        holder.tvdate.setText(simpleDateFormat.format(list.get(position).getNgay()));

        int stt = position;
        holder.btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Bạn có muốn thanh toán không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (donHangDAO.update(new DonHang(list.get(stt).getMaDH(), list.get(stt).getMaKH(), list.get(stt).getMaNV(), list.get(stt).getMaSanPham(),
                                        list.get(stt).getTienBan(), list.get(stt).getNgay(), 1))) {
                                    Toast.makeText(context, "thành công", Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list.addAll(donHangDAO.selectAll());
                                    notifyDataSetChanged();

                                } else {
                                    Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
        });
        if (list.get(stt).getThanhToan() == 1) {
            holder.btnThanhtoan.setVisibility(View.GONE);
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.green));

        } else {
            holder.btnThanhtoan.setVisibility(View.VISIBLE);
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.red1));
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_detail(list.get(stt));
            }
        });
    }

    private void dialog_detail(DonHang donHang) {

        Dialog dialogDetail = new Dialog(context);
        dialogDetail.setContentView(R.layout.dialog_detail_donhang);
        dialogDetail.show();

        TextView tvmdh = dialogDetail.findViewById(R.id.dialog_detail_madh);
        TextView tvtenkh = dialogDetail.findViewById(R.id.dialog_detail_tenkh);
        TextView tvtensp = dialogDetail.findViewById(R.id.dialog_detail_tensp);
        TextView tvngay = dialogDetail.findViewById(R.id.dialog_detail_ngayban);
        TextView tvgiaban = dialogDetail.findViewById(R.id.dialog_detail_giaban);
        TextView tvtennguoiban = dialogDetail.findViewById(R.id.dialog_detail_nguoiban);
        Button btnxoa = dialogDetail.findViewById(R.id.dialog_btnxoadh);
        Button btnsua = dialogDetail.findViewById(R.id.dialog_btnsuadh);


        tvmdh.setText("Mã đơn hàng: " + donHang.getMaDH());
        tvtenkh.setText("Tên khách hàng: " + khachHangDAO.getID(donHang.getMaKH()).getHoTenKH());
        tvtensp.setText("Tên sản phẩm: " + sanPhamDAO.getID(donHang.getMaSanPham()).getTenSanPham());
        tvngay.setText("Ngày: " + simpleDateFormat.format(donHang.getNgay()));
        tvgiaban.setText("Giá bán: " + donHang.getTienBan() + " VND");
        tvtennguoiban.setText("Người bán: " + nhanVienDAO.getID(String.valueOf(donHang.getMaNV())).getHoTenNV());
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Bạn có chắc chắn xóa không ?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (donHangDAO.delete(donHang.getMaDH())) {
                                    Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list.addAll(donHangDAO.selectAll());
                                    notifyDataSetChanged();
                                    dialogDetail.dismiss();
                                } else {
                                    Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
                            }
                        }).show();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDetail.dismiss();
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_themdonhang);
                dialog.show();

                Spinner spnkhachhang = dialog.findViewById(R.id.dialog_spn_kh_donhang);
                Spinner spnsanpham = dialog.findViewById(R.id.dialog_spn_sp_dh);
                Spinner spnnhanvien = dialog.findViewById(R.id.dialog_spn_nv_dh);
                TextView tvtienban = dialog.findViewById(R.id.dialog_tvtienban_dh);
                Button btnthem = dialog.findViewById(R.id.dialog_themdh);
                Button btnhuy = dialog.findViewById(R.id.dialog_huythemdh);

                TextView tvthemdonhang=dialog.findViewById(R.id.tv_themdonhang);
                tvthemdonhang.setText("Sửa đơn hàng");

                btnthem.setText("Sửa");

                //set spinner khách hàng, sản phẩm, nhân viên
                SimpleAdapter adapterSpnkhahhang = new SimpleAdapter(context, listHMKhachhang, android.R.layout.simple_list_item_1,
                        new String[]{"HoTenKH"}, new int[]{android.R.id.text1});
                spnkhachhang.setAdapter(adapterSpnkhahhang);
                SimpleAdapter adapterSpnsanpham = new SimpleAdapter(context, listHMSanPham, android.R.layout.simple_list_item_1,
                        new String[]{"TenSanPham"}, new int[]{android.R.id.text1});
                spnsanpham.setAdapter(adapterSpnsanpham);

                SimpleAdapter adapterSpnnhanvien = new SimpleAdapter(context, listHMNhanVien, android.R.layout.simple_list_item_1,
                        new String[]{"HoTenNV"}, new int[]{android.R.id.text1});
                spnnhanvien.setAdapter(adapterSpnnhanvien);
                //set vị trí spinner kh,sản phẩm,nhân viên
                int index = 0, stt = -1;
                for (HashMap<String, Object> khachhang : listHMKhachhang) {
                    if ((int) khachhang.get("MaKH") == donHang.getMaKH()) {
                        stt = index;
                    }
                    index++;
                }
                spnkhachhang.setSelection(stt);
                index = 0;
                stt = -1;
                for (HashMap<String, Object> item : listHMSanPham) {
                    if ((int) item.get("MaSanPham") == donHang.getMaSanPham()) {
                        stt = index;
                    }
                    index++;
                }
                spnsanpham.setSelection(stt);

                index = 0;
                stt = -1;
                for (HashMap<String, Object> item : listHMNhanVien) {
                    if ((int) item.get("MaNV") == donHang.getMaNV()) {
                        stt = index;
                    }
                    index++;
                }
                spnnhanvien.setSelection(stt);


                //set tiền khi click vào spnner san phẩm
                tvtienban.setText(donHang.getTienBan() + " VND");
                spnsanpham.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        HashMap<String, Object> hashMapGiaBan = (HashMap<String, Object>) spnsanpham.getSelectedItem();
                        tvtienban.setText(hashMapGiaBan.get("GiaBan") + " VND");
                        return false;
                    }
                });

                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnthem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, Object> hashMapSanPham = (HashMap<String, Object>) spnsanpham.getSelectedItem();
                        HashMap<String, Object> hashMapKhachHang = (HashMap<String, Object>) spnkhachhang.getSelectedItem();
                        HashMap<String, Object> hashMapNhanvien = (HashMap<String, Object>) spnnhanvien.getSelectedItem();
                        Date ngay = donHang.getNgay();
                        int thanhtoan = donHang.getThanhToan();
                        int tienban = (int) hashMapSanPham.get("GiaBan");
                        int makhachhang = (int) hashMapKhachHang.get("MaKH");
                        int masanpham = (int) hashMapSanPham.get("MaSanPham");
                        int manhanvien = (int) hashMapNhanvien.get("MaNV");
                        if (donHangDAO.update(new DonHang(donHang.getMaDH(),makhachhang, masanpham, tienban, ngay, thanhtoan, manhanvien))) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            list.clear();
                            list.addAll(donHangDAO.selectAll());
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
            return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        LinearLayout linearLayout;
        TextView tvtenkh, tvtensp, tvdate;
        Button btnThanhtoan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.item_carview_donhang);
            linearLayout = itemView.findViewById(R.id.item_linear_dh);
            tvtenkh = itemView.findViewById(R.id.item_tv_tenkh_donhang);
            tvtensp = itemView.findViewById(R.id.item_tv_tensp_donhang);
            tvdate = itemView.findViewById(R.id.item_tv_ngay_donhang);
            btnThanhtoan = itemView.findViewById(R.id.item_btn_thanhtoan);
        }
    }

}
