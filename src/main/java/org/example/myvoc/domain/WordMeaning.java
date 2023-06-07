package org.example.myvoc.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.myvoc.enums.WordCategory;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordMeaning {

    private UUID id;

    private WordCategory category;

    private String meaning;

    private String usageExample;

    private String translation;

    private WordCard wordCard;
}
