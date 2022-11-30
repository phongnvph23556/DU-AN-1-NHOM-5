package duan1.nhom5.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import duan1.nhom5.Entity.LoaiSanPham;
import duan1.nhom5.SQLite.SQLite;

public class LoaiSanPhamDAO {
    private SQLiteDatabase db;

    public LoaiSanPhamDAO(Context context) {
        SQLite sqLite=new SQLite(context);
        db=sqLite.getWritableDatabase();
    }

    public long insert(LoaiSanPham loaiSanPham){
        ContentValues values = new ContentValues();
        values.put("TenLoai", loaiSanPham.getTenLoai());
        values.put("NamSX", loaiSanPham.getNamSX());
        values.put("HangSX", loaiSanPham.getHangSX());

        return db.insert("LoaiSanPham", null, values);

    }

    public int update(LoaiSanPham loaiSanPham){
        ContentValues values = new ContentValues();
        values.put("TenLoai", loaiSanPham.getTenLoai());
        values.put("NamSX", loaiSanPham.getNamSX());
        values.put("HangSX", loaiSanPham.getHangSX());
        return db.update("LoaiSanPham", values, "MaLoaiSP=?", new String[]{String.valueOf(loaiSanPham.getMaLoaiSP())});
    }

    public boolean delete(int MaLoaiSP){
        long kq = db.delete("LoaiSanPham", "MaLoaiSP=?", new String[]{String.valueOf(MaLoaiSP)});
        return (kq> 0);
    }

    public List<LoaiSanPham> selectAll(){
        String sql = "SELECT * FROM LoaiSanPham";
        return getData(sql);
    }

    public LoaiSanPham getID(int MaLoaiSP){
        String MaLoaiSP1 = String.valueOf(MaLoaiSP);
        String sql = "SELECT * FROM LoaiSanPham WHERE MaLoaiSP=?";
        List<LoaiSanPham> list = new ArrayList<>();
        list = getData(sql, MaLoaiSP1);
        return list.get(0);
    }

    public List<LoaiSanPham> getData(String sql, String...selectionArgs){
        List<LoaiSanPham> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int MaLoaiSP = cursor.getInt(0);
            String TenLoai = cursor.getString(1);
            int NamSX=cursor.getInt(2);
            String HangSX=cursor.getString(3);
            list.add(new LoaiSanPham(MaLoaiSP,TenLoai,NamSX,HangSX));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


}
