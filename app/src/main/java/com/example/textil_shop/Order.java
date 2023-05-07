package com.example.textil_shop;

public class Order {
    private String UID;
    private String textil;
    private Float yard;


    public Order(String UID, String textil, Float yard) {
        this.UID = UID;
        this.textil = textil;
        this.yard = yard;
    }

    public Order() {
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getTextil() {
        return textil;
    }

    public void setTextil(String textil) {
        this.textil = textil;
    }

    public Float getYard() {
        return yard;
    }

    public void setYard(Float yard) {
        this.yard = yard;
    }
}
