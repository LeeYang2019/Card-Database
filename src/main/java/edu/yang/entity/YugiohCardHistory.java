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

    @Column(name = "updated_date")
    private Timestamp ts;

    @Column(name = "price")
    private double price;

    @ManyToOne
    private YugiohCard yugiohCard;

    /**
     * no arg constructor
     */
    public YugiohCardHistory() { }


    public YugiohCardHistory(Timestamp ts, double price, YugiohCard yugiohCard) {
        this.ts = ts;
        this.price = price;
        this.yugiohCard = yugiohCard;
    }


    public void setDate(Timestamp ts) {
        this.ts = ts;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public void setYugiohCard(YugiohCard yugiohCard) {
        this.yugiohCard = yugiohCard;
    }


    public int getId() {
        return this.id;
    }


    public Date getTimeStamp() {
        return this.ts;
    }


    public double getPrice() {
        return this.price;
    }


    public YugiohCard getYugiohCard() {
        return this.yugiohCard;
    }
}
