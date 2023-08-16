package dev.namiga.sharingToDoList.controller;


import dev.namiga.sharingToDoList.domain.ChallengeEntity;
import dev.namiga.sharingToDoList.domain.MateEntity;
import dev.namiga.sharingToDoList.domain.UserEntity;
import dev.namiga.sharingToDoList.service.MateService;
import dev.namiga.sharingToDoList.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainpageController {
    @Autowired
    private UserService userService;

    @Autowired
    private MateService mateService;

    @GetMapping ("/mainpage")
    public String mainPage(Model model) {
        UserEntity memb = userService.findByUserId(2); //test를 위해 2 넣음(userId전달법 필요)
        String userIntro = memb.getIntroduce();
        String userNickname = memb.getNickName();

        List<ChallengeEntity > challenges = memb.getChallenge();
        List<MateEntity > mates = memb.getMate();
        Map<String, String> mateInfoMap = new HashMap<>();

        for(MateEntity  mate: mates){
            String mateNickname = mate.getMateNickname();
            UserEntity  mateUser = userService.findByNickName(mateNickname);
            String mateIntro = mateUser.getIntroduce();
            mateInfoMap.put(mateNickname, mateIntro);
        }

        model.addAttribute("userIntro",userIntro);
        model.addAttribute("userNickname",userNickname);
        model.addAttribute("challenges", challenges);
        model.addAttribute("mateInfoMap", mateInfoMap);

        return "mainpage";
    }

    @GetMapping("/searchmate")
    public String searchMate(@RequestParam("mateNickname") String mateNickname, Model model) {
        UserEntity  matchingUser = userService.findByNickName(mateNickname);

        if(matchingUser != null){
            MateEntity mate = new MateEntity();
            UserEntity  usertemp = userService.findByUserId(2); //여기에 userId어떻게 전달할지 고민중(임의로 2전달)

            mate.setUserId(usertemp); //여기에 userId어떻게 전달할지 고민중(임의로 2전달)
            mate.setMateNickname(mateNickname);
            mate.setTogether("no");
            mateService.saveMate(mate);
            model.addAttribute("errorMessage", "To-do메이트 추가 완료");
        }else{
            model.addAttribute("errorMessage", "해당 닉네임의 사용자가 없습니다.");
        }
        return "mainpage";
    }
}
