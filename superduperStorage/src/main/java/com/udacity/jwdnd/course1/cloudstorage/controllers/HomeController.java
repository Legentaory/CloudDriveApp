package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.pojos.data.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.pojos.data.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.pojos.data.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.pojos.User;
import com.udacity.jwdnd.course1.cloudstorage.DAOs.CredentialDao;
import com.udacity.jwdnd.course1.cloudstorage.DAOs.FileDao;
import com.udacity.jwdnd.course1.cloudstorage.DAOs.NoteDao;
import com.udacity.jwdnd.course1.cloudstorage.DAOs.UserDao;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.services.securityservices.EncryptionService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Resource
    private FileService fileService;
    @Resource
    private UserService userService;
    @Resource
    private CredentialService credentialService;
    @Resource
    private NoteService noteService;
    @Resource
    private EncryptionService encryptionService;

    @GetMapping
    public String getHomePage(
            Authentication authentication,
            @ModelAttribute("fileForm") FileForm newFile,
            @ModelAttribute("noteForm") NoteForm newNote,
            @ModelAttribute("credentialForm") CredentialForm newCredential,
            Model model) {
        int userId = userService.getUserId(authentication.getName());
        model.addAttribute("files", fileService.getFileListings(userId));
        model.addAttribute("notes", noteService.getNotes(userId));
        model.addAttribute("credentials", credentialService.getCredentials(userId));
        model.addAttribute("encryptionService", encryptionService);
        return "home";
    }

    @PostMapping
    public String newFile(
            Authentication authentication,
            @ModelAttribute("fileForm") FileForm fileForm,
            Model model) throws IOException {
        int userId = userService.getUserId(authentication.getName());
        String result = fileService.createFile(fileForm, userId);

        if(result.equals("duplicated")){
            model.addAttribute("result", "error");
            model.addAttribute("message", "You have uploaded one file with the same name! Please rename.");
        } else {
            model.addAttribute("result", result);
        }
        return "result";
    }

    //this one might be useful
    @GetMapping(
            value = "/get-file/{fileName}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody
    byte[] getFile(@PathVariable String fileName) {
        return fileService.getFile(fileName).getFileData();
    }

    @GetMapping(value = "/delete-file/{fileName}")
    public String deleteFile(
            Authentication authentication,
            @PathVariable String fileName,
            Model model) {
        fileService.deleteFile(fileName);
        int userId = userService.getUserId(authentication.getName());
        model.addAttribute("result", "success");

        return "result";
    }
}
