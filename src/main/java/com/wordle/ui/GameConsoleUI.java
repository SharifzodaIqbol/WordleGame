package com.wordle.ui;

import com.wordle.service.GameService;

import java.util.Scanner;

public class GameConsoleUI {
    private final GameService gameService;
    private final Scanner input = new Scanner(System.in);
    public GameConsoleUI(GameService gameService){
        this.gameService = gameService;
    }
    public void start(){
        System.out.println("Добро пожаловать в игру Wordle!");
        int counter = 1;
        while(counter<=6){
            System.out.println("Попытка " + counter + "/6.Введите слово из 5 букв:");
            String word = input.nextLine();
            if (!gameService.isWordValid(word)){
                System.out.println("Ошибка: Слово должно быть из 5 латинских букв и существовать в словаре!");
                continue;
            }
            String[] result = gameService.compareWords(word);
            printStepResult(result, word);
            if (gameService.isWin(result)){
                System.out.println("Поздравляем! Вы угадали слово!");
                return;
            }
            counter++;
        }
        System.out.println("Попытки закончились. Вы проиграли!");
    }
    public void printStepResult(String[] result, String word){
        for (int i = 0; i < 5; i++){
            System.out.println(word.charAt(i) + ": " + result[i]);
        }
    }
}
