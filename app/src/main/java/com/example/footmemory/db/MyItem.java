package com.example.footmemory.db;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class MyItem extends LitePalSupport {

    private int id;

    private String name;

    private double amount;

    private long time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
