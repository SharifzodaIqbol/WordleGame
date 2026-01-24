package com.wordle;

import com.wordle.repository.WordRepository;
import com.wordle.service.GameService;
import com.wordle.ui.GameConsoleUI;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        WordRepository words = new WordRepository();
        List<String> list = words.getWords();
        System.out.println(list);
    }
}