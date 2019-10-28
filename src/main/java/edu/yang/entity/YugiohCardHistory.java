package edu.yang.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * YugiohCardHistory object
 * @author Lee Yang
 */
@Entity(name = "YugiohCardHistory")
@Table(name = "yugioh_card_history")
public class YugiohCardHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "update_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp ts;

    @Column(name = "price")
    private double price;

    @ManyToOne
    private YugiohCard yugiohCard;

    /**
     * no arg constructor
     */
    public YugiohCardHistory() { }

    /**
     * arg constructor
     * @param price
     * @param yugiohCard
     */
    public YugiohCardHistory(double price, YugiohCard yugiohCard) {
        this.price = price;
        this.yugiohCard = yugiohCard;
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
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     * gets timeStamp
     * @return
     */
    public Date getTimeStamp() {
        return this.ts;
    }

    /**
     * gets price
     * @return
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * gets YugiohCard
     * @return
     */
    public YugiohCard getYugiohCard() {
        return this.yugiohCard;
    }

    @Override
    public String toString() {
        return "id: " + this.id + "\n" +
                "card: " + this.yugiohCard + "\n" +
                "price: " + this.price;
    }

}
