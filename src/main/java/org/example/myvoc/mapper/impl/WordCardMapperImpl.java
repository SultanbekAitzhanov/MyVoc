package org.example.myvoc.mapper.impl;

import org.example.myvoc.domain.WordCard;
import org.example.myvoc.domain.WordMeaning;
import org.example.myvoc.dto.WordRequestDTO;
import org.example.myvoc.mapper.WordCardMapper;
import org.example.myvoc.mapper.WordMeaningMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class WordCardMapperImpl implements WordCardMapper {
    @Autowired
    private WordMeaningMapper wordMeaningMapper;

    @Override
    public WordCard mapToEntity(WordRequestDTO dto) {
        WordCard word = new WordCard();
        word.setWord(dto.getWord());
        List<WordMeaning> meanings = dto.getMeanings().stream()
                .map(wordMeaningMapper::toEntity)
                .peek(wordMeaning -> wordMeaning.setWordCard(word))
                .collect(toList());
        word.setMeanings(meanings);
        return word;
    }
}
