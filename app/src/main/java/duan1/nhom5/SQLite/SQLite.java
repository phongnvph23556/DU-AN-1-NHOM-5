package duan1.nhom5.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite extends SQLiteOpenHelper {

    public static final String DB_NAME = "DuAn";
    public static final int DB_VERSION = 1;

    public SQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Admin(" +
                "TaiKhoan TEXT PRIMARY KEY," +
                "MatKhau TEXT not null)");

        db.execSQL("CREATE TABLE NhanVien(" +
                "MaNV INTEGER PRIMARY KEY," +
                "HoTenNV TEXT not null," +
                "NamSinhNV INTEGER not null," +
                "DiaChiNV TEXT not null)");

        db.execSQL("CREATE TABLE KhachHang(" +
                "MaKH INTEGER PRIMARY KEY AUTOINCREMENT," +
                "HoTenKH TEXT not null," +
                "NamSinhKH INTEGER not null," +
                "DiaChiKH TEXT not null," +
                "SDT INTEGER not null)");

        db.execSQL("CREATE TABLE LoaiSanPham(" +
                "MaLoaiSP INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TenLoai TEXT not null," +
                "NamSX INTEGER not null," +
                "HangSX TEXT not null) ");

        db.execSQL("CREATE TABLE SanPham(" +
                "MaSanPham INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TenSanPham TEXT not null," +
                "GiaBan INTEGER not null," +
                "MaLoaiSP INTEGER REFERENCES LoaiSanPham(MaLoaiSP))");

        db.execSQL("CREATE TABLE DonHang(" +
                "MaDH INTEGER PRIMARY KEY AUTOINCREMENT," +
                "MaKH INTEGER REFERENCES KhachHang(MaKH)," +
                "MaNV INTEGER REFERENCES NhanVien(MaNV)," +
                "MaSanPham INTEGER REFERENCES SanPham(MaSanPham)," +
                "TienBan INTEGER not null," +
                "Ngay DATE not null," +
                "ThanhToan INTEGER not null)");


        db.execSQL("INSERT INTO KhachHang VALUES ('4','nguyễn văn phong','2003','hà nội','0343243243')");
        db.execSQL("INSERT INTO KhachHang VALUES ('5','nguyễn văn phong','2003','hà nội','0343243243')");
        db.execSQL("INSERT INTO KhachHang VALUES ('6','nguyễn văn phong','2003','hà nội','0343243243')");
        db.execSQL("INSERT INTO KhachHang VALUES ('7','nguyễn văn phong','2003','hà nội','0343243243')");
        db.execSQL("INSERT INTO KhachHang VALUES ('8','nguyễn văn phong','2003','hà nội','0343243243')");
        db.execSQL("INSERT INTO KhachHang VALUES ('9','nguyễn văn phong','2003','hà nội','0343243243')");
        db.execSQL("INSERT INTO KhachHang VALUES ('10','nguyễn văn phong','2003','hà nội','0343243243')");
        db.execSQL("INSERT INTO KhachHang VALUES ('11','nguyễn văn phong','2003','hà nội','0343243243')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS NhanVien");
        db.execSQL("DROP TABLE IF EXISTS KhachHang");
        db.execSQL("DROP TABLE IF EXISTS LoaiSanPham");
        db.execSQL("DROP TABLE IF EXISTS SanPham");
        db.execSQL("DROP TABLE IF EXISTS DonHang");

        onCreate(db);
    }
}
