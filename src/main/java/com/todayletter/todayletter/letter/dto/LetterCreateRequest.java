package com.todayletter.todayletter.letter.dto;

import com.todayletter.todayletter.domain.LetterWriterType;

public record LetterCreateRequest(
        String content,
        Long letterNumber,
        LetterWriterType writerType
) {
}
