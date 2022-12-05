package duan1.nhom5.Entity;

public class Top {
    private String TenSanPham;
    private Integer GiaBan;
    private Integer SoLuong;

    public Top() {
    }

    public Top(String tenSanPham, Integer giaBan, Integer soLuong) {
        TenSanPham = tenSanPham;
        GiaBan = giaBan;
        SoLuong = soLuong;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public Integer getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(Integer giaBan) {
        GiaBan = giaBan;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer soLuong) {
        SoLuong = soLuong;
    }
}
