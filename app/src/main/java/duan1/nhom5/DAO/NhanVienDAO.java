package duan1.nhom5.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import duan1.nhom5.Entity.NhanVien;
import duan1.nhom5.SQLite.SQLite;

public class NhanVienDAO {
    private SQLiteDatabase db;

    public NhanVienDAO(Context context) {
        SQLite sqLite = new SQLite(context);
        db = sqLite.getWritableDatabase();
    }
    //check đăng nhập
//    public boolean checkDangNhap(String matt, String matkhau){
//        Cursor cursor = db.rawQuery("SELECT * FROM ThuThu WHERE maTT=? AND matkhau =?", new String[]{matt, matkhau});
//        if (cursor.getCount() != 0){
//            return true;
//        }else {
//            return false;
//        }
//    }

    public boolean insert(NhanVien nhanVien) {
        ContentValues values = new ContentValues();
        values.put("MaNV", nhanVien.getMaNV());
        values.put("HoTenNV", nhanVien.getHoTenNV());
        values.put("TaiKhoanNV", nhanVien.getTaiKhoanNV());
        values.put("MatKhauNV", nhanVien.getMatKhauNV());
        long kq = db.insert("NhanVien", null, values);
        return (kq > 0);
    }

    public boolean update(NhanVien nhanVien) {
        ContentValues values = new ContentValues();
        values.put("MaNV", nhanVien.getMaNV());
        values.put("HoTenNV", nhanVien.getHoTenNV());
        values.put("TaiKhoanNV", nhanVien.getTaiKhoanNV());
        values.put("MatKhauNV", nhanVien.getMatKhauNV());
        long kq = db.update("NhanVien", values, "MaNV=?", new String[]{String.valueOf(nhanVien.getMaNV())});
        return (kq > 0);
    }

    public boolean delete(String MaNV) {
        long kq = db.delete("NhanVien", "MaNV=?", new String[]{MaNV});
        return (kq > 0);
    }

    public List<NhanVien> selectAll() {
        String sql = "SELECT * FROM NhanVien";
        return getData(sql);
    }

    public NhanVien getID(String MaNV) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> list = new ArrayList<>();
        list = getData(sql, MaNV);

        return list.get(0);
    }

    public List<NhanVien> getData(String sql, String... selectionArgs) {
        List<NhanVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String MaNV = cursor.getString(0);
            String HoTenNV = cursor.getString(1);
            String TaiKhoanNV = cursor.getString(2);
            String MatKhauNV = cursor.getString(3);


            list.add(new NhanVien(MaNV, HoTenNV, TaiKhoanNV, MatKhauNV));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public Boolean checkusernamepassword(String TaiKhoanNV, String MatKhauNV) {
        Cursor cursor = db.rawQuery("Select * from NhanVien where TaiKhoanNV = ? and MatKhauNV = ?", new String[]{TaiKhoanNV, MatKhauNV});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


}
