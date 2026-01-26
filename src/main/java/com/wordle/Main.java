package com.wordle;

import com.wordle.repository.WordRepository;
import com.wordle.service.GameService;
import com.wordle.ui.GameConsoleUI;

public class Main {
    public static void main(String[] args) {

        WordRepository repository = new WordRepository();
        GameService service = new GameService(repository);
        GameConsoleUI ui = new GameConsoleUI(service);
        ui.start();
    }
}