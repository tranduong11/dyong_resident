package com.scan.dyong_resident.models;
public class Room {
    private int id;
    private String tenPhong;
    private String viTriSoTang;
    private String trangThai;
    private String moTa;

    public Room(int id, String tenPhong, String viTriSoTang, String trangThai, String moTa) {
        this.id = id;
        this.tenPhong = tenPhong;
        this.viTriSoTang = viTriSoTang;
        this.trangThai = trangThai;
        this.moTa = moTa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getViTriSoTang() {
        return viTriSoTang;
    }

    public void setViTriSoTang(String viTriSoTang) {
        this.viTriSoTang = viTriSoTang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}

