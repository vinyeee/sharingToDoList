package dev.namiga.sharingToDoList.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    @GetMapping("signup")
    public String singupPage() {
        return "signup";
    }

    @GetMapping("login")
    public String loginPage() {
        return "login";
    }
}
