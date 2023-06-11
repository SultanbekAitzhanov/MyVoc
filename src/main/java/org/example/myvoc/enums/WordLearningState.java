package org.example.myvoc.enums;

public enum WordLearningState {
    NEW_WORD(0, "NEW WORD"),
    LEARNING(1, "LEARNING"),
    MASTERED(2, "MASTERED"),
    REVIEWING(3, "REVIEWING");

    private final int priority;
    private final String translation;

    WordLearningState(int priority, String translation) {
        this.priority = priority;
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    public int getPriority() {
        return priority;
    }
}
