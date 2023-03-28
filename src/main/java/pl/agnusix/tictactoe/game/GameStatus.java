package pl.agnusix.tictactoe.game;

import java.util.List;

public record GameStatus(
        String opponentNick,
        Sign mySign,
        Sign turnSign,
        String winner,
        List<String> board) {
}
