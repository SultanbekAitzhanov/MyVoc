package org.example.myvoc.repository;

import org.example.myvoc.domain.WordCard;
import org.example.myvoc.dto.GroupStatisticsDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WordCardRepository {

    List<Integer> getDistinctGroupsSorted();

    List<WordCard> getWordCardsByCodeOrderByStateDescLastTimePickedDesc(int code);

    void save(WordCard pickedCard);

    GroupStatisticsDTO getStatisticsByGroupCode(int code);

    Optional<WordCard> findById(UUID id);
}
