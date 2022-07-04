package pl.agnusix.tictactoe.game;

public class Move {
    private final int field;

    private Move(int field) {
        this.field = field;
    }
    public int getField(){
        return field;
    }

    public static Move newMove(int field) throws InvalidMoveException {
        if (field < 0 || field > 8){
            throw new InvalidMoveException();
        }
        return new Move(field);
    }
}
