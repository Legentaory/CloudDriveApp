package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.DAOs.NoteDao;
import com.udacity.jwdnd.course1.cloudstorage.pojos.Note;
import com.udacity.jwdnd.course1.cloudstorage.pojos.data.NoteForm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NoteService {

    @Resource
    private NoteDao noteDao;

    public String createNote(NoteForm noteForm, int userId){
        String status = "error";

        Note note = new Note();
        note.setNoteTitle(noteForm.getTitle());
        note.setNoteDescription(noteForm.getDescription());
        note.setUserId(userId);

        if (noteDao.getNote(noteForm.getTitle()) == null){
            int noteId =  noteDao.addNote(note, userId);
            status = (noteId > 0)? "success": "error";
        } else {
            noteDao.updateNote(note);
            status = "success";
        }
        return status;
    }

    public String deleteNote(int noteId){
        noteDao.deleteNote(noteId);
        return "success";
    }

    public Note[] getNotes(int userId){
        return noteDao.getNoteListings(userId);
    }

    public Note getNote(int nodeId){
        return noteDao.getNote(nodeId);
    }
}
