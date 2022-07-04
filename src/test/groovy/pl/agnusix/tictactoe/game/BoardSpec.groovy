package pl.agnusix.tictactoe.game

import spock.lang.Specification

import static pl.agnusix.tictactoe.game.Sign.O
import static pl.agnusix.tictactoe.game.Sign.X

class BoardSpec extends Specification {
    def board
    def setup (){
        board = Board.emptyBoard()
    }

    def 'move is possible when X is first move'() {
        when:
        board.makeMove(X, Move.newMove(0))
        then:
        noExceptionThrown()
    }

    def 'move is not possible when O is first move'() {
        when:
        board.makeMove(O, Move.newMove(0))
        then:
        thrown(InvalidMoveException)
    }
    def 'it is not possible to move twice in row by one player'(){
        when:
        // def firstMovePossibility = board.isMovePossible(X, Move.newMove(4));
        board.makeMove(X, Move.newMove(0))
        board.makeMove(X, Move.newMove(1))
        // def secondMovePossibility = board.isMovePossible(X, Move.newMove(5));
        then:
//        firstMovePossibility
//        !secondMovePossibility
        thrown(InvalidMoveException)
    }

    def 'it is possible to play simple game' (){
        given:
        def firstMove = Move.newMove(1)
        def secondMove = Move.newMove(2)
        def thirdMove = Move.newMove(3)
        when:

        //def firstMovePossibility = board.isMovePossible(X, firstMove)
        board.makeMove(X, firstMove)
        //def secondMovePossibility = board.isMovePossible(O, secondMove)
        board.makeMove(O, secondMove)
        //def thirdMovePossibility = board.isMovePossible(X, thirdMove)
        board.makeMove(X, thirdMove)

        then:
//        firstMovePossibility
//        secondMovePossibility
//        thirdMovePossibility
        noExceptionThrown()
    }
    def 'it is not possible to override the occupied field' (){
        given:
        def move = Move.newMove(1)
        when:
            board.makeMove(X, move)
            board.makeMove(O, move)
        then:
            thrown(InvalidMoveException)
    }
}
