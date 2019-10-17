package com.example.footmemory.util;

public class TraceItem {
    private String name;
    private double amount;
    private long time;

    public TraceItem(String name,double amount,long time)
    {
        this.name = name;
        this.amount = amount;
        this.time = time;
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

    //private double value;

}
