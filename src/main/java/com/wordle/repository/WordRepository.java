package com.wordle.repository;
import java.util.*;
public class WordRepository {
    private final Set<String> words;
    public WordRepository(){
        this.words = loadWordsFromFile();
    }
    public Set<String> loadWordsFromFile() {
        Set<String> words = new HashSet<>();
        try(var is = getClass().getClassLoader().getResourceAsStream("words.txt")) {
            if(is == null) throw new RuntimeException("Файл words.txt не найден");
            Scanner scanner = new Scanner(is);
            while (scanner.hasNextLine()){
                words.add(scanner.nextLine());
            }
        } catch (Exception e){
            throw new RuntimeException("Ошибка загрузки словаря", e);
        }
        return words;
    }
    public Set<String> getWords(){
        return words;
    }
}
