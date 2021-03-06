package com.udacity.jwdnd.course1.cloudstorage.DAOs;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.pojos.Note;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NoteDao {

    @Resource
    private NoteMapper noteMapper;

    public int addNote(Note note, int userId) {
        return noteMapper.insert(note);
    }

    public Note[] getNoteListings(Integer userId) {
        return noteMapper.getUserNotes(userId);
    }

    public Note getNote(Integer noteId) {
        return noteMapper.getNoteById(noteId);
    }

    public Note getNote(String noteTile) {
        return noteMapper.getNoteByTitle(noteTile);
    }

    public void deleteNote(Integer noteId) {
        noteMapper.deleteNote(noteId);
    }

    public void updateNote(Note note) {
        noteMapper.updateNote(note);
    }
}
