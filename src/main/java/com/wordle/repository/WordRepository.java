package com.wordle.repository;

import java.util.*;

public class WordRepository {
    private static final String FILE_NAME = "words.txt";
    private static final String FILE_NOT_FOUND = "Файл " + FILE_NAME + " не найден";
    private static final String ERROR_FILE = "Ошибка загрузки словаря";
    private final Set<String> words;

    public WordRepository() {
        this.words = loadWordsFromFile();
    }

    public Set<String> loadWordsFromFile() {
        Set<String> wordsSet = new HashSet<>();
        try (var is = getClass().getClassLoader().getResourceAsStream(FILE_NAME)) {
            if (is == null) throw new RuntimeException(FILE_NOT_FOUND);
            Scanner scanner = new Scanner(is);
            while (scanner.hasNextLine()) {
                wordsSet.add(scanner.nextLine().toUpperCase());
            }
        } catch (Exception e) {
            throw new RuntimeException(ERROR_FILE, e);
        }
        return wordsSet;
    }

    public Set<String> getWords() {
        return words;
    }
}