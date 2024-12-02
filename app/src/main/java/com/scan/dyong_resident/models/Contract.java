package com.scan.dyong_resident.models;

public class Contract {
    private String room;
    private String owner;
    private int quantity;

    public Contract(String room, String owner, int quantity) {
        this.room = room;
        this.owner = owner;
        this.quantity = quantity;
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
}

