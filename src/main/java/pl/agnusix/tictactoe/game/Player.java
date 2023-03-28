package pl.agnusix.tictactoe.game;

import java.util.Objects;

public class Player {
    private final String uid;
    private final String nick;

    public Player(String uid, String nick) {
        this.uid = uid;
        this.nick = nick;
    }

    public Player(String uid) {
        this (uid, "UNKNOWN");
    }

    public String getUID(){
        return this.uid;
    }
    public String getNick(){ return this.nick;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return uid.equals(player.uid) && nick.equals(player.nick);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, nick);
    }

    @Override
    public String toString() {
        return "Player{" +
                "uid='" + uid + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }
}