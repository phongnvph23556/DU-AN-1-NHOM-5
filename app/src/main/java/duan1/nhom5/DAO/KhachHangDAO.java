package duan1.nhom5.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import duan1.nhom5.Entity.KhachHang;
import duan1.nhom5.SQLite.SQLite;

public class KhachHangDAO {
    private SQLiteDatabase db;

    public KhachHangDAO(Context context) {
        SQLite sqLite = new SQLite(context);
        db = sqLite.getWritableDatabase();
    }

    public long insert(KhachHang khachHang) {
        ContentValues values = new ContentValues();
        values.put("HoTenKH", khachHang.getHoTenKH());
        values.put("NamSinhKH", khachHang.getNamSinhKH());
        values.put("DiaChiKH", khachHang.getDiaChiKH());
        values.put("SDT", khachHang.getSDT());

        return db.insert("KhachHang", null, values);
    }

    public int update(KhachHang khachHang) {
        ContentValues values = new ContentValues();
        values.put("HoTenKH", khachHang.getHoTenKH());
        values.put("NamSinhKH", khachHang.getNamSinhKH());
        values.put("DiaChiKH", khachHang.getDiaChiKH());
        values.put("SDT", khachHang.getSDT());
        return db.update("KhachHang", values, "MaKH=?", new String[]{String.valueOf(khachHang.getMaKH())});
    }

    public boolean delete(int MaKH) {
        long kq = db.delete("KhachHang", "MaKH=?", new String[]{String.valueOf(MaKH)});
        return (kq > 0);
    }

    public List<KhachHang> selectAll() {
        String sql = "SELECT * FROM KhachHang";
        return getData(sql);
    }

    public KhachHang getID(int MaKH) {
        String MaKH1 = String.valueOf(MaKH);
        String sql = "SELECT * FROM KhachHang WHERE MaKH=?";
        List<KhachHang> list = new ArrayList<>();
        list = getData(sql, MaKH1);
        return list.get(0);
    }

    private List<KhachHang> getData(String sql, String... selectionArgs) {
        List<KhachHang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int MaKH = cursor.getInt(0);
            String HoTenKH = cursor.getString(1);
            String NamSinhKH = cursor.getString(2);
            String DiaChiKH = cursor.getString(3);
            String SDT = cursor.getString(4);
            list.add(new KhachHang(MaKH, HoTenKH, NamSinhKH, DiaChiKH, SDT));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
