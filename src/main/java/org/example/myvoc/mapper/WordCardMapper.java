package org.example.myvoc.mapper;

import org.example.myvoc.domain.WordCard;
import org.example.myvoc.dto.WordRequestDTO;

public interface WordCardMapper {

    WordCard mapToEntity(WordRequestDTO dto);
}
