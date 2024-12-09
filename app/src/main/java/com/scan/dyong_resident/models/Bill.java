package com.scan.dyong_resident.models;

public class Bill {
    private int id;
    private String phong;
    private String chuHopDong;
    private String kyThanhToan;
    private String ngayLap;
    private String hanThanhToan;
    private int soLuong;
    private double tienPhong;
    private double tienDien;
    private double tienNuoc;
    private double tienMang;
    private double tienThangMay;
    private double tienRac;

    // Constructor
    public Bill(int id, String room, String owner, String kyThanhToan, String ngayLap,
                String hanThanhToan, int soLuong, double tienPhong, double tienDien,
                double tienNuoc, double tienMang, double tienThangMay, double tienRac) {
        this.id = id;
        this.phong = room;
        this.chuHopDong = owner;
        this.kyThanhToan = kyThanhToan;
        this.ngayLap = ngayLap;
        this.hanThanhToan = hanThanhToan;
        this.soLuong = soLuong;
        this.tienPhong = tienPhong;
        this.tienDien = tienDien;
        this.tienNuoc = tienNuoc;
        this.tienMang = tienMang;
        this.tienThangMay = tienThangMay;
        this.tienRac = tienRac;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public String getPhong() {
        return phong;
    }

    public String getChuHopDong() {
        return chuHopDong;
    }

    public String getKyThanhToan() {
        return kyThanhToan;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public String getHanThanhToan() {
        return hanThanhToan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getTienPhong() {
        return tienPhong;
    }

    public double getTienDien() {
        return tienDien;
    }

    public double getTienNuoc() {
        return tienNuoc;
    }

    public double getTienMang() {
        return tienMang;
    }

    public double getTienThangMay() {
        return tienThangMay;
    }

    public double getTienRac() {
        return tienRac;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public void setChuHopDong(String chuHopDong) {
        this.chuHopDong = chuHopDong;
    }

    public void setKyThanhToan(String kyThanhToan) {
        this.kyThanhToan = kyThanhToan;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public void setHanThanhToan(String hanThanhToan) {
        this.hanThanhToan = hanThanhToan;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setTienPhong(double tienPhong) {
        this.tienPhong = tienPhong;
    }

    public void setTienDien(double tienDien) {
        this.tienDien = tienDien;
    }

    public void setTienNuoc(double tienNuoc) {
        this.tienNuoc = tienNuoc;
    }

    public void setTienMang(double tienMang) {
        this.tienMang = tienMang;
    }

    public void setTienThangMay(double tienThangMay) {
        this.tienThangMay = tienThangMay;
    }

    public void setTienRac(double tienRac) {
        this.tienRac = tienRac;
    }
}
