package org.example.myvoc;

import org.example.myvoc.domain.WordCard;
import org.example.myvoc.dto.WordMeaningDTO;
import org.example.myvoc.dto.WordRequestDTO;
import org.example.myvoc.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private VocabularyService vocabularyService;

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("groups")
    public List<Integer> groups() {
        return vocabularyService.getWordGroups();
    }

    @GetMapping("group/{code}")
    public String wordCards(@PathVariable Integer code, Model model) {
        model.addAttribute(vocabularyService.pickFlashCard(code));
        return "flash_card";
    }

    @GetMapping("backflashcard/{id}")
    public String backFlashCard(@PathVariable UUID id,
                                Model model) {
        model.addAttribute(vocabularyService.backFlashCard(id));
        return "backflashcard";
    }

    @GetMapping("group/{code}/backflashcard/{id}/answer")
    public String answer(@PathVariable UUID id,
                         @PathVariable int code,
                         @RequestParam("known") boolean known, Model model) {
        vocabularyService.submitAnswer(id, known);
        return "redirect:/group/"+code;
    }

    @GetMapping("word")
    public String newWord(Model model) {
        WordRequestDTO wordRequestDTO = new WordRequestDTO();
        wordRequestDTO.getMeanings().add(new WordMeaningDTO());
        model.addAttribute(wordRequestDTO);
        return "word";
    }

    @PostMapping(value = "word", headers = {})
    public String newWord(WordRequestDTO wordRequestDTO) {
        return "word";
    }
}
