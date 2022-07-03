package pl.agnusix.tictactoe.game;

import java.util.Map;
import java.util.Objects;

public class Game{
    private final Map<Player, Sign> players;
    private final Board board;

    public Game(Map<Player, Sign> players, Board board) {
        this.players = players;
        this.board = board;
    }

    public void makeMove(Player player, Move move) throws InvalidMoveException {
        final var sign = players.get(player);
        if (!board.isMovePossible(sign, move)) {
            throw new InvalidMoveException();
        }
        board.makeMove(sign, move);
    }



}
