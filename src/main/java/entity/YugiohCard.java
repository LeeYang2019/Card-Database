package entity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * YugiohCard object
 * @author Lee Yang
 */
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

}

