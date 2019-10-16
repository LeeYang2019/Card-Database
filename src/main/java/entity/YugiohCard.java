package entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity(name = "YugiohCard")
@Table(name = "yugioh_card")
public class YugiohCard {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "card_rarity")
    private String cardRarity;

    @Column(name = "card_set")
    private String cardSet;

    @Column(name = "card_price")
    private double cardPrice;

    @Column(name = "card_quantity")
    private int cardQuantity;

    @ManyToOne
    private Collector collector;

    /**
     * no arg constructor
     */
    public YugiohCard() {

    }

    /**
     * arg constructor
     * @param cardName cardname
     * @param cardType cardType
     * @param cardRarity cardRarity
     * @param cardSet cardSet
     * @param cardPrice cardPrice
     * @param cardQuantity cardQuantity
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

    public int getId() {
        return this.id;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardName() {
        return this.cardName;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardType() {
        return this.cardType;
    }

    public void setCardRarity(String cardRarity) {
        this.cardRarity = cardRarity;
    }

    public String getCardRarity() {
        return this.cardRarity;
    }

    public void setCardSet(String cardSet) {
        this.cardSet = cardSet;
    }

    public String getCardSet() {
        return this.cardSet;
    }

    public void setCardPrice(double cardPrice) {
        this.cardPrice = cardPrice;
    }

    public double getCardPrice() {
        return this.cardPrice;
    }

    public void setCardQuantity(int cardQuantity) {
        this.cardQuantity = cardQuantity;
    }

    public int getCardQuantity() {
        return this.cardQuantity;
    }

    public void setCollector(Collector collector) {
        this.collector = collector;
    }

    public Collector getCollector() {
        return this.collector;
    }

}

