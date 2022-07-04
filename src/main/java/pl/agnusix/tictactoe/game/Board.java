package pl.agnusix.tictactoe.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static pl.agnusix.tictactoe.game.Field.emptyField;

public class Board {
    private final List<Field> fields;

    public Board(List<Field> fields) {
        this.fields = fields;
    }
    public static Board emptyBoard(){
        List<Field> fields = new ArrayList<>();
        for (int i=0; i<9; i++){
            fields.add(emptyField());
        }
        return new Board (fields);
    }


    private boolean isMovePossible(Sign sign, Move move) {
        boolean isOccupied=fields.get(move.getField()).isOccupied();
        int occupiedFieldsCount = (int) fields.stream()
                .filter(Field::isOccupied)
                .count();
        Sign turn = occupiedFieldsCount % 2 == 0 ?
                Sign.X :
                Sign.O;
        return !isOccupied && turn.equals(sign);
    }

    public void makeMove(Sign sign , Move move) throws InvalidMoveException {
        if(!isMovePossible(sign , move)){
            throw new InvalidMoveException();
        }
        fields.get(move.getField()).set(sign);
    }


    private Optional<Sign> check(int n, int k, int l){
        List<Sign> signs = new ArrayList<>();
        for (int i = n; i< n+k; i+=l){
            if(fields.get(i).occupiedBy().isEmpty()) {
                return Optional.empty();
            }
            signs.add(fields.get(i).occupiedBy().get());
        }
        Sign firstSign = signs.get(0);
        int counter = (int) signs.stream()
                .filter(firstSign::equals)
                .count();
        return counter == 3 ? Optional.of(firstSign): Optional.empty();

    }

    public Optional<Sign> getWinner(){ //it returned Optional<Player> before
        List<Optional<Sign>> winningLines = List.of(
                check(0,3,1),   //firstLine
                check(3,3,1),   //secondLine
                check(6,3,1),   //thirdLine
                check(0,7,3),   //firstColumn
                check(1,7,3),   //secondColumn
                check(2,7,3),   //thirdColumn
                check(0, 9, 4), //rightDiagonal
                check(2, 7, 2)  //leftDiagonal
        );
        return winningLines.stream()
                .filter(Optional::isPresent)
                .findFirst()
                .orElse(Optional.empty());
    }
}
