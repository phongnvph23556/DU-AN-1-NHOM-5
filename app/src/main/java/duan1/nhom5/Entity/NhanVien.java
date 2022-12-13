package duan1.nhom5.Entity;

import java.io.Serializable;

public class NhanVien {
    private String MaNV;
    private String HoTenNV;
    private String TaiKhoanNV;
    private String MatKhauNV;


    public NhanVien() {
    }

    public NhanVien(String maNV, String hoTenNV, String taiKhoanNV, String matKhauNV) {
        MaNV = maNV;
        HoTenNV = hoTenNV;
        TaiKhoanNV = taiKhoanNV;
        MatKhauNV = matKhauNV;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getHoTenNV() {
        return HoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        HoTenNV = hoTenNV;
    }

    public String getTaiKhoanNV() {
        return TaiKhoanNV;
    }

    public void setTaiKhoanNV(String taiKhoanNV) {
        TaiKhoanNV = taiKhoanNV;
    }

    public String getMatKhauNV() {
        return MatKhauNV;
    }

    public void setMatKhauNV(String matKhauNV) {
        MatKhauNV = matKhauNV;
    }
}
