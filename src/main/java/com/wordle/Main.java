package com.wordle;

import com.wordle.repository.WordRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        WordRepository words = new WordRepository();
        List<String> list = words.getWords();
        System.out.println(list);
    }
}