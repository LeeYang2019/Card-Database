package edu.yang.entity;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * YugiohCard object
 * @author Lee Yang
 */
@Entity(name="YugiohCard")
@Table(name="yugioh_card")
public class YugiohCard {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name="card_name")
    private String cardName;

    @Column(name="card_type")
    private String cardType;

    @Column(name="card_rarity")
    private String cardRarity;

    @Column(name="card_set")
    private String cardSet;

    @Column(name="card_price")
    private double cardPrice;

    @Column(name="card_quantity")
    private int cardQuantity;

    @ManyToOne
    private Collector collector;

    @OneToMany (mappedBy = "yugioh_card", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UpdateHistory> entries = new HashSet<>();

    /**
     * no arg constructor
     */
    public YugiohCard() { }

    /**
     * arg constructor
     * @param cardName cardname
     * @param cardType cardType
     * @param cardRarity cardRarity
     * @param cardSet cardSet
     * @param cardPrice cardPrice
     * @param cardQuantity cardQuantity
     * @param collector collector
     */
    public YugiohCard(String cardName, String cardType, String cardRarity, String cardSet, double cardPrice, int cardQuantity, Collector collector) {
        this.cardName = cardName;
        this.cardType = cardType;
        this.cardRarity = cardRarity;
        this.cardSet = cardSet;
        this.cardPrice = cardPrice;
        this.cardQuantity = cardQuantity;
        this.collector = collector;
    }

    /**
     * sets id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * gets id
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * sets cardName
     * @param cardName cardName
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * gets cardName
     * @return cardName
     */
    public String getCardName() {
        return this.cardName;
    }

    /**
     * sets cardType
     * @param cardType cardType
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * gets cardType
     * @return cardType
     */
    public String getCardType() {
        return this.cardType;
    }

    /**
     * sets cardRarity
     * @param cardRarity cardRarity
     */
    public void setCardRarity(String cardRarity) {
        this.cardRarity = cardRarity;
    }

    /**
     * gets cardRarity
     * @return cardRarity
     */
    public String getCardRarity() {
        return this.cardRarity;
    }

    /**
     * sets cardSet
     * @param cardSet cardSet
     */
    public void setCardSet(String cardSet) {
        this.cardSet = cardSet;
    }

    /**
     * gets cardSet
     * @return cardSet
     */
    public String getCardSet() {
        return this.cardSet;
    }

    /**
     * sets cardPrice
     * @param cardPrice cardPrice
     */
    public void setCardPrice(double cardPrice) {
        this.cardPrice = cardPrice;
    }

    /**
     * gets cardPrice
     * @return cardPrice
     */
    public double getCardPrice() {
        return this.cardPrice;
    }

    /**
     * sets cardQuantity
     * @param cardQuantity cardQuantity
     */
    public void setCardQuantity(int cardQuantity) {
        this.cardQuantity = cardQuantity;
    }

    /**
     * gets cardQuantity
     * @return cardQuantity
     */
    public int getCardQuantity() {
        return this.cardQuantity;
    }

    /**
     * sets collector
     * @param collector collector
     */
    public void setCollector(Collector collector) {
        this.collector = collector;
    }

    /**
     * gets collector
     * @return collector
     */
    public Collector getCollector() {
        return this.collector;
    }

    public void setUpdate(Set<UpdateHistory> entries) {
        this.entries = entries;
    }

    public Set<UpdateHistory> getEntries() {
        return this.entries;
    }

    public void addEntry(UpdateHistory entry) {
        entries.add(entry);
        entry.setYugiohCard(this);
    }

    public void removeEntry(UpdateHistory entry) {
        entries.remove(entry);
        entry.setYugiohCard(null);
    }

    @Override
    public String toString() {
        return "cardName: " + this.cardName + "\n"
                + "cardType: " + this.cardType + "\n"
                + "cardRarity: " + this.cardRarity + "\n"
                + "cardSet: " + this.cardSet + "\n"
                + "Quantity: " + this.cardQuantity + "\n"
                + "cardPrice: $" + this.cardPrice + "\n";
    }

}

