package com.scan.dyong_resident.models;


public class ContractData {

    private String room;
    private String date;
    private String startDate;
    private String endDate;
    private String note;
    private String rent;
    private String paymentCycle;
    private String startPayment;
    private String deposit;
    private String promo;
    private String promoMonths;
    private String discount;
    private String other;

    public ContractData(String room, String date, String startDate, String endDate,
                        String note, String rent, String paymentCycle, String startPayment,
                        String deposit, String promo, String promoMonths, String discount,
                        String other) {
        this.room = room;
        this.date = date;
        this.startDate = startDate;
        this.endDate = endDate;
        this.note = note;
        this.rent = rent;
        this.paymentCycle = paymentCycle;
        this.startPayment = startPayment;
        this.deposit = deposit;
        this.promo = promo;
        this.promoMonths = promoMonths;
        this.discount = discount;
        this.other = other;
    }

    public String getRoom() {
        return room;
    }

    public String getDate() {
        return date;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getNote() {
        return note;
    }

    public String getRent() {
        return rent;
    }

    public String getPaymentCycle() {
        return paymentCycle;
    }

    public String getStartPayment() {
        return startPayment;
    }

    public String getDeposit() {
        return deposit;
    }
}

