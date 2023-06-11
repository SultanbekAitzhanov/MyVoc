package org.example.myvoc.repository;

import org.example.myvoc.domain.WordMeaning;

import java.util.List;

public interface WordMeaningRepostiory {
    void saveAll(List<WordMeaning> meaningList);
}
