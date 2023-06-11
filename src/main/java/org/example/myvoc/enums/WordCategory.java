package org.example.myvoc.enums;

public enum WordCategory {
    adjective(0), verb(1), noun(2), adverb(3);
    private final int number;
    WordCategory(int number) {
        this.number = number;
    };

    public int getNumber() {
        return number;
    }
}
