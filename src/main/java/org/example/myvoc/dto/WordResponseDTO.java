package org.example.myvoc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.myvoc.enums.WordLearningState;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordResponseDTO {
    private UUID id;
    private String word;
    private String state;
    private GroupStatisticsDTO statistics;
}
