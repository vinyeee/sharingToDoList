package dev.namiga.sharingToDoList.controller;


import dev.namiga.sharingToDoList.domain.ChallengeEntity;
import dev.namiga.sharingToDoList.domain.UserEntity;
import dev.namiga.sharingToDoList.repository.TodoRepository;
import dev.namiga.sharingToDoList.service.ChallengeService;
import dev.namiga.sharingToDoList.service.TodoService;
import dev.namiga.sharingToDoList.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AnalysisController {

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private UserService userService;

    @Autowired
    private TodoService todoService;



    @GetMapping("analysispage")
    public String calculateCumulativePrizeStatistics(Model model) {
        Long userId = 2L; // 임의 설정
        List<ChallengeEntity> challengeIds = userService.findByUserId(userId).getChallenge();
        Map<String, Integer> challegePrize = new HashMap<>();
        Map<String, String> winners = new HashMap<>(); // Store winners' userIds for each challenge
        Map<String, Integer> winnerTotalPrizes = new HashMap<>();

        for (ChallengeEntity challenge : challengeIds) {
            Long challengeId = challenge.getChallengeId();
            ChallengeEntity currentPrize = challengeService.findPrizeByChallengeId(challengeId);
            String mateNick= challenge.getMate();
            String userNick = userService.findByUserId(userId).getNickName();
            UserEntity mate = userService.findByNickName(mateNick);

            int userComplete = todoService.getCompletedCountByChallengeIdAndUserId(challenge, userService.findByUserId(userId));
            int mateComplete = todoService.getCompletedCountByChallengeIdAndUserId(challenge, mate);

            System.out.println(userComplete);
            System.out.println(mateComplete);

            if (userComplete > mateComplete) {
                challegePrize.put(challenge.getQuest(), currentPrize.getPrize());
                winners.put(challenge.getQuest(), userNick);
                winnerTotalPrizes.put(userNick,currentPrize.getPrize());
                winnerTotalPrizes.put(mateNick,-currentPrize.getPrize());

            } else if (mateComplete > userComplete) {
                challegePrize.put(challenge.getQuest(), currentPrize.getPrize());
                winners.put(challenge.getQuest(), mateNick);
                winnerTotalPrizes.put(mateNick,currentPrize.getPrize());
                winnerTotalPrizes.put(userNick,-currentPrize.getPrize());
            }
        }

        model.addAttribute("WinTotalPrize", winnerTotalPrizes);
        model.addAttribute("challengePrize", challegePrize);
        model.addAttribute("winners", winners);
        return "analysispage";
    }
}
