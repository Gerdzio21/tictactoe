package pl.agnusix.tictactoe.game;

import java.util.List;

public class Board {
    List<Field> fields;


    public boolean isMovePossible(Sign sign, Move move) {
        move.getMove();
        return true;
    }

    public void makeMove(Sign sign , Move move) {
        fields.get(move.getMove()).set(sign);
    }
}
