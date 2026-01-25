package com.wordle.service;

import com.wordle.repository.WordRepository;

import java.util.*;

public class GameService {
    private final WordRepository repository;
    private final String secretWord;
    public GameService(WordRepository repository){
        this.repository = repository;
        this.secretWord = selectRandomWord();
    }
    private String selectRandomWord(){
        List<String> words = new ArrayList<>(repository.getWords());
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }
    public String[] compareWords(String userWord){
        String word = userWord.toUpperCase();
        String[] result = new String[5];
        boolean[] usedPlace = new boolean[5];
        for(int i = 0; i < 5; i++) {
            if(word.charAt(i) == secretWord.charAt(i)){
                result[i] = "Correct";
                usedPlace[i] = true;
            }
        }
        for(int i = 0; i < 5; i++){
            if(result[i]!=null) continue;
            for (int j = 0; j < 5; j++){
                if (!usedPlace[j] && word.charAt(i) == secretWord.charAt(j)){
                    result[i] = "Present";
                    usedPlace[j] = true;
                    break;
                }
            }
            if (result[i] == null){
                result[i] = "Absent";
            }
        }
        return result;
    }
    public boolean isWordValid(String word) {
        return word!=null && word.length()==5 && isAlpha(word) && exist(word);
    }
    public boolean isAlpha(String word){
        for (int i=0;i<word.length(); i++) {
            if(!isValid(word.charAt(i))){
                return false;
            }
        }
        return true;
    }
    public boolean isValid(char ch){
        return ch>='A' && ch<='Z' || ch >='a' && ch<='z';
    }
    public boolean exist(String word){
        return repository.getWords().contains(word);
    }
}
