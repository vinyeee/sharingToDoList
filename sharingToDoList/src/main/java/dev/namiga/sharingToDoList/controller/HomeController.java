package dev.namiga.sharingToDoList.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "start";
    }

    @GetMapping("/main")
    public String toDoMain(){
        return "mainpage";
    }

    @GetMapping("/challenge")
    public String makeChallenge(){
        return "makechallenge";
}
