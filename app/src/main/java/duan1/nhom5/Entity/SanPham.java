package duan1.nhom5.Entity;

public class SanPham {
    private int MaSanPham;
    private int MaLoaiSP;
    private String TenSanPham;
    private int GiaBan;

    public SanPham() {
    }

    public SanPham(int maSanPham, int maLoaiSP, String tenSanPham, int giaBan) {
        MaSanPham = maSanPham;
        MaLoaiSP = maLoaiSP;
        TenSanPham = tenSanPham;
        GiaBan = giaBan;
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
