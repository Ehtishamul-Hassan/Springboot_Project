package com.java.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserEmail(String email);

    Optional<Note> findByIdAndUserEmail(Long id, String email);
}

