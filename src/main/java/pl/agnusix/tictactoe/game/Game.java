package pl.agnusix.tictactoe.game;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Game{
    private final Map<Player, Sign> players;
    private final Board board;
    private Optional<Player> winner;

    public Game(Map<Player, Sign> players, Board board, Optional<Player> winner) {
        this.players = players;
        this.board = board;
        this.winner = winner;
    }
    public Game(Map<Player, Sign> players){
        this (players, Board.emptyBoard(), Optional.empty());
    }

    private void gameWon(Player player){
        winner = Optional.of(player);
    }
    public Optional<Player> getWinner(){
        return winner;
    }
    public boolean isGameWon(){
        return winner.isPresent();
    }
    public void makeMove(Player player, Move move) throws InvalidMoveException {
        if(isGameWon()){
            throw new InvalidMoveException();
        }
        final var sign = players.get(player);
        board.makeMove(sign, move);
        var winningSign = board.getWinner();
        if(winningSign.isPresent()){
            gameWon(player);
        }
    }



}
