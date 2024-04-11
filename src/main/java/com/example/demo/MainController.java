package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
    @RequestMapping("/home")
    public String home() {
        return "home.html";
    }

    @RequestMapping("/user")
    public String userWithQueryParam(Model model, @RequestParam(required = false) String color) {
        if(color == null) {
            color = "purple";
        }
        model.addAttribute("username", "pdmnhn");
        model.addAttribute("color", color);
        return "user.html";
    }

    @RequestMapping("/user/{color}")
    public String userWithPathVariable(Model model, @PathVariable String color) {
        model.addAttribute("username", "pdmnhn");
        model.addAttribute("color", color);
        return "user.html";
    }
}
