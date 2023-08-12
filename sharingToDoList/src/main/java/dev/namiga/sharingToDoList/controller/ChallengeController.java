package dev.namiga.sharingToDoList.controller;

import dev.namiga.sharingToDoList.domain.Challenge;
import dev.namiga.sharingToDoList.domain.Mate;
import dev.namiga.sharingToDoList.domain.User;
import dev.namiga.sharingToDoList.repository.UserRepository;
import dev.namiga.sharingToDoList.service.ChallengeService;
import dev.namiga.sharingToDoList.service.MateService;
import dev.namiga.sharingToDoList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import java.security.Principal;

import java.util.List;


@Controller
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private UserService userService;

    @GetMapping("/find-mates")
//    public String findMates(Model model, Principal principal) {
    public String findMates(Model model, @RequestParam long userId) {
//        long userId = Long.parseLong(principal.getName());
        User user = userService.findByUserId(userId);

        List<Mate> mates = MateService.findMatesByUserId(user);

        model.addAttribute("mates", mates);
        model.addAttribute("challenge", new Challenge());

        return "makeChallenge";
    }


    @PostMapping("/create-challenge")
    public String createChallenge(@RequestParam String selectedMate,
                                  @RequestParam String quest,
                                  @RequestParam Integer prize,
                                  @RequestParam Integer time) {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            String userId = userDetails.getUsername(); //login 후 유저 정보 받아올 때 사용
//
            User user = userService.findByUserId(1);

            if (user != null) {
                Challenge challenge = new Challenge();
                challenge.setUserId(user);
                challenge.setMate(selectedMate);
                challenge.setQuest(quest);
                challenge.setPrize(prize);
                challenge.setTime(time);
                challenge.setStatus("대기 중");   // 디폴트값
                challenge.setStartTime(null);   //시작 누르기 전 null
                challenge.setEndTime(null);     //시작 누르기 전 null
                challenge.setPermit(null);      //시작 누르기 전 null
                challenge.setResult(null);      //시작 누르기 전 null

                challengeService.createChallenge(challenge);

                return "mainpage";
            } else {

                return "alertPage"; // 에러페이지 생성 필요
            }
        }
//        return "makeChallenge";
//    }
}



