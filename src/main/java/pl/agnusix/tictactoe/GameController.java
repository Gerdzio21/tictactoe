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
@CrossOrigin(origins ="http://localhost:4200")
public class QueueController {
    private final GameService gameService;

    public QueueController(GameService gameService) {
        this.gameService = gameService;
    }

    public static class UserRegistrationParams {
        private String nick;

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }
    }

    public static class UserParams {
        private String uid;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }

    public static class MovePostParams {
        private String uid;
        private int field;
        public String getUid() {
            return uid;
        }
        public int getField() {
            return field;
        }
        public void setUid(String uid) {
            this.uid = uid;
        }
        public void setField(int field) {
            this.field = field;
        }
    }





    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<UserParams> register(@RequestBody UserRegistrationParams request){
        UserParams response = new UserParams();
        response.setUid(gameService.addPlayer(request.getNick()));
        return ResponseEntity.ok(response);
        //return ResponseEntity.ok(gameService.addPlayer(request.getNick()));
    }

    @CrossOrigin
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
    public ResponseEntity<String> makeMove(@RequestBody MovePostParams request) {
        try {
            gameService.makeMove(request.getUid(), request.getField());
            return ResponseEntity.ok().build();
        } catch (Exception e ) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/game")
    public ResponseEntity<String> deleteGame(@RequestBody UserParams request){
        try{
            gameService.deleteGame(request.getUid());
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }



}
