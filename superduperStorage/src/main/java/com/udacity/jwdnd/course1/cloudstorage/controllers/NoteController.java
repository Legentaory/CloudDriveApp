package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.pojos.*;
import com.udacity.jwdnd.course1.cloudstorage.pojos.data.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.pojos.data.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.pojos.data.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.DAOs.NoteDao;
import com.udacity.jwdnd.course1.cloudstorage.DAOs.UserDao;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class NoteController {

    @Resource
    private NoteService noteService;

    @Resource
    private UserService userService;

    @PostMapping("add-note")
    public String newNote(
            Authentication authentication,
            @ModelAttribute("newNote") NoteForm newNote,
            Model model) {
        String userName = authentication.getName();
        int userId = userService.getUserId(userName);
        String result = noteService.createNote(newNote, userId);
        model.addAttribute("result", result);
        return "result";
    }

    //Leave it here, didn't find the function of this part
    @GetMapping(value = "/get-note/{noteId}")
    public Note getNote(@PathVariable Integer noteId) {
        return noteService.getNote(noteId);
    }


    //Question, how you know the id of deletion??
    @GetMapping(value = "/delete-note/{noteId}")
    public String deleteNote(
            Authentication authentication, @PathVariable Integer noteId,
            Model model) {
        String result = noteService.deleteNote(noteId);
        model.addAttribute("result", result);
        return "result";
    }
}
