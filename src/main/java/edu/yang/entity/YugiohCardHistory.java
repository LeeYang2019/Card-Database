package edu.yang.entity;

import org.hibernate.annotations.GenericGenerator;
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

    @Column(name = "updated_dt")
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
     * @param ts
     * @param quantity
     * @param price
     */
    public YugiohCardHistory(Timestamp ts, int quantity, double price, YugiohCard yugiohCard) {
        this.ts = ts;
        this.price = price;
        this.yugiohCard = yugiohCard;
    }

    /**
     * sets date
     * @param ts
     */
    public void setDate(Timestamp ts) {
        this.ts = ts;
    }


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
     * gets timestamp
     * @return timestamp
     */
    public Date getTimeStamp() {
        return this.ts;
    }


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
