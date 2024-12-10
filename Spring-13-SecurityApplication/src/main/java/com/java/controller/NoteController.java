package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.model.Note;
import com.java.service.INoteService;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private INoteService service;

    // Create a note for the authenticated user
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody String content,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Note createdNote = service.createNoteForUser(username, content);
        return new ResponseEntity<>( createdNote, HttpStatus.CREATED);
    }

    // Get all notes for the authenticated user
    @GetMapping
    public ResponseEntity<List<Note>> getUserNotes(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        List<Note> userNotes = service.getUserNotes(username);
        return new ResponseEntity<>(userNotes,HttpStatus.OK);
    }

    // Update a note for the authenticated user
    @PutMapping("/{noteId}")
    public ResponseEntity<Note> updateNote(@PathVariable Long noteId,
                                           @RequestBody String content,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Note updatedNote = service.updateNoteForUser(noteId, content, username);
        return new ResponseEntity<>(updatedNote, HttpStatus.OK);
    }

    // Delete a note for the authenticated user
    @DeleteMapping("/{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable Long noteId,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        service.deleteNoteForUser(noteId, username);
        return new ResponseEntity<>("Note deleted successfully", HttpStatus.OK);
    }
}
