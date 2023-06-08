package org.example.myvoc;

import org.example.myvoc.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
