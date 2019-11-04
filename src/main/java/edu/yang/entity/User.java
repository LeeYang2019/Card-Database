package edu.yang.entity;
import org.hibernate.annotations.GenericGenerator;

import javax.annotation.security.DeclareRoles;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * User object
 * @Author Lee Yang
 */
@Entity(name = "User")
@Table(name = "user")
@DeclareRoles("general")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_role")
    private String role;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<YugiohCard> cards = new HashSet<>();

    /**
     * no arg constructor
     */
    public User() {

    }

    /**
     * constructor
     * @param userName
     * @param password
     */
    public User(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
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

    public void setRole(String role) {
        this.role = role;
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
     * gets role
     * @return role
     */
    public String getRole() {
        return this.role;
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
        card.setUser(this);
    }

    /**
     * removes card
     * @param card yugioh card
     */
    public void removeCard(YugiohCard card) {
        cards.remove(card);
        card.setUser(null);
    }

    @Override
    public String toString() {
        return "id : " + this.id + "\n"
                + "first name : " + this.userName + "\n"
                + "last name : " + this.password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                userName.equals(user.userName) &&
                password.equals(user.password) &&
                role.equals(user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, role);
    }
}


