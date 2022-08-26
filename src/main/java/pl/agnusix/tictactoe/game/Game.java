package pl.agnusix.tictactoe.game;



import java.util.List;
import java.util.Map;
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
    public String getOpponentNick(String uid){
        return players.keySet().stream().
                filter(player->player.getUID() != uid).
                findAny().
                get().
                getNick();
    }
    public Sign getSign(String uid){
        return players.get(uid);
    }
    public List<String> boardStatus(){
        return board.boardStatus();
    }
    public Sign getTurn(){
        return board.turn();
    }
    public Player whoMove(){
        var turn = board.turn();
        Optional<Player> player = players.entrySet().stream()
                .filter(entry -> entry.getValue().equals(turn))
                .findFirst()
                .map(Map.Entry::getKey);
        return null;
    }

    public boolean playerExist(String uid){
        return players.keySet().stream().anyMatch(player -> uid.equals(player.getUID()));
    }
    public void makeMove(String uid, Move move) throws InvalidMoveException {
        var player = players.keySet().stream().
                filter(p-> p.getUID().equals(uid)).findAny().get();
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

    @Override
    public String toString() {
        return "Game{" +
                "players=" + players +
                ", board=" + board +
                ", winner=" + winner +
                '}';
    }
}
