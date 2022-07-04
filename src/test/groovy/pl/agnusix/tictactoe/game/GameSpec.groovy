package pl.agnusix.tictactoe.game

import spock.lang.Specification

import static pl.agnusix.tictactoe.game.Move.newMove
import static pl.agnusix.tictactoe.game.Sign.*

class GameSpec extends Specification {
    def 'is player X won game when X won'(){
        given:
            Player player1 = new Player("Player X")
            Player player2 = new Player("Player O")
            Game game = new Game(Map.of(player1, X, player2, O))
        when:
            game.makeMove(player1, newMove(0))
            game.makeMove(player2, newMove(3))
            game.makeMove(player1, newMove(1))
            game.makeMove(player2, newMove(4))
            game.makeMove(player1, newMove(2))
            def xWon = game.isGameWon();
        then:
            xWon
    }
    def 'is not possible to move after game ending'(){
        given:
            Player player1 = new Player("Player X")
            Player player2 = new Player("Player O")
            Game game = new Game(Map.of(player1, X, player2, O))
        when:
            game.makeMove(player1, newMove(0))
            game.makeMove(player2, newMove(3))
            game.makeMove(player1, newMove(1))
            game.makeMove(player2, newMove(4))
            game.makeMove(player1, newMove(2))
            game.makeMove(player2, newMove(5))
        then:
            thrown(InvalidMoveException)
    }
}
