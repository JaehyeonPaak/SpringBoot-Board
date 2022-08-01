package com.example.springboot_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String greetings(Model model) {
        model.addAttribute("username", "Jaehyeon");
        return "greeting";
    }

    @GetMapping("/bye")
    public String goodbye(Model model) {
        model.addAttribute("username", "Jaehyeon");
        return "goodbye";
    }
}
