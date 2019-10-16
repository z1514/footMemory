package com.example.footmemory.util;

public class InnerItem {

    private String name;
    private double base;
    private String unit = "kg";

    public InnerItem(String name,double base)
    {
        this.name = name;
        this.base = base;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }
}
