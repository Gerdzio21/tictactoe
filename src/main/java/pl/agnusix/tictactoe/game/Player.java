package pl.agnusix.tictactoe.game;

import java.util.Objects;

public class Player {
    private final String uid;


    public Player(String uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return uid.equals(player.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }

    @Override
    public String toString() {
        return "Player{" +
                "uid='" + uid + '\'' +
                '}';
    }
}