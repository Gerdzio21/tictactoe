package pl.agnusix.tictactoe.game

import pl.agnusix.tictactoe.GameService
import pl.agnusix.tictactoe.MissingObjectException
import spock.lang.Specification

import static pl.agnusix.tictactoe.game.Move.newMove
import static pl.agnusix.tictactoe.game.Sign.O
import static pl.agnusix.tictactoe.game.Sign.X

class GameServiceSpec extends Specification {
    def 'is not possible to delete game after deleting it'(){
        given:
            GameService gameService = new GameService(new ArrayList<>(), new ArrayList<>())
        when:
            def uidX = gameService.addPlayer("Player X")
            def uidO =gameService.addPlayer("Player O")
            gameService.deleteGame(uidX)
            gameService.deleteGame(uidO)
        then:
            thrown(MissingObjectException)
    }
}
