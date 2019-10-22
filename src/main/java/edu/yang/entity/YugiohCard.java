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

    @ManyToOne
    private User user;

    @OneToMany (mappedBy = "yugiohCard", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<YugiohCardHistory> entries = new HashSet<>();

    public YugiohCard() { }

    public YugiohCard(String cardName, String cardType, User user) {
        this.cardName = cardName;
        this.cardType = cardType;
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

