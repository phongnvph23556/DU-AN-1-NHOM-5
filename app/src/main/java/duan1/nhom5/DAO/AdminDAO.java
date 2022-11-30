package duan1.nhom5.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import duan1.nhom5.Entity.Admin;
import duan1.nhom5.SQLite.SQLite;

public class AdminDAO {
    private SQLiteDatabase db;

    public AdminDAO(Context context) {
        SQLite sqLite = new SQLite(context);
        db = sqLite.getWritableDatabase();
    }

    //check đăng nhập
    public boolean checkDangNhap(String TaiKhoan, String MatKhau) {
        Cursor cursor = db.rawQuery("SELECT * FROM Admin WHERE TaiKhoan=? AND MatKhau =?", new String[]{TaiKhoan, MatKhau});
        if (cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public Admin getID(String TaiKhoan) {
        String sql = "SELECT * FROM Admin";
        List<Admin> list = new ArrayList<>();
        list = getData(sql);
        return list.get(0);
    }

    public List<Admin> getData(String sql, String... selectionArgs) {
        List<Admin> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String TaiKhoan = cursor.getString(0);
            String MatKhau = cursor.getString(1);
            list.add(new Admin(TaiKhoan, MatKhau));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public int updatePass(Admin obj){
        ContentValues values = new ContentValues();
        values.put("hoten",obj.getTaiKhoan());
        values.put("matkhau",obj.getMatKhau());
        return db.update("ThuThu", values, "maTT=?", new String[]{obj.getAdmin()});
    }
}
