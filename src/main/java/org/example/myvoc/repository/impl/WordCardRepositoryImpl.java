package org.example.myvoc.repository.impl;

import org.example.myvoc.domain.WordCard;
import org.example.myvoc.domain.WordMeaning;
import org.example.myvoc.dto.GroupDTO;
import org.example.myvoc.dto.GroupStatisticsDTO;
import org.example.myvoc.enums.WordCategory;
import org.example.myvoc.enums.WordLearningState;
import org.example.myvoc.repository.WordCardRepository;
import org.example.myvoc.repository.WordMeaningRepostiory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class WordCardRepositoryImpl implements WordCardRepository, InitializingBean {

    private final String SELECT_GROUP_CODES_WITH_MASTERED_STATS = "SELECT t.code, COALESCE(m.mastered, 0) AS mastered, t.total\n" +
            "FROM (SELECT wc.code, count(*) as mastered\n" +
            "FROM word_card wc\n" +
            "WHERE wc.state = '3'\n" +
            "GROUP BY wc.code) as m\n" +
            "RIGHT JOIN (SELECT wc.code, count(*) as total\n" +
            "      FROM word_card wc\n" +
            "      GROUP BY wc.code) as t ON m.code = t.code " +
            "ORDER BY t.code";
    private final String SELECT_WORD_CARDS_BY_GROUP_CODE =
            "SELECT * " +
            "FROM word_card wc " +
            "WHERE wc.code = :code " +
            "ORDER BY wc.state ASC, wc.last_time_picked DESC " +
            "LIMIT 10";
    private final String UPDATE_LAST_TIME_PICKED = "UPDATE word_card SET last_time_picked = now(), state = :state WHERE id = :id";
    private final String SELECT_STATISTICS = "SELECT\n" +
            "    (SELECT COUNT(*)\n" +
            "        FROM word_card wc\n" +
            "        WHERE state = '1' AND wc.code = :code) AS learning,\n" +
            "    (SELECT COUNT(*)\n" +
            "     FROM word_card wc\n" +
            "     WHERE state = '2' AND wc.code = :code) AS mastered,\n" +
            "    (SELECT COUNT(*)\n" +
            "     FROM word_card wc\n" +
            "     WHERE state = '3' AND wc.code = :code) AS reviewing,\n" +
            "    (SELECT COUNT(*)\n" +
            "     FROM word_card wc\n" +
            "     WHERE wc.code = :code) AS total";
    private static final String SELECT_BY_ID = "SELECT * FROM word_card wc WHERE wc.id = :id";
    private static final String SELECT_MEANINGS_BY_ID = "SELECT wm.* FROM word_card wc INNER JOIN word_meaning wm ON wm.word_card_id = wc.id WHERE wc.id = :id";
    private static Long wordCardCount;
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private WordMeaningRepostiory wordMeaningRepostiory;

    private Long count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) AS count FROM word_card", new HashMap<String,Object>(), Long.class);
    }

    @Override
    public List<GroupDTO> getDistinctGroupsSorted() {
        List<GroupDTO> groupDTOList = new ArrayList<>();
        jdbcTemplate.query(SELECT_GROUP_CODES_WITH_MASTERED_STATS, rs -> {
            GroupDTO groupDTO = new GroupDTO();
            groupDTO.setCode(rs.getInt("code"));
            groupDTO.setTotal(rs.getInt("total"));
            groupDTO.setMastered(rs.getInt("mastered"));
            groupDTOList.add(groupDTO);
        });
        return groupDTOList;
    }

    @Override
    public List<WordCard> getWordCardsByCodeOrderByStateDescLastTimePickedDesc(int code) {
        List<WordCard> wordCardList = new ArrayList<>();
        Map<String, Object> argumentsMap = new HashMap<>();
        argumentsMap.put("code", code);
        jdbcTemplate.query(SELECT_WORD_CARDS_BY_GROUP_CODE, argumentsMap, rs -> {
            WordCard wordCard = new WordCard();
            wordCard.setWord(rs.getString("word"));
            wordCard.setState(WordLearningState.values()[(rs.getInt("state"))]);
            wordCard.setId(UUID.fromString(rs.getString("id")));
            wordCard.setLastTimePicked(rs.getTimestamp("last_time_picked").toInstant());
            wordCard.setCode(rs.getInt("code"));
            wordCardList.add(wordCard);
        });
        return wordCardList;
    }

    @Override
    public void save(WordCard pickedCard) {
        Map<String, Object> argumentsMap = new HashMap<>();
        argumentsMap.put("id", pickedCard.getId());
        argumentsMap.put("state", pickedCard.getState().getPriority());
        jdbcTemplate.update(UPDATE_LAST_TIME_PICKED, argumentsMap);
    }

    @Override
    public GroupStatisticsDTO getStatisticsByGroupCode(int code) {
        GroupStatisticsDTO dto = new GroupStatisticsDTO();
        Map<String, Object> argumentsMap = new HashMap<>();
        argumentsMap.put("code", code);
        jdbcTemplate.query(SELECT_STATISTICS, argumentsMap, rs -> {
            dto.setTotal(rs.getInt("total"));
            dto.setMasteredCount(rs.getInt("mastered"));
            dto.setLearningCount(rs.getInt("learning"));
            dto.setReviewingCount(rs.getInt("reviewing"));
        });
        return dto;
    }

    @Override
    public Optional<WordCard> findById(UUID id) {
        Map<String, Object> argumentsMap = new HashMap<>();
        argumentsMap.put("id", id);
        Optional<WordCard> found = Optional.ofNullable(jdbcTemplate.query(SELECT_BY_ID, argumentsMap, rs -> {
            WordCard wc = new WordCard();
            while(rs.next()) {
                wc.setId(UUID.fromString(rs.getString("id")));
                wc.setWord(rs.getString("word"));
                wc.setCode(rs.getInt("code"));
                wc.setState(WordLearningState.values()[(rs.getInt("state"))]);
                wc.setLastTimePicked(rs.getTimestamp("last_time_picked").toInstant());
            }
            return wc;
        }));
        found.ifPresent(wc -> {
            wc.setMeanings(getMeaningsById(wc.getId()));
        });
        return found;
    }

    @Override
    public boolean existsByWord(String word) {
        Map<String, Object> params = new HashMap<>();
        params.put("word", word);
        return Boolean.TRUE.equals(jdbcTemplate.query("SELECT EXISTS (SELECT * FROM word_card wc WHERE wc.word = :word) AS exists", params, rs -> {
            if(rs.next()) {
                return rs.getBoolean("exists");
            }
            return false;
        }));
    }

    @Override
    @Transactional
    public void create(WordCard word) {
        word.setState(WordLearningState.NEW_WORD);
        word.setCode(Long.valueOf(Math.floorDiv(++wordCardCount, 30)).intValue());
        Map<String, Object> params = new HashMap<>();
        params.put("id", word.getId());
        params.put("word", word.getWord());
        params.put("state", word.getState().getPriority());
        params.put("code", word.getCode());
        jdbcTemplate.update("INSERT INTO word_card (id, word, state, last_time_picked, code) " +
                "VALUES (:id, :word, :state, now(), :code)", params);
        wordMeaningRepostiory.saveAll(word.getMeanings());
    }

    private List<WordMeaning> getMeaningsById(UUID id) {
        List<WordMeaning> meanings = new ArrayList<>();
        Map<String, Object> argumentsMap = new HashMap<>();
        argumentsMap.put("id", id);
        jdbcTemplate.query(SELECT_MEANINGS_BY_ID, argumentsMap, resultSet -> {
            WordMeaning wm = new WordMeaning();
            wm.setMeaning(resultSet.getString("meaning"));
            wm.setUsageExample(resultSet.getString("example"));
            wm.setCategory(WordCategory.values()[(resultSet.getInt("word_category"))]);
            wm.setTranslation(resultSet.getString("translation"));
            meanings.add(wm);
        });
        return meanings;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.wordCardCount = count();
    }
}
