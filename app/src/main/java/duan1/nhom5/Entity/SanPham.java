package duan1.nhom5.Entity;

public class SanPham {
    private int MaSanPham;
    private int MaLoaiSP;
    private String TenSanPham;
    private int GiaBan;
    private String tenLoaii;

    public SanPham() {
    }

    public SanPham(int maSanPham, String tenSanPham, int giaBan, int maLoaiSP, String tenLoai) {
        MaSanPham = maSanPham;
        MaLoaiSP = maLoaiSP;
        TenSanPham = tenSanPham;
        GiaBan = giaBan;
        tenLoaii = tenLoai;
    }

    public String getTenLoaii() {
        return tenLoaii;
    }

    public void setTenLoaii(String tenLoaii) {
        this.tenLoaii = tenLoaii;
    }

    public SanPham(int maSanPham, String ten, int giaban, int maloai) {
        MaLoaiSP = maloai;
        TenSanPham = ten;
        GiaBan = giaban;
        MaSanPham = maSanPham;
    }

    public SanPham(String ten, int giaban, int maloai) {
        this.MaLoaiSP = maloai;
        this.TenSanPham = ten;
        this.GiaBan = giaban;
    }

    public int getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        MaSanPham = maSanPham;
    }

    public int getMaLoaiSP() {
        return MaLoaiSP;
    }

    public void setMaLoaiSP(int maLoaiSP) {
        MaLoaiSP = maLoaiSP;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public int getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(int giaBan) {
        GiaBan = giaBan;
    }
}
