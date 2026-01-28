package com.wordle.service;
import com.wordle.repository.WordRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class GameServiceTest {
    @Test
    void testIsWin(){
        WordRepository repository = new WordRepository();
        GameService gameService = new GameService(repository);
        String[] result = {"Correct", "Correct", "Correct", "Correct", "Correct"};
        assertTrue(gameService.isWin(result));
    }
}
