package org.example.myvoc.enums;

public enum WordLearningState {
    LEARNING(1),
    NEW_WORD(0),
    MASTERED(2),
    REVIEWING(3);

    private final int priority;

    WordLearningState(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
