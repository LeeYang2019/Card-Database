package entity;

import java.util.Date;

public class UpdateHistory {

    //store 4 things: id, date, quantity, price
    private int id;
    private Date date;
    private int quantity;
    private double price;

    /**
     * no arg constructor
     */
    public UpdateHistory() {

    }

    public UpdateHistory(Date date, int quantity, double price) {
        this.date = date;
        this.quantity = quantity;
        this.price = price;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public Date getDate() {
        return this.date;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public double getPrice() {
        return this.price;
    }
}
