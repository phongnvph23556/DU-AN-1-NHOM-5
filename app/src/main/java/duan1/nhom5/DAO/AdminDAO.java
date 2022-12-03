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
    public static String TAIKHOAN="";

    public AdminDAO(Context context) {
        SQLite sqLite = new SQLite(context);
        db = sqLite.getWritableDatabase();
    }
    public boolean changepass(String TaiKhoan,String MatKhau){
        ContentValues values=new ContentValues();
        values.put("MatKhau",MatKhau);
        int row=db.update("Admin",values,"TaiKhoan=?",new String[Integer.parseInt(TaiKhoan)]);
        if(row<=0){
            return false;
        }
        return true;
    }


    public Admin getTaiKhoan(String TaiKhoan) {
        String sql = "SELECT * FROM Admin WHERE TaiKhoan=?";
        List<Admin> list = getData(sql, TaiKhoan);
        return  list.get(0);

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
        values.put("TaiKhoan",obj.getTaiKhoan());
        values.put("MatKhau",obj.getMatKhau());
        return db.update("Admin", values, "TaiKhoan=?", new String[]{obj.getTaiKhoan()});
    }


    public Boolean insert(String TaiKhoan, String MatKhau){
        ContentValues contentValues= new ContentValues();
        contentValues.put("TaiKhoan", TaiKhoan);
        contentValues.put("MatKhau", MatKhau);
        long result = db.insert("Admin", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkTaiKhoan(String TaiKhoan) {
        Cursor cursor = db.rawQuery("Select * from Admin where TaiKhoan = ?", new String[]{TaiKhoan});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkusernamepassword(String TaiKhoan, String MatKhau){
        Cursor cursor = db.rawQuery("Select * from Admin where TaiKhoan = ? and MatKhau = ?", new String[] {TaiKhoan,MatKhau});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

}
