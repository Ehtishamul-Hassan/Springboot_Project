package com.java.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java.exception.NoteNotFoundException;
import com.java.model.Note;
import com.java.model.User;
import com.java.repository.NoteRepository;
import com.java.repository.UserRepository;
import com.java.service.INoteService;

@Service
public class NoteServiceImpl implements INoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Note createNoteForUser(String username, String content) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        Note note = new Note();
        note.setContent(content);
        note.setUser(user);

        return noteRepository.save(note);
    }

    @Override
    public List<Note> getUserNotes(String username) {
        return noteRepository.findByUserEmail(username);
    }

    @Override
    public Note updateNoteForUser(Long noteId, String content, String username) {
        Note note = noteRepository.findByIdAndUserEmail(noteId, username)
                .orElseThrow(() -> new IllegalArgumentException("Note not found or not owned by user"));

        note.setContent(content);
        return noteRepository.save(note);
    }

    @Override
    public void deleteNoteForUser(Long noteId, String username) {
        Note note = noteRepository.findByIdAndUserEmail(noteId, username)
                .orElseThrow(() -> new IllegalArgumentException("Note not found or not owned by user"));

        noteRepository.delete(note);
    }
}
