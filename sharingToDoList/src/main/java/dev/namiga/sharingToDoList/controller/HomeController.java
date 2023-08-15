package dev.namiga.sharingToDoList.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("sharingToDo")
public class HomeController {
    @GetMapping
    public String home() {
        return "start";
    }

    @GetMapping("/main")
    public String mainPage() {
        return "mainpage";
    }

//    @GetMapping("/todopage")   //TodoController에서 작업중
//    public String todoPage() {
//        return "todopage";
//    }
}
