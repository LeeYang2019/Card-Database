package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name="Player")
@Table(name="player")
public class Player {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name="player_name")
    private String playerName;

    @Column(name="player_last_name")
    private String playerLastName;

    public Player() {

    }

    public Player(String playerName, String playerLastName) {
        this.playerName = playerName;
        this.playerLastName = playerLastName;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public String getPlayerLastName() {
        return this.playerLastName;
    }
}
