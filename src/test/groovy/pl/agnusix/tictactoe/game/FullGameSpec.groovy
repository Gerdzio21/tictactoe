package pl.agnusix.tictactoe.game

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import spock.lang.Specification

import static org.springframework.http.HttpStatus.*

@RunWith (SpringRunner.class)
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FullGameSpec extends Specification {
    @Autowired
    private TestRestTemplate template
    def playerX
    def playerO
    def setup(){
        final def responseOne = template.exchange("/api/v1/register?nick=P1", HttpMethod.POST, HttpEntity.EMPTY,String)
        final def responseTwo = template.exchange("/api/v1/register?nick=P2", HttpMethod.POST, HttpEntity.EMPTY,String)
        playerX = responseOne.body;
        playerO = responseTwo.body;
    }

    def "X move first"(){
        when:
            final def response = template.exchange(
                String.format("/api/v1/game/move?uid=%s&field=%d", playerX, 1),
                HttpMethod.POST,
                HttpEntity.EMPTY,String)
        then:
            response.statusCode == OK
    }

    def "X can't move as second"(){
        when:
        final def response = template.exchange(
                String.format("/api/v1/game/move?uid=%s&field=%d", playerX, 1),
                HttpMethod.POST,
                HttpEntity.EMPTY,String)
        final def secondResponse = template.exchange(
                String.format("/api/v1/game/move?uid=%s&field=%d", playerX, 2),
                HttpMethod.POST,
                HttpEntity.EMPTY,String)
        then:
            response.statusCode == OK
            secondResponse.statusCode == BAD_REQUEST
    }
    def "opponent can't go to the occupied field"(){
        when:
        final def response = template.exchange(
                String.format("/api/v1/game/move?uid=%s&field=%d", playerX, 1),
                HttpMethod.POST,
                HttpEntity.EMPTY,String)
        final def secondResponse = template.exchange(
                String.format("/api/v1/game/move?uid=%s&field=%d", playerO, 1),
                HttpMethod.POST,
                HttpEntity.EMPTY,String)
        then:
            response.statusCode == OK
            secondResponse.statusCode == BAD_REQUEST
    }
    def "opponent can't make move after ended game"(){
        when:
        final def response = template.exchange(
                String.format("/api/v1/game/move?uid=%s&field=%d", playerX, 0),
                HttpMethod.POST,
                HttpEntity.EMPTY,String)
        final def secondResponse = template.exchange(
                String.format("/api/v1/game/move?uid=%s&field=%d", playerO, 3),
                HttpMethod.POST,
                HttpEntity.EMPTY,String)
        final def thirdResponse = template.exchange(
                String.format("/api/v1/game/move?uid=%s&field=%d", playerX, 1),
                HttpMethod.POST,
                HttpEntity.EMPTY,String)
        final def fourthResponse = template.exchange(
                String.format("/api/v1/game/move?uid=%s&field=%d", playerO, 4),
                HttpMethod.POST,
                HttpEntity.EMPTY,String)
        final def fifthResponse = template.exchange(
                String.format("/api/v1/game/move?uid=%s&field=%d", playerX, 2),
                HttpMethod.POST,
                HttpEntity.EMPTY,String)
        final def sixthResponse = template.exchange(
                String.format("/api/v1/game/move?uid=%s&field=%d", playerO, 5),
                HttpMethod.POST,
                HttpEntity.EMPTY,String)
        then:
            sixthResponse.statusCode == BAD_REQUEST
    }

}
