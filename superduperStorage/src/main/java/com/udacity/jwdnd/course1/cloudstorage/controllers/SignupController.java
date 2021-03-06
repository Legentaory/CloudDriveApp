package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.pojos.User;
import com.udacity.jwdnd.course1.cloudstorage.DAOs.UserDao;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller()
@RequestMapping("/signup")
public class SignupController {

    @Resource
    private UserService userService;

    @GetMapping()
    public String signupView() {
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute User user, Model model) {

        String errorMsg = userService.createUser(user);

        if (errorMsg == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", errorMsg);
        }

        return "signup";
    }
}
