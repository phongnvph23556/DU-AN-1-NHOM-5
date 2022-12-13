package duan1.nhom5.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import duan1.nhom5.Entity.DonHang;
import duan1.nhom5.SQLite.SQLite;

public class DonHangDAO {
    private SQLiteDatabase db;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public DonHangDAO(Context context) {
        SQLite sqLite = new SQLite(context);
        db = sqLite.getWritableDatabase();
    }

    public boolean insert(DonHang donHang) {
        ContentValues values = new ContentValues();
        values.put("MaKH", donHang.getMaKH());
        values.put("MaNV", donHang.getMaNV());
        values.put("MaSanPham", donHang.getMaSanPham());
        values.put("TienBan", donHang.getTienBan());
        values.put("Ngay", simpleDateFormat.format(donHang.getNgay()));
        values.put("ThanhToan", donHang.getThanhToan());

        long kq = db.insert("DonHang", null, values);
        return (kq > 0);
    }

    public boolean update(DonHang donHang) {
        ContentValues values = new ContentValues();
        values.put("MaKH", donHang.getMaKH());
        values.put("MaNV", donHang.getMaNV());
        values.put("MaSanPham", donHang.getMaSanPham());
        values.put("TienBan", donHang.getTienBan());
        values.put("Ngay", simpleDateFormat.format(donHang.getNgay()));
        values.put("ThanhToan", donHang.getThanhToan());

        long kq = db.update("DonHang", values, "MaDH=?", new String[]{String.valueOf(donHang.getMaDH())});
        return (kq > 0);
    }

    public boolean delete(int MaDH) {
        long kq = db.delete("DonHang", "MaDH=?", new String[]{String.valueOf(MaDH)});
        return (kq > 0);
    }

    public List<DonHang> selectAll() {
        String sql = "SELECT * FROM DonHang";
        return getData(sql);
    }

    public DonHang getID(int MaDH) {
        String MaDH1 = String.valueOf(MaDH);
        String sql = "SELECT * FROM DonHang WHERE MaDH=?";
        List<DonHang> list = new ArrayList<>();
        list = getData(sql, MaDH1);
        return list.get(0);
    }

    public List<DonHang> getData(String sql, String... selectionArgs) {
        List<DonHang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        cursor.moveToFirst();
        try {
            while (!cursor.isAfterLast()) {
                int MaDH = cursor.getInt(0);
                int MaKH = cursor.getInt(1);
                String MaNV = cursor.getString(2);
                int MaSanPham = cursor.getInt(3);
                int TienBan = cursor.getInt(4);
                Date Ngay = simpleDateFormat.parse(cursor.getString(5));
                int ThanhToan = cursor.getInt(6);

                list.add(new DonHang(MaDH, MaKH, MaNV, MaSanPham, TienBan, Ngay, ThanhToan));
                cursor.moveToNext();
            }
            cursor.close();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
