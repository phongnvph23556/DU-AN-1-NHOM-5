package duan1.nhom5.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import duan1.nhom5.Entity.SanPham;
import duan1.nhom5.SQLite.SQLite;

public class SanPhamDAO {
    private SQLiteDatabase db;

    public SanPhamDAO(Context context) {
        SQLite sqLite = new SQLite(context);
        db = sqLite.getWritableDatabase();
    }

    public long insert(SanPham sanPham) {
        ContentValues values = new ContentValues();
        values.put("MaLoaiSP", sanPham.getMaLoaiSP());
        values.put("TenSanPham", sanPham.getTenSanPham());
        values.put("GiaBan", sanPham.getGiaBan());

         return db.insert("SanPham", null, values);

    }

    public int update(SanPham sanPham) {
        ContentValues values = new ContentValues();
        values.put("MaLoaiSP", sanPham.getMaLoaiSP());
        values.put("TenSanPham", sanPham.getTenSanPham());
        values.put("GiaBan", sanPham.getGiaBan());
        return db.update("SanPham", values, "MaSanPham=?", new String[]{String.valueOf(sanPham.getMaSanPham())});

    }

    public boolean delete(int MaSanPham) {
        long kq = db.delete("SanPham", "MaSanPham=?", new String[]{String.valueOf(MaSanPham)});
        return (kq > 0);
    }

    public List<SanPham> selectAll() {
        String sql = "SELECT * FROM SanPham INNER JOIN LoaiSanPham ON SanPham.MaSanPham = LoaiSanPham.MaLoaiSP";
        return getData(sql);
    }

    public SanPham getID(int MaSanPham) {
        String MaSanPham1 = String.valueOf(MaSanPham);
        String sql = "SELECT * FROM SanPham INNER JOIN LoaiSanPham ON SanPham.MaSanPham = LoaiSanPham.MaLoaiSP WHERE MaSanPham=?";
        List<SanPham> list = new ArrayList<>();
        list = getData(sql, MaSanPham1);
        return list.get(0);
    }

    public List<SanPham> getData(String sql, String... selectionArgs) {
        List<SanPham> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int MaSanPham = cursor.getInt(0);
            int MaLoaiSP = cursor.getInt(1);
            String TenSanPham = cursor.getString(2);
            int GiaBan = cursor.getInt(3);

            list.add(new SanPham(MaSanPham, MaLoaiSP, TenSanPham, GiaBan));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

}
