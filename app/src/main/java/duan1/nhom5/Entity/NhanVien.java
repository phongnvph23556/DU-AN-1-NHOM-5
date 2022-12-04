package duan1.nhom5.Entity;

public class NhanVien {
    private int MaNV;
    private String HoTenNV;
    private String NamSinhNV;
    private String DiaChiNV;

    public NhanVien(String ten, String ngay, String diachi) {
        HoTenNV = ten;
        NamSinhNV = ngay;
        DiaChiNV = diachi;

    }

    public NhanVien(int maNV, String hoTenNV, String namSinhNV, String diaChiNV) {
        MaNV = maNV;
        HoTenNV = hoTenNV;
        NamSinhNV = namSinhNV;
        DiaChiNV = diaChiNV;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int maNV) {
        MaNV = maNV;
    }

    public String getHoTenNV() {
        return HoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        HoTenNV = hoTenNV;
    }

    public String getNamSinhNV() {
        return NamSinhNV;
    }

    public void setNamSinhNV(String namSinhNV) {
        NamSinhNV = namSinhNV;
    }

    public String getDiaChiNV() {
        return DiaChiNV;
    }

    public void setDiaChiNV(String diaChiNV) {
        DiaChiNV = diaChiNV;
    }
}
