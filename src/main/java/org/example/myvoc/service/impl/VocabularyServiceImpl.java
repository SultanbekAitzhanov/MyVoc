package org.example.myvoc.service.impl;

import org.example.myvoc.service.VocabularyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VocabularyServiceImpl implements VocabularyService {

    @Override
    public List<Integer> getWordGroups() {
        List<Integer> wordGroups = new ArrayList<>();
        wordGroups.add(1);
        wordGroups.add(2);
        return wordGroups;
    }
}
