package dev.namiga.sharingToDoList.controller;

import dev.namiga.sharingToDoList.domain.Challenge;
import dev.namiga.sharingToDoList.domain.Mate;
import dev.namiga.sharingToDoList.domain.To_do_list;
import dev.namiga.sharingToDoList.domain.User;
import dev.namiga.sharingToDoList.repository.ChallengeRepository;
import dev.namiga.sharingToDoList.service.MateService;
import dev.namiga.sharingToDoList.service.TodoService;
import dev.namiga.sharingToDoList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController {

    @Autowired
    private MateService mateService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private TodoService todoService;

    @PostMapping("/todopage/{challengeId}")
    //userId가져오는 방법 찾아야 함.
    public String getTodoPage(Model model, @PathVariable("challengeID") Challenge challengeId) {
        User test = userService.findByUserId(2); //임의 설정
        List<Mate> mates = mateService.findMatesByUserId(test);
        List<String> tasks = new ArrayList<>();

        if(mates == null){
            String msg = "챌린지를 함께할 투두메이트를 설정하세요!";
            System.out.println(msg);
        }else {
            for (Mate mate : mates) {
                System.out.println(mate.getMateNickname());
                User mateUser = userService.findByNickName(mate.getMateNickname()); //메이트의 사용자 정보 접근
                //challenge ID를 어떻게 전달하지?->maipage에서 넘어올때 challengeId받아오도록 구현 예정
                List<To_do_list> mateTasks =  todoService.findByUserIdAndChallengeId(mateUser.getUserId(),challengeId);
                //현재 챌린지 정보와 메이트 userId를 이용해 to_do_list 받아오기
                model.addAttribute("todoItems", mateTasks);
                }

        }

        return "todopage";
    }

    @PostMapping("/add-todo")
    public ResponseEntity<String> addTodo(@RequestBody String todoText) {
        // 이용자의 userId, challengeId를 어떻게 전달하지?
        // text todo_id, complete, details, challenge_id, user_id
        To_do_list to_do_list = new To_do_list();
        to_do_list.setDetails(todoText);
        to_do_list.setComplete("진행 중"); //클릭하면 db에 반영되는 controller mapping 따로 필요
        return ResponseEntity.ok("Todo added successfully");
    }
    //클릭하면 db에 "완료"로 반영하는 controller 개별로 필요함
}
