package com.scan.dyong_resident.models;

public class Reflection {
    private String tenPhong;
    private String viTriSoTang;
    private String nguoiTao;
    private String noiDung;

    public Reflection(String tenPhong, String viTriSoTang, String nguoiTao, String noiDung) {
        this.tenPhong = tenPhong;
        this.viTriSoTang = viTriSoTang;
        this.nguoiTao = nguoiTao;
        this.noiDung = noiDung;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public String getViTriSoTang() {
        return viTriSoTang;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public String getNoiDung() {
        return noiDung;
    }
}
