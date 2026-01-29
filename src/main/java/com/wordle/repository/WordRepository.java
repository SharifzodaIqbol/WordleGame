package com.wordle.repository;

import java.util.*;

public class WordRepository {
    private static final String FILE_NAME = "words.txt";
    private final Set<String> words;

    public WordRepository() {
        this.words = loadWordsFromFile();
    }

    public Set<String> loadWordsFromFile() {
        Set<String> wordsSet = new HashSet<>();
        try (var is = getClass().getClassLoader().getResourceAsStream(FILE_NAME)) {
            if (is == null) throw new RuntimeException("Файл " + FILE_NAME + " не найден");
            Scanner scanner = new Scanner(is);
            while (scanner.hasNextLine()) {
                wordsSet.add(scanner.nextLine().toUpperCase());
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки словаря", e);
        }
        return wordsSet;
    }

    public Set<String> getWords() {
        return words;
    }
}