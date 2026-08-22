package com.todayletter.todayletter.letter;

import com.todayletter.todayletter.letter.dto.LetterCreateRequest;
import com.todayletter.todayletter.letter.dto.LetterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/letter")
@RequiredArgsConstructor
public class LetterController {
    private final LetterService letterService;

    @GetMapping("/new")
    public String writeLetterPage(@RequestParam(defaultValue = "USER") String writerType, Model model) {
        model.addAttribute("writerType", writerType);

        return "letter/new";
    }

    @PostMapping
    public String saveLetter(LetterCreateRequest request) {
        LetterResponse letterResponse = letterService.saveLetter(request);

        return "redirect:/letter/" + letterResponse.letterNumber();
    }

    @GetMapping("/{letterNumber}")
    public String openDrawer(@PathVariable Long letterNumber, Model model) {
        Long selectedLetterNumber = Math.max(letterNumber, 1L);
        LetterResponse letterResponse = letterService.readLetter(selectedLetterNumber);

        model.addAttribute("drawerNumber", letterResponse.letterNumber());
        model.addAttribute("hasLetter", letterResponse.hasLetter());
        model.addAttribute("content", letterResponse.content());

        return "letter/show";
    }
}
