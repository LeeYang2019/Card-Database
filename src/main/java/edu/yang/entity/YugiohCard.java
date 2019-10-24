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

    @Column(name = "price")
    private double price;

    @Column(name = "qty")
    private int quantity;

    @Column(name = "status")
    private String status;

    @ManyToOne
    private User user;

    @OneToMany (mappedBy = "yugiohCard", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<YugiohCardHistory> entries = new HashSet<>();

    public YugiohCard() {

    }

    public YugiohCard(String cardName, String cardType, String cardRarity, String cardSet, double price, int quantity, User user) {
        this.cardName = cardName;
        this.cardType = cardType;
        this.cardRarity = cardRarity;
        this.cardSet = cardSet;
        this.price = price;
        this.quantity = quantity;
        this.user = user;
    }

    public void setCardName(String cardName) { this.cardName = cardName; }
    public void setCardType(String cardType) { this.cardType = cardType; }
    public String getCardName() { return this.cardName; }
    public String getCardType() { return this.cardType; }
    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return this.user;
    }
    public void setEntries(Set<YugiohCardHistory> entries) {
        this.entries = entries;
    }
    public Set<YugiohCardHistory> getEntries() {
        return this.entries;
    }
    public void addEntry(YugiohCardHistory entry) {
        entries.add(entry);
        entry.setYugiohCard(this);
    }
    public void removeEntry(YugiohCardHistory entry) {
        entries.remove(entry);
        entry.setYugiohCard(null);
    }

}

