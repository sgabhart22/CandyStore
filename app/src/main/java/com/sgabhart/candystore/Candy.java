package com.sgabhart.candystore;

/**
 * Created by Admin on 9/20/2017.
 */

public class Candy {
    private int id;
    private String name;
    private double price;

    public Candy(int newID, String newName, double newPrice){
        setId(newID);
        setName(newName);
        setPrice(newPrice);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString(){
        return id + " " + name + " " + price;
    }
}
