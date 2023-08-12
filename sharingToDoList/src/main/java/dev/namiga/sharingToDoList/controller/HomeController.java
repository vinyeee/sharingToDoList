package dev.namiga.sharingToDoList.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "start";
    }

    @GetMapping("/mainpage")
    public String mainPage() {
        return "mainpage";
    }

    @GetMapping("/makechallenge")
    public String makeChallenge() {
        return "makechallenge";
    }
}
