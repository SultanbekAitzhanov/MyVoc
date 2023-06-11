package org.example.myvoc.service.impl;

import org.example.myvoc.domain.WordCard;
import org.example.myvoc.dto.AnswerResponseDTO;
import org.example.myvoc.dto.GroupStatisticsDTO;
import org.example.myvoc.dto.WordRequestDTO;
import org.example.myvoc.dto.WordResponseDTO;
import org.example.myvoc.enums.WordLearningState;
import org.example.myvoc.mapper.WordCardMapper;
import org.example.myvoc.mapper.WordMeaningMapper;
import org.example.myvoc.repository.WordCardRepository;
import org.example.myvoc.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class VocabularyServiceImpl implements VocabularyService {

    @Autowired
    private WordCardRepository wordCardRepository;
    @Autowired
    private WordMeaningMapper wordMeaningMapper;
    @Autowired
    private WordCardMapper wordCardMapper;
    private static final String WORD_NOT_FOUND_ERROR_MESSAGE = "Введенное слово не найдено среди сохраненных";

    @Override
    public List<Integer> getWordGroups() {
        return wordCardRepository.getDistinctGroupsSorted();
    }

    @Override
    public WordResponseDTO pickFlashCard(int code) {
        List<WordCard> cards = wordCardRepository.getWordCardsByCodeOrderByStateDescLastTimePickedDesc(code).stream()
                .sorted(Comparator
                        .comparing(WordCard::getLastTimePicked))
                .limit(3)
                .collect(Collectors.toList());
        WordCard pickedCard = cards.get(0);
        wordCardRepository.save(pickedCard);
        GroupStatisticsDTO statisticsDTO = wordCardRepository.getStatisticsByGroupCode(code);
        return mapToWordResponse(pickedCard, statisticsDTO);
    }

    @Override
    public AnswerResponseDTO backFlashCard(UUID id) {
        return wordCardRepository.findById(id).map(this::mapToAnswerResponse).orElseThrow(
                () -> new RuntimeException(WORD_NOT_FOUND_ERROR_MESSAGE));
    }

    @Override
    public void submitAnswer(UUID id, boolean known) {
        WordCard found = wordCardRepository.findById(id).orElseThrow(
                () -> new RuntimeException(WORD_NOT_FOUND_ERROR_MESSAGE));
        updateWordStatus(found, known);
        wordCardRepository.save(found);
    }

    @Override
    public void create(WordRequestDTO dto) {
        if (wordCardRepository.existsByWord(dto.getWord())){return;}
        WordCard word = wordCardMapper.mapToEntity(dto);
        word.setId(UUID.randomUUID());
        wordCardRepository.create(word);
    }

    private void updateWordStatus(WordCard word, boolean answerResult){
        switch (word.getState()) {
            case NEW_WORD:
            case LEARNING:
                if (answerResult) {
                    word.setState(WordLearningState.MASTERED);
                } else {
                    word.setState(WordLearningState.LEARNING);
                }
                break;
            case MASTERED:
            case REVIEWING:
                if (answerResult) {
                    word.setState(WordLearningState.REVIEWING);
                } else {
                    word.setState(WordLearningState.LEARNING);
                }
                break;
        }
    }

    private WordResponseDTO mapToWordResponse(WordCard wordCard, GroupStatisticsDTO statisticsDTO) {
        return new WordResponseDTO(wordCard.getId(), wordCard.getWord(), wordCard.getState().getTranslation(), statisticsDTO);
    }

    private AnswerResponseDTO mapToAnswerResponse(WordCard found) {
        return AnswerResponseDTO.builder()
                .id(found.getId())
                .word(found.getWord())
                .code(found.getCode())
                .state(found.getState().getTranslation())
                .meanings(found.getMeanings().stream().map(wordMeaningMapper::toDTO).collect(toList()))
                .build();
    }
}
