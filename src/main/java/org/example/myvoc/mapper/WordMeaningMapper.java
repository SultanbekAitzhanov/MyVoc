package org.example.myvoc.mapper;

import org.example.myvoc.domain.WordMeaning;
import org.example.myvoc.dto.WordMeaningDTO;

public interface WordMeaningMapper {
    WordMeaningDTO toDTO(WordMeaning meaning);

    WordMeaning toEntity(WordMeaningDTO wordMeaningDTO);
}
