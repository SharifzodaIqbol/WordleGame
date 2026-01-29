package com.wordle.model;

public class Word {
    private final String value;

    public Word(String value) {
        this.value = value.toUpperCase();
    }

    public String getValue() {
        return value;
    }

    public char charAt(int index) {
        return value.charAt(index);
    }

    @Override
    public String toString() {
        return value;
    }
}