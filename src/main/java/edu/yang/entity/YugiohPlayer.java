package edu.yang.entity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * YugiohPlayer object
 * @Author Lee Yang
 */
@Entity(name = "YugiohPlayer")
@Table(name = "yugioh_player")
public class YugiohPlayer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "player_name")
    private String userName;

    @Column(name = "player_password")
    private String password;

    @OneToMany (mappedBy = "yugioh_player", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<YugiohCard> cards = new HashSet<>();

    /**
     * no arg constructor
     */
    public YugiohPlayer() {

    }

    /**
     * constructor
     * @param userName
     * @param password
     */
    public YugiohPlayer(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * sets userName
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * sets password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets id
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * gets username
     * @return username
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * gets password
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * sets cards
     * @param cards
     */
    public void setCards(Set<YugiohCard> cards) {
        this.cards = cards;
    }

    /**
     * gets cards
     * @return cards yugioh cards
     */
    public Set<YugiohCard> getCards() {
        return this.cards;
    }

    /**
     * adds a card to the collection of yugioh cards
     * @param card yugioh card
     */
    public void addCard(YugiohCard card) {
        cards.add(card);
        card.setYugiohPlayer(this);
    }

    /**
     * removes card
     * @param card yugioh card
     */
    public void removeCard(YugiohCard card) {
        cards.remove(card);
        card.setYugiohPlayer(null);
    }

    @Override
    public String toString() {
        return "id : " + this.id + "\n"
                + "first name : " + this.userName + "\n"
                + "last name : " + this.password;
    }
}


