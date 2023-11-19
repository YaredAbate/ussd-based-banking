package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
public class HomeController {
    private final MessageSource messageSource;

    public HomeController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    @GetMapping("/greet")
    public String greet(Model model, Locale locale) {
        String greetingMessage = messageSource.getMessage("greeting.message", null, locale);
        model.addAttribute("greeting", greetingMessage);
        return "greet";
    }

//...

}
