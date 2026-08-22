package com.todayletter.todayletter.letter;

import com.todayletter.todayletter.domain.Letter;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterRepository extends JpaRepository<Letter, Long> {

    Optional<Letter> findByLetterNumber(Long letterNumber);
}
