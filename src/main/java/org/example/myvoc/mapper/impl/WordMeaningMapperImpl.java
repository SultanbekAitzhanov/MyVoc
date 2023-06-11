package org.example.myvoc.mapper.impl;

import org.example.myvoc.domain.WordMeaning;
import org.example.myvoc.dto.WordMeaningDTO;
import org.example.myvoc.mapper.WordMeaningMapper;
import org.springframework.stereotype.Component;

@Component
public class WordMeaningMapperImpl implements WordMeaningMapper {
    @Override
    public WordMeaningDTO toDTO(WordMeaning wordMeaning) {
        return new WordMeaningDTO(
                wordMeaning.getMeaning(),
                wordMeaning.getCategory(),
                wordMeaning.getUsageExample(),
                wordMeaning.getTranslation());
    }
}
