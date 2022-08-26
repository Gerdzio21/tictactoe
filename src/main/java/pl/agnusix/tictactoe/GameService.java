package pl.agnusix.tictactoe;

import pl.agnusix.tictactoe.game.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class GameService {
    private final List<Player> players;
    private final List<Game> games;

    public GameService(List<Player> players, List<Game> games) {
        this.players = players;
        this.games = games;
    }

    public String addPlayer(String nick){
        String uid = UUID.randomUUID().toString();
        var newPlayer = new Player(uid,nick);
        players.add(newPlayer);
        System.out.println(players);
        if(players.size()>1){
            createGame(players.remove(0),players.remove(0));
        }
        return uid;
    }

    public void createGame(Player player1, Player player2){
        var newGame = new Game(Map.of(player1, Sign.X,player2, Sign.O));
        System.out.println(newGame);
        games.add(newGame);
    }
    public void makeMove(String uid,int field) throws InvalidMoveException {
        Move fieldToMove = Move.newMove(field);
        this.getPlayerGame(uid).get().makeMove(uid, fieldToMove);
    }

    public Optional<Game> getPlayerGame(String uid){
        return games.stream().filter(game -> game.playerExist(uid)).findFirst();
    }



}
