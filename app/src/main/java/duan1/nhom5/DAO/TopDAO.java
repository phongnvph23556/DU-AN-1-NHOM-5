package duan1.nhom5.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import duan1.nhom5.Entity.Top;
import duan1.nhom5.SQLite.SQLite;

public class TopDAO {

    SQLiteDatabase db;
    public TopDAO(Context context) {
        SQLite sqLite = new SQLite(context);
        db = sqLite.getWritableDatabase();
    }

    public List<Top> selectAll() {
        String sql = "SELECT SanPham.TenSanPham, SanPham.GiaBan, COUNT(DonHang.MaSanPham) FROM SanPham " +
                "LEFT OUTER JOIN DonHang ON SanPham.MaSanPham = DonHang.MaSanPham " +
                "GROUP BY DonHang.MaSanPham " +                                                  // gộp
                "ORDER BY COUNT(DonHang.MaSanPham) DESC LIMIT 10";                                       // Sắp xếp giảm dần
        List<Top> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String TenSanPham = cursor.getString(0);
            int TienBan = cursor.getInt(1);
            int SoLuong = cursor.getInt(2);
            list.add(new Top(TenSanPham, TienBan, SoLuong));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public int getDoanhThu(String tuNgay, String denNgay){
        String sql = "SELECT SUM(TienBan) FROM DonHang WHERE Ngay BETWEEN'" + tuNgay + "' AND '" + denNgay + "' ";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int doanhThu = cursor.getInt(0);
        cursor.close();
        return doanhThu;
    }

}
