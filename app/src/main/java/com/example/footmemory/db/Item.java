package com.example.footmemory.db;

import org.litepal.crud.LitePalSupport;

public class Item extends LitePalSupport {
    private int id;

    private String itemName;

    private double amount;

    private static final String unit = "kg";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static String getUnit() {
        return unit;
    }
}
