package pl.agnusix.tictactoe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public GameService gameService(){
          return new GameService(new ArrayList<>(), new ArrayList<>());
    }
}
