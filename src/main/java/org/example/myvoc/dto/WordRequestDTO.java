package org.example.myvoc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class WordRequestDTO {
    private String word;
    private List<WordMeaningDTO> meanings = new ArrayList<>();

    public void setMeanings(List<WordMeaningDTO> meanings) {
        this.meanings = meanings;
    }

    public List<WordMeaningDTO> getMeanings() {
        return meanings;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
