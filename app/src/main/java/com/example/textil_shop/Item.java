package com.example.textil_shop;

public class Item {
    private String name;
    private String desc;
    private int price;

    public Item(String name, String info, int price) {
        this.name = name;
        this.desc = info;
        this.price = price;
    }


    public String getName() {return name;}
    public String getInfo() {return desc;}
    public int getPrice() {return price;}

    public void setName(String name) {this.name= name;}
    public void setDesc(String desc) {this.desc = desc;}
    public void setPrice(int price) {this.price = price;}
}
