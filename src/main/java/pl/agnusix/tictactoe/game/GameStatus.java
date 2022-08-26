package pl.agnusix.tictactoe.game;

import java.util.List;

public record GameStatus(
        String opponentUsername,
        Sign sign,
        Sign turn,
        String winner,
        List<String> board) {
}
