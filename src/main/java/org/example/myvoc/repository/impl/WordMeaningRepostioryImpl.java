package org.example.myvoc.repository.impl;

import org.example.myvoc.domain.WordMeaning;
import org.example.myvoc.repository.WordMeaningRepostiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class WordMeaningRepostioryImpl implements WordMeaningRepostiory {

    private final String INSERT_WORD_MEANING = "INSERT INTO word_meaning (id, word_category, meaning, example, word_card_id, translation) " +
            "VALUES (:id, :word_category, :meaning, :example, :word_card_id, :translation)";
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void saveAll(List<WordMeaning> meaningList) {
        for (WordMeaning wordMeaning: meaningList) {
            Map<String, Object> params = new HashMap<>();

            params.put("id",UUID.randomUUID());
            params.put("word_category",wordMeaning.getCategory().getNumber());
            params.put("meaning",wordMeaning.getMeaning());
            params.put("example",wordMeaning.getUsageExample());
            params.put("word_card_id",wordMeaning.getWordCard().getId());
            params.put("translation",wordMeaning.getTranslation());

            jdbcTemplate.update(INSERT_WORD_MEANING, params);
        }
    }
}
