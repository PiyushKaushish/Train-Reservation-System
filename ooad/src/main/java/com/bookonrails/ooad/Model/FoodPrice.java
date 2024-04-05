package com.bookonrails.ooad.Model;

public class FoodPrice {
 
    private double vegprice;
    private double nonvegprice;

    FoodPrice(){
        this.vegprice = 300.0;
        this.nonvegprice = 400.0;
    }

    public double getVegprice() {
        return vegprice;
    }

    public void setVegprice(double vegprice) {
        this.vegprice = vegprice;
    }

    public double getNonvegprice() {
        return nonvegprice;
    }

    public void setNonvegprice(double nonvegprice) {
        this.nonvegprice = nonvegprice;
    }

    

}
