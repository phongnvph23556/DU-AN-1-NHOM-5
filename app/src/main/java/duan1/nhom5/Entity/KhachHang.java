package duan1.nhom5.Entity;

public class KhachHang {
    private int MaKH;
    private String HoTenKH;
    private int NamSinhKH;
    private String DiaChiKH;
    private int SDT;

    public KhachHang() {
    }

    public KhachHang(int maKH, String hoTenKH, int namSinhKH, String diaChiKH, int SDT) {
        MaKH = maKH;
        HoTenKH = hoTenKH;
        NamSinhKH = namSinhKH;
        DiaChiKH = diaChiKH;
        this.SDT = SDT;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int maKH) {
        MaKH = maKH;
    }

    public String getHoTenKH() {
        return HoTenKH;
    }

    public void setHoTenKH(String hoTenKH) {
        HoTenKH = hoTenKH;
    }

    public int getNamSinhKH() {
        return NamSinhKH;
    }

    public void setNamSinhKH(int namSinhKH) {
        NamSinhKH = namSinhKH;
    }

    public String getDiaChiKH() {
        return DiaChiKH;
    }

    public void setDiaChiKH(String diaChiKH) {
        DiaChiKH = diaChiKH;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }
}
