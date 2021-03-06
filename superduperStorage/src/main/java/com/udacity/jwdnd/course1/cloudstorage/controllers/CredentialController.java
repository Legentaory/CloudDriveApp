package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.pojos.*;
import com.udacity.jwdnd.course1.cloudstorage.pojos.data.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.pojos.data.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.pojos.data.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.DAOs.CredentialDao;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.services.securityservices.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.DAOs.UserDao;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequestMapping("credential")
public class CredentialController {

    @Resource
    private CredentialService credentialService;

    @Resource
    private EncryptionService encryptionService;

    @Resource
    private UserService userService;

    @PostMapping("add-credential")
    public String newCredential(
            Authentication authentication,
            @ModelAttribute("newCredential") CredentialForm credentialForm,
            Model model) {
        String userName = authentication.getName();
        int userId = userService.getUserId(userName);

        String result = credentialService.createCredential(credentialForm, userId);
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("result", result);

        return "result";
    }

    @GetMapping(value = "/get-credential/{credentialId}")
    public Credential getCredential(@PathVariable Integer credentialId) {
        return credentialService.getCredential(credentialId);
    }

    @GetMapping(value = "/delete-credential/{credentialId}")
    public String deleteCredential(
            Authentication authentication, @PathVariable Integer credentialId,
            @ModelAttribute("newCredential") CredentialForm newCredential,
            Model model) {
        String result = credentialService.deleteCredential(credentialId);
        model.addAttribute("result", result);

        return "result";
    }
}
