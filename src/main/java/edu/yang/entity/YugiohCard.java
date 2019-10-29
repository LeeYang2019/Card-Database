package edu.yang.entity;

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

    @Column(name = "card_rarity")
    private String cardRarity;

    @Column(name = "card_set")
    private String cardSet;

    @Column(name = "card_index")
    private String cardIndex;

    @Column(name = "price")
    private double price;

    @Column(name = "qty")
    private int quantity;

    @Column(name = "status")
    private String status;

    @Column(name = "image")
    private String image;

    @ManyToOne
    private User user;

    @OneToMany (mappedBy = "yugiohCard", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<YugiohCardHistory> entries = new HashSet<>();

    /**
     * no arg constructor
     */
    public YugiohCard() {

    }

    /**
     * arg constructor
     * @param cardName
     * @param cardType
     * @param cardRarity
     * @param cardSet
     * @param index
     * @param price
     * @param quantity
     * @param status
     * @param image
     * @param user
     */
    public YugiohCard(String cardName, String cardType, String cardRarity, String cardSet, String index,
                      double price, int quantity, String status, String image, User user) {
        this.cardName = cardName;
        this.cardType = cardType;
        this.cardRarity = cardRarity;
        this.cardSet = cardSet;
        this.cardIndex = index;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.image = image;
        this.user = user;
    }

    /**
     * sets cardName
     * @param cardName
     */
    public void setCardName(String cardName) { this.cardName = cardName; }

    /**
     * sets cardType
     * @param cardType
     */
    public void setCardType(String cardType) { this.cardType = cardType; }

    /**
     * sets cardRarity
     * @param cardRarity
     */
    public void setCardRarity(String cardRarity) {
        this.cardRarity = cardRarity;
    }

    /**
     * sets cardSet
     * @param cardSet
     */
    public void setCardSet(String cardSet) {
        this.cardSet = cardSet;
    }

    /**
     * sets cardIndex
     * @param cardIndex
     */
    public void setIndex(String cardIndex) {
        this.cardIndex = cardIndex;
    }

    /**
     * sets price
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * sets quantity
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * sets status
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * sets image
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * sets user
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * gets cardName
     * @return cardName
     */
    public String getCardName() { return this.cardName; }

    /**
     * gets cardType
     * @return cardType
     */
    public String getCardType() { return this.cardType; }

    /**
     * gets cardRarity
     * @return cardRarity
     */
    public String getCardRarity() {
        return this.cardRarity;
    }

    /**
     * gets cardSet
     * @return cardSet
     */
    public String getCardSet() {
        return this.cardSet;
    }

    /**
     * gets cardIndex
     * @return cardIndex
     */
    public String getCardIndex() {
        return this.cardIndex;
    }

    /**
     * gets price
     * @return price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * gets quantity
     * @return quantity
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * gets status
     * @return status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * gets image
     * @return image
     */
    public String getImage() {
        return this.image;
    }


    /**
     * gets user
     * @return user
     */
    public User getUser() {
        return this.user;
    }

    /**
     * sets entries set
     * @param entries
     */
    public void setEntries(Set<YugiohCardHistory> entries) {
        this.entries = entries;
    }

    /**
     * gets entries
     * @return entries
     */
    public Set<YugiohCardHistory> getEntries() {
        return this.entries;
    }

    /**
     * sets an entry
     * @param entry
     */
    public void addEntry(YugiohCardHistory entry) {
        entries.add(entry);
        entry.setYugiohCard(this);
    }

    /**
     * removes an entry
     * @param entry
     */
    public void removeEntry(YugiohCardHistory entry) {
        entries.remove(entry);
        entry.setYugiohCard(null);
    }

    @Override
    public String toString() {
        return "user: " + this.user + "\n" +
                "cardName : " + this.cardName + "\n" +
                "cardType : " + this.cardType + "\n" +
                "cardRarity : " + this.cardRarity + "\n" +
                "cardSet : " + this.cardSet + "\n" +
                "price : " + this.price + "\n" +
                "quantity : " + this.quantity;
    }

}

