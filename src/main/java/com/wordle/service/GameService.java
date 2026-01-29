package com.wordle.service;

import com.wordle.model.Word;
import com.wordle.repository.WordRepository;
import java.util.*;

public class GameService {
    private static final String CORRECT = "Correct";
    private static final String PRESENT = "Present";
    private static final String ABSENT = "Absent";
    private static final int WORD_LENGTH = 5;

    private final WordRepository repository;
    private Word secretWord;

    public GameService(WordRepository repository) {
        this.repository = repository;
    }

    public void prepareNewGame() {
        this.secretWord = selectRandomWord();
    }

    private Word selectRandomWord() {
        List<String> words = new ArrayList<>(repository.getWords());
        return new Word(words.get(new Random().nextInt(words.size())));
    }

    public String[] compareWords(Word userWord) {
        String userVal = userWord.getValue();
        String secretVal = secretWord.getValue();
        String[] result = new String[WORD_LENGTH];
        boolean[] usedPlace = new boolean[WORD_LENGTH];

        for (int i = 0; i < WORD_LENGTH; i++) {
            if (userVal.charAt(i) == secretVal.charAt(i)) {
                result[i] = CORRECT;
                usedPlace[i] = true;
            }
        }

        for (int i = 0; i < WORD_LENGTH; i++) {
            if (result[i] != null) continue;
            for (int j = 0; j < WORD_LENGTH; j++) {
                if (!usedPlace[j] && userVal.charAt(i) == secretVal.charAt(j)) {
                    result[i] = PRESENT;
                    usedPlace[j] = true;
                    break;
                }
            }
            if (result[i] == null) result[i] = ABSENT;
        }
        return result;
    }

    public boolean isWordValid(String word) {
        return word != null && word.length() == WORD_LENGTH && isAlpha(word) && exist(word);
    }

    public boolean isAlpha(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!isValid(word.charAt(i))) return false;
        }
        return true;
    }

    public boolean isValid(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    public boolean exist(String word) {
        return repository.getWords().contains(word.toUpperCase());
    }

    public boolean isWin(String[] result) {
        for (String s : result) {
            if (!s.equals(CORRECT)) return false;
        }
        return true;
    }

    public Word getSecretWord() {
        return secretWord;
    }
}