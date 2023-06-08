package org.example.myvoc.repository.impl;

import org.example.myvoc.repository.WordCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WordCardRepositoryImpl implements WordCardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Integer> getDistinctGroupsSorted() {
        List<Integer> groupCodes = new ArrayList<>();
        String sql = "SELECT DISTINCT wc.code FROM word_card wc GROUP BY wc.code ORDER BY wc.code ASC";
        jdbcTemplate.query(sql, resultSet -> {
            int code = resultSet.getInt("code");
            groupCodes.add(code);
        });
        return groupCodes;
    }
}
