package org.example.myvoc.service;

import org.example.myvoc.dto.AnswerResponseDTO;
import org.example.myvoc.dto.WordRequestDTO;
import org.example.myvoc.dto.WordResponseDTO;

import java.util.List;
import java.util.UUID;

public interface VocabularyService {
    List<Integer> getWordGroups();
    WordResponseDTO pickFlashCard(int code);

    AnswerResponseDTO backFlashCard(UUID id);

    void submitAnswer(UUID id, boolean known);

    void create(WordRequestDTO wordRequestDTO);
}
