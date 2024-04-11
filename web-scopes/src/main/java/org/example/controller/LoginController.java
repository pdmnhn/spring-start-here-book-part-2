package org.example.controller;

import org.example.service.LoggedUserManagementService;
import org.example.service.LoginCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.example.model.LoginProcessor;


@Controller
public class LoginController {
    private final LoginProcessor loginProcessor;
    private final LoggedUserManagementService loggedUserManagementService;

    @Autowired
    public LoginController(LoginProcessor loginProcessor, LoggedUserManagementService loggedUserManagementService) {
        this.loginProcessor = loginProcessor;
        this.loggedUserManagementService = loggedUserManagementService;
        System.out.println(LoginController.class + " instantiated!");
    }

    @GetMapping("/")
    public String loginGet() {
        if(loggedUserManagementService.getUsername() != null) {
            return "redirect:/main";
        }
        return "login.html";
    }

    @PostMapping("/")
    public String loginPost(
            Model model,
            @RequestParam String username,
            @RequestParam String password) {
        loginProcessor.setUsername(username);
        loginProcessor.setPassword(password);
        boolean loggedIn = loginProcessor.login();
        if(loggedIn) {
            return "redirect:/main";
        }
        model.addAttribute("message", "Login failed!");
        return "login.html";
    }
}
