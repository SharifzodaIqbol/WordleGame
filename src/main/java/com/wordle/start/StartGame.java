package com.wordle.start;

import com.wordle.repository.WordRepository;
import com.wordle.service.GameService;
import com.wordle.ui.GameConsoleUI;

public class StartGame {
    public void run() {
        WordRepository repository = new WordRepository();
        GameService service = new GameService(repository);
        GameConsoleUI ui = new GameConsoleUI(service);
        ui.start();
    }
}