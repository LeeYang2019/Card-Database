package edu.yang.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;

/**
 * UpdateHistory object
 * @author Lee Yang
 */
@Entity(name="UpdateHistory")
@Table(name="update_history")
public class UpdateHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name="update_date")
    private Date date;

    @Column(name="quantity")
    private int quantity;

    @Column(name="price")
    private double price;

    @ManyToOne
    private YugiohCard yugiohCard;

    /**
     * no arg constructor
     */
    public UpdateHistory() { }

    /**
     * arg constructor
     * @param date
     * @param quantity
     * @param price
     */
    public UpdateHistory(Date date, int quantity, double price, YugiohCard yugiohCard) {
        this.date = date;
        this.quantity = quantity;
        this.price = price;
        this.yugiohCard = yugiohCard;
    }


    /**
     * sets date
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * sets quantity
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * sets price
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * sets YugiohCard
     * @param yugiohCard
     */
    public void setYugiohCard(YugiohCard yugiohCard) {
        this.yugiohCard = yugiohCard;
    }

    /**
     * gets id
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * gets date
     * @return date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * gets quantity
     * @return quantity
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * gets price
     * @return price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * gets YugiohCard
     * @return YugiohCard
     */
    public YugiohCard getYugiohCard() {
        return this.yugiohCard;
    }
}
