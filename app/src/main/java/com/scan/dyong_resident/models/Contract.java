package com.scan.dyong_resident.models;

import java.io.Serializable;

public class Contract implements Serializable {

    private int id;
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

    // Constructor
    public Contract(int id, String room, String owner, int quantity, String ngayKy, String ngayBatDau, String ngayKetThuc,
                    double tienNha, double tienCoc, double internet, double nuocUong, double nuocUongTheoNguoi,
                    double tienDien, double mayGiat, String khac) {
        this.id = id;
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

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNgayKy() {
        return ngayKy;
    }

    public void setNgayKy(String ngayKy) {
        this.ngayKy = ngayKy;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public double getTienNha() {
        return tienNha;
    }

    public void setTienNha(double tienNha) {
        this.tienNha = tienNha;
    }

    public double getTienCoc() {
        return tienCoc;
    }

    public void setTienCoc(double tienCoc) {
        this.tienCoc = tienCoc;
    }

    public double getInternet() {
        return internet;
    }

    public void setInternet(double internet) {
        this.internet = internet;
    }

    public double getNuocUong() {
        return nuocUong;
    }

    public void setNuocUong(double nuocUong) {
        this.nuocUong = nuocUong;
    }

    public double getNuocUongTheoNguoi() {
        return nuocUongTheoNguoi;
    }

    public void setNuocUongTheoNguoi(double nuocUongTheoNguoi) {
        this.nuocUongTheoNguoi = nuocUongTheoNguoi;
    }

    public double getTienDien() {
        return tienDien;
    }

    public void setTienDien(double tienDien) {
        this.tienDien = tienDien;
    }

    public double getMayGiat() {
        return mayGiat;
    }

    public void setMayGiat(double mayGiat) {
        this.mayGiat = mayGiat;
    }

    public String getKhac() {
        return khac;
    }

    public void setKhac(String khac) {
        this.khac = khac;
    }
}
