package com.scan.dyong_resident.models;

public class Contract {

    private String room;
    private String owner;
    private int quantity;
    private String ngayKy;
    private String ngayBatDau;
    private String ngayKetThuc;
    private double tienNha;
    private double tienCoc;
    private double internet;
    private double nuocUong;
    private double nuocUongTheoNguoi;
    private double tienDien;
    private double mayGiat;
    private String khac;

    public Contract(String room, String owner, int quantity, String ngayKy, String ngayBatDau, String ngayKetThuc,
                    double tienNha, double tienCoc, double internet, double nuocUong, double nuocUongTheoNguoi,
                    double tienDien, double mayGiat, String khac) {
        this.room = room;
        this.owner = owner;
        this.quantity = quantity;
        this.ngayKy = ngayKy;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tienNha = tienNha;
        this.tienCoc = tienCoc;
        this.internet = internet;
        this.nuocUong = nuocUong;
        this.nuocUongTheoNguoi = nuocUongTheoNguoi;
        this.tienDien = tienDien;
        this.mayGiat = mayGiat;
        this.khac = khac;
    }

    public String getRoom() {
        return room;
    }

    public String getOwner() {
        return owner;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getNgayKy() {
        return ngayKy;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public double getTienNha() {
        return tienNha;
    }

    public double getTienCoc() {
        return tienCoc;
    }

    public double getInternet() {
        return internet;
    }

    public double getNuocUong() {
        return nuocUong;
    }

    public double getNuocUongTheoNguoi() {
        return nuocUongTheoNguoi;
    }

    public double getTienDien() {
        return tienDien;
    }

    public double getMayGiat() {
        return mayGiat;
    }

    public String getKhac() {
        return khac;
    }
}
