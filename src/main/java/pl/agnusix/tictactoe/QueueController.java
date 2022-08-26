package pl.agnusix.tictactoe;

//import static java.lang.String.format;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.agnusix.tictactoe.game.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class QueueController {
    private final GameService gameService;

    public QueueController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/register")
    public String register(@RequestParam("nick") String name){
        return gameService.addPlayer(name);
    }

    @GetMapping("/game")
    public ResponseEntity<GameStatus> gameState(@RequestParam("uid") String uid){
        var game = gameService.getPlayerGame(uid);
        if (game.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new GameStatus(
                game.get().getOpponentNick(uid),
                game.get().getSign(uid),
                game.get().getTurn(),
                game.get().getWinner().toString(),
                game.get().boardStatus()));
        //return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
    @PostMapping("/game/move")
    public ResponseEntity<String> makeMove(@RequestParam("uid") String uid, @RequestParam("field") int field){
        try {
            gameService.makeMove(uid , field);
            return ResponseEntity.ok().build();
        } catch (Exception e ) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
//    ("/game")
//            id_gry uid
//            zwraca obiekt gra <--dodac kogo kolej
//wykonywanie ruchu
//    "game/move ? id_gry move-to = pozycja & uid"

}
