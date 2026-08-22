package com.todayletter.todayletter.letter;

import com.todayletter.todayletter.domain.Letter;
import com.todayletter.todayletter.letter.dto.LetterCreateRequest;
import com.todayletter.todayletter.letter.dto.LetterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LetterService {
    private final LetterRepository letterRepository;

    public LetterResponse readLetter(Long letterNumber) {
        return letterRepository.findByLetterNumber(letterNumber)
                .map(letter -> new LetterResponse(
                        true,
                        letter.getId(),
                        letter.getContent(),
                        letter.getLetterNumber()
                ))
                .orElse(LetterResponse.empty(letterNumber));
    }

    public LetterResponse saveLetter(LetterCreateRequest request) {

        Letter letter = Letter.builder()
                .content(request.content())
                .letterNumber(request.letterNumber())
                .writerType(request.writerType())
                .build();

        Letter savedLetter = letterRepository.save(letter);

        return new LetterResponse(
                true,
                savedLetter.getId(),
                savedLetter.getContent(),
                savedLetter.getLetterNumber()
        );
    }
}
