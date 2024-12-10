package com.java.service;

import java.util.List;

import com.java.model.Note;


public interface INoteService {
    Note createNoteForUser(String username, String content);

    List<Note> getUserNotes(String username);

    Note updateNoteForUser(Long noteId, String content, String username);

    void deleteNoteForUser(Long noteId, String username);
}
