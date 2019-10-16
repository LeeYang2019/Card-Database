package entity;


import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Collector")
@Table(name = "collector")
public class Collector {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String password;

    @OneToMany (mappedBy = "collector", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<YugiohCard> cards = new HashSet<>();

    /**
     * no arg constructor
     */
    public Collector() {

    }

    /**
     * constructor
     * @param userName
     * @param password
     */
    public Collector(String userName, String password) {
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
    }d

    /**
     * gets password
     * @return password
     */
    public String getPassword() {
        return this.password;
    }


    public void setCards(Set<YugiohCard> cards) {
        this.cards = cards;
    }

    public Set<YugiohCard> getCards() {
        return this.cards;
    }

    public void addCard(YugiohCard card) {
        cards.add(card);
    }

    //need to write toString method
    //write equals method

}


