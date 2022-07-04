package pl.agnusix.tictactoe.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static pl.agnusix.tictactoe.game.Field.emptyField;

public class Board {
    private List<Field> fields;

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

    private Optional<Sign> isFullHorizontaly(int n){
        List<Sign> signs = new ArrayList<>();
        for (int i = n; i< n+3; i++){
            if(fields.get(i).occupiedBy().isEmpty()) {
                return Optional.empty();
            }
            signs.add(fields.get(i).occupiedBy().get());
        }
        Sign firstSign = signs.get(0);
        int counter = (int) signs.stream().filter(sign -> firstSign.equals(sign)).count();
        return counter == 3 ? Optional.of(firstSign): Optional.empty(); //CO KIEDY REMIS?
    }
    private Optional<Sign> isFullVerticaly(int n){
        List<Sign> signs = new ArrayList<>();
        for (int i = n; i< n+7; i+=3){
            if(fields.get(i).occupiedBy().isEmpty()) {
                return Optional.empty();
            }
            signs.add(fields.get(i).occupiedBy().get());
        }
        Sign firstSign = signs.get(0);
        int counter = (int) signs.stream().filter(sign -> firstSign.equals(sign)).count();
        return counter == 3 ? Optional.of(firstSign): Optional.empty(); //CO KIEDY REMIS?
    }
    private Optional<Sign> isFullRightDiagonaly(int n){
        List<Sign> signs = new ArrayList<>();
        for (int i = n; i< n+9; i+=4){
            if(fields.get(i).occupiedBy().isEmpty()) {
                return Optional.empty();
            }
            signs.add(fields.get(i).occupiedBy().get());
        }
        Sign firstSign = signs.get(0);
        int counter = (int) signs.stream().filter(sign -> firstSign.equals(sign)).count();
        return counter == 3 ? Optional.of(firstSign): Optional.empty(); //CO KIEDY REMIS?
    }
    private Optional<Sign> isFullLeftDiagonaly(int n){
        List<Sign> signs = new ArrayList<>();
        for (int i = n; i< n+9; i+=2){
            if(fields.get(i).occupiedBy().isEmpty()) {
                return Optional.empty();
            }
            signs.add(fields.get(i).occupiedBy().get());
        }
        Sign firstSign = signs.get(0);
        int counter = (int) signs.stream().filter(sign -> firstSign.equals(sign)).count();
        return counter == 3 ? Optional.of(firstSign): Optional.empty(); //CO KIEDY REMIS?
    }

    public Optional<Player> getWinner(){
        isFullHorizontaly(0);
        isFullHorizontaly(3);
        isFullHorizontaly(6);
        isFullVerticaly(0);
        isFullVerticaly(1);
        isFullVerticaly(2);
        isFullLeftDiagonaly(2);
        isFullRightDiagonaly(0);

    }

}
