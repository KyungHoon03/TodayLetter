package com.todayletter.todayletter.letter.dto;

public record LetterResponse(
        boolean hasLetter,
        Long id,
        String content,
        Long letterNumber
) {

    public static LetterResponse empty(Long letterNumber) {
        return new LetterResponse(false, null, null, letterNumber);
    }
}
