package org.example.myvoc.dto;

import lombok.Builder;
import lombok.Data;
import org.example.myvoc.enums.WordLearningState;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class AnswerResponseDTO {
    private UUID id;
    private String word;
    private int code;
    private String state;
    private List<WordMeaningDTO> meanings;
}
