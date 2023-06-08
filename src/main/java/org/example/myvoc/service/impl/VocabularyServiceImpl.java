package org.example.myvoc.service.impl;

import org.example.myvoc.repository.WordCardRepository;
import org.example.myvoc.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocabularyServiceImpl implements VocabularyService {

    @Autowired
    private WordCardRepository wordCardRepository;

    @Override
    public List<Integer> getWordGroups() {
        return wordCardRepository.getDistinctGroupsSorted();
    }
}
