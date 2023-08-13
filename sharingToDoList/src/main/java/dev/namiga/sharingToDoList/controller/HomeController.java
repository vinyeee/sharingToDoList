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

    @GetMapping("/signup")
    public String singupPage() {
        return "signup";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/mainpage")
    public String mainPage() {
        return "mainpage";
    }

    @GetMapping("/todopage")
    public String todoPage() {
        return "todopage";
    }
}
