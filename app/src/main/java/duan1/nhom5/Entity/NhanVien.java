package duan1.nhom5.Entity;

public class NhanVien {
    private int MaNV;
    private String HoTenNV;
    private int NamSinhNV;
    private String DiaChiNV;
    private String MatKhau;

    public NhanVien() {
    }

    public NhanVien(int maNV, String hoTenNV, int namSinhNV, String diaChiNV, String matKhau) {
        MaNV = maNV;
        HoTenNV = hoTenNV;
        NamSinhNV = namSinhNV;
        DiaChiNV = diaChiNV;
        MatKhau = matKhau;
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

    public int getNamSinhNV() {
        return NamSinhNV;
    }

    public void setNamSinhNV(int namSinhNV) {
        NamSinhNV = namSinhNV;
    }

    public String getDiaChiNV() {
        return DiaChiNV;
    }

    public void setDiaChiNV(String diaChiNV) {
        DiaChiNV = diaChiNV;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }
}