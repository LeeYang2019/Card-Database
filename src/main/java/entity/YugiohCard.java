package entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity(name = "YugiohCard")
@Table(name = "yugioh_card")
public class YugiohCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name ="card_name")
    private String cardName;

    @Column(name ="card_type")
    private String cardType;

    @Column(name ="card_rarity")
    private String cardRarity;

    @Column(name ="card_set")
    private String cardSet;

    @Column(name ="card_price")
    private Double cardPrice;

    //no arg constructor
    public YugiohCard() {}

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param cardName
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     *
     * @param cardType
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     *
     * @param cardRarity
     */
    public void setCardRarity(String cardRarity) {
        this.cardRarity = cardRarity;
    }

    /**
     *
     * @param cardSet
     */
    public void setCardSet(String cardSet) {
        this.cardSet = cardSet;
    }

    /**
     *
     * @param cardPrice
     */
    public void setCardPrice(Double cardPrice) {
        this.cardPrice = cardPrice;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     *
     * @return
     */
    public String getCardName() {
        return this.cardName;
    }

    /**
     *
     * @return
     */
    public String getCardType() {
        return this.cardType;
    }

    /**
     *
     * @return
     */
    public String getCardRarity() {
        return this.cardRarity;
    }

    /**
     *
     * @return
     */
    public String getCardSet() {
        return this.cardSet;
    }

    /**
     *
     * @return
     */
    public Double getCardPrice() {
        return this.cardPrice;
    }

    /**
     *
     * @return
     */
    public String toString() {
        return "id: " + this.id + "\n"
                + "name: " + this.cardName + "\n"
                + "type: " + this.cardType + "\n"
                + "rarity: " + this.cardRarity + "\n"
                + "set: " + this.cardSet + "\n"
                + "price: " + this.cardPrice + "\n";
    }

}

