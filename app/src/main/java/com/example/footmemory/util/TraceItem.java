package com.example.footmemory.util;

public class TraceItem {
    private String name;
    private double amount;

    public TraceItem(String name,double amount)
    {
        this.name = name;
        this.amount = amount;
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

    private double value;

}
