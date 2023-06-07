package org.example.myvoc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.myvoc.enums.WordLearningState;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class WordCard {

    private UUID id;

    private String word;

    private List<WordMeaning> meanings;

    private WordLearningState state;

    private Instant lastTimePicked;

    private Integer code;

    public int getStatePriority() {
        return this.getState().getPriority();
    }
}