package com.example.textil_shop;

public class Item {
    private String Name;
    private String Desc;
    private int Price;

    public Item(String name, String desc, int price) {
        this.Name = name;
        this.Desc = desc;
        this.Price = price;
    }

    public Item() {
    }

    public String getName() {return Name;}
    public String getDesc() {return Desc;}
    public int getPrice() {return Price;}

    public void setName(String name) {this.Name = name;}
    public void setDesc(String desc) {this.Desc = desc;}
    public void setPrice(int price) {this.Price = price;}
}
