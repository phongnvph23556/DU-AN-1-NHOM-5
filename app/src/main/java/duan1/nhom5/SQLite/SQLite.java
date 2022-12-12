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
//        db.execSQL("CREATE TABLE Admin(" +
//                "TaiKhoan TEXT PRIMARY KEY," +
//                "MatKhau TEXT not null)");

        db.execSQL("CREATE TABLE NhanVien(" +
                "MaNV TEXT PRIMARY KEY," +
                "HoTenNV TEXT not null," +
                "TaiKhoanNV TEXT not null," +
                "MatKhauNV TEXT not null)");

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
                "HangSX TEXT not null)");

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


        db.execSQL("INSERT INTO KhachHang VALUES ('4','Nguyễn Văn Phong','2003','Hà Nội','0343243243')");
        db.execSQL("INSERT INTO KhachHang VALUES ('5','Nguyễn Sơn Dương','2003','Cà Mau','0378540244')");
        db.execSQL("INSERT INTO KhachHang VALUES ('6','Nguyễn Văn Quân','2000','Nghệ An','0378540244')");
        db.execSQL("INSERT INTO KhachHang VALUES ('7','Đặng Thế Tiến','2002','Nam Định','0378540244')");
        db.execSQL("INSERT INTO KhachHang VALUES ('8','Hoàng Văn Thắng','2003','Thanh Hóa','0378540244')");


//
//        db.execSQL("INSERT INTO NhanVien VALUES ('1','Nguyễn Văn Phong','2003','Hà Nội')");
//        db.execSQL("INSERT INTO NhanVien VALUES ('3','Nguyễn Văn Dương','2003','Hà Nội')");
//        db.execSQL("INSERT INTO NhanVien VALUES ('4','Hoàng Huy Quân','2003','Hà Nội')");
//        db.execSQL("INSERT INTO NhanVien VALUES ('5','Nguyễn Đặng Tiến','2003','Hà Nội')");
//        db.execSQL("INSERT INTO NhanVien VALUES ('6','Nguyễn Thế Sáng','2003','Hà Nam')");
//        db.execSQL("INSERT INTO NhanVien VALUES ('7','Nguyễn Hoan Thắng','2003','Hà Nội')");

        db.execSQL("INSERT INTO NhanVien VALUES('PH23556','văn phong','admin','admin')");


        db.execSQL("INSERT INTO SanPham VALUES ('1','Giày Jogarbola','2000000','5')");
        db.execSQL("INSERT INTO SanPham VALUES ('5','Giày Kamito','4300000','5')");
        db.execSQL("INSERT INTO SanPham VALUES ('67','Giày Ba sọc','1000000','5')");
        db.execSQL("INSERT INTO SanPham VALUES ('7','Áo Đấu Tuyển Đức','4000000','54')");
        db.execSQL("INSERT INTO SanPham VALUES ('9','Áo Đấu CLB Manchester United','1500000','54')");
        db.execSQL("INSERT INTO SanPham VALUES ('2','Áo Đấu Tuyển Brazil','6000000','54')");


        db.execSQL("INSERT INTO LoaiSanPham VALUES ('5','Giày','1986','Adidas')");
        db.execSQL("INSERT INTO LoaiSanPham VALUES ('7','Bóng','1999','Nike')");
        db.execSQL("INSERT INTO LoaiSanPham VALUES ('9','Tất','1986','Adidas')");
        db.execSQL("INSERT INTO LoaiSanPham VALUES ('23','Găng Tay','1986','Nike')");
        db.execSQL("INSERT INTO LoaiSanPham VALUES ('54','Áo','1997','Adidas')");
        db.execSQL("INSERT INTO LoaiSanPham VALUES ('67','Túi','2000','Nike')");


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
