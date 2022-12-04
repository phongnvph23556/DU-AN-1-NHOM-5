package duan1.nhom5.Entity;

public class KhachHang {
    private int MaKH;
    private String HoTenKH;
    private String NamSinhKH;
    private String DiaChiKH;
    private String SDT;

    public KhachHang() {
    }

    public KhachHang(String hoTenKH, String namSinhKH, String diaChiKH, String Sodt) {
        HoTenKH = hoTenKH;
        NamSinhKH = namSinhKH;
        DiaChiKH = diaChiKH;
        SDT = Sodt;
    }

    public KhachHang(int maKH, String hoTenKH, String namSinhKH, String diaChiKH, String Sodt) {
        MaKH = maKH;
        HoTenKH = hoTenKH;
        NamSinhKH = namSinhKH;
        DiaChiKH = diaChiKH;
        SDT = Sodt;
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

    public String getNamSinhKH() {
        return NamSinhKH;
    }

    public void setNamSinhKH(String namSinhKH) {
        NamSinhKH = namSinhKH;
    }

    public String getDiaChiKH() {
        return DiaChiKH;
    }

    public void setDiaChiKH(String diaChiKH) {
        DiaChiKH = diaChiKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
}
