package duan1.nhom5.Entity;

import java.util.Date;

public class DonHang {
    private int MaDH;
    private int MaKH;
    private String MaNV;
    private int MaSanPham;
    private int TienBan;
    private Date Ngay;
    private int ThanhToan;

    public DonHang() {
    }

    public DonHang(int maDH, int maKH, String maNV, int maSanPham, int tienBan, Date ngay, int thanhToan) {
        MaDH = maDH;
        MaKH = maKH;
        MaNV = maNV;
        MaSanPham = maSanPham;
        TienBan = tienBan;
        Ngay = ngay;
        ThanhToan = thanhToan;
    }

    public DonHang(int madh, int maKH, int maSanPham, int tienBan, Date ngay, int thanhToan, String manv) {
        MaDH = madh;
        MaKH = maKH;
        MaSanPham = maSanPham;
        TienBan = tienBan;
        Ngay = ngay;
        ThanhToan = thanhToan;
        MaNV = manv;
    }
    public DonHang(int maKH, int maSanPham, int tienBan, Date ngay, int thanhToan, String manv) {
        MaKH = maKH;
        MaSanPham = maSanPham;
        TienBan = tienBan;
        Ngay = ngay;
        ThanhToan = thanhToan;
        MaNV = manv;
    }

    public int getMaDH() {
        return MaDH;
    }

    public void setMaDH(int maDH) {
        MaDH = maDH;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int maKH) {
        MaKH = maKH;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public int getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        MaSanPham = maSanPham;
    }

    public int getTienBan() {
        return TienBan;
    }

    public void setTienBan(int tienBan) {
        TienBan = tienBan;
    }

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date ngay) {
        Ngay = ngay;
    }

    public int getThanhToan() {
        return ThanhToan;
    }

    public void setThanhToan(int thanhToan) {
        ThanhToan = thanhToan;
    }
}
