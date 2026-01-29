package com.wordle.ui;

import com.wordle.model.Word;
import com.wordle.service.GameService;
import java.util.Scanner;

public class GameConsoleUI {
    private static final String WELCOME = "--- Новая игра Wordle! ---";
    private static final String PROMPT = "Попытка %d/6. Введите слово (или 'exit' для выхода): ";
    private static final String ERROR = "Ошибка: 5 латинских букв, которые есть в словаре!";
    private static final String WIN = "Поздравляем! Вы угадали!";
    private static final String LOSE = "Вы проиграли. Было загадано: ";
    private static final String EXIT_CMD = "exit";

    private final GameService gameService;
    private final Scanner input = new Scanner(System.in);

    public GameConsoleUI(GameService gameService) {
        this.gameService = gameService;
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    public void start() {
        while (true) {
            gameService.prepareNewGame();
            if (!playRound()) break;
        }
    }

    private boolean playRound() {
        printMessage(WELCOME);
        int counter = 1;
        while (counter <= 6) {
            printMessage(String.format(PROMPT, counter));
            String rawInput = input.nextLine();

            if (rawInput.equalsIgnoreCase(EXIT_CMD)) return false;

            if (!gameService.isWordValid(rawInput)) {
                printMessage(ERROR);
                continue;
            }

            Word userWord = new Word(rawInput);
            String[] result = gameService.compareWords(userWord);

            for (int i = 0; i < 5; i++) {
                printMessage(userWord.charAt(i) + ": " + result[i]);
            }

            if (gameService.isWin(result)) {
                printMessage(WIN);
                return true;
            }
            counter++;
        }
        printMessage(LOSE + gameService.getSecretWord());
        return true;
    }
}