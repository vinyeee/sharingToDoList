package dev.namiga.sharingToDoList.controller;

import com.nimbusds.jose.shaded.json.JSONObject;
import dev.namiga.sharingToDoList.domain.ChallengeEntity;
import dev.namiga.sharingToDoList.domain.MateEntity;
import dev.namiga.sharingToDoList.domain.ToDoListEntity;
import dev.namiga.sharingToDoList.domain.UserEntity;
import dev.namiga.sharingToDoList.repository.ChallengeRepository;
import dev.namiga.sharingToDoList.repository.UserRepository;
import dev.namiga.sharingToDoList.service.ChallengeService;
import dev.namiga.sharingToDoList.service.MateService;
import dev.namiga.sharingToDoList.service.TodoService;
import dev.namiga.sharingToDoList.service.UserService;
import lombok.Getter;
import lombok.Setter;
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
import java.util.Map;

@Controller
public class TodoController {

    @Autowired
    private MateService mateService;

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoService todoService;

    @GetMapping("/todopage/{challengeId}")
    //userId가져오는 방법 찾아야 함.
    public String getTodoPage(Model model, @PathVariable("challengeId") ChallengeEntity challengeId) {
        UserEntity test = userService.findByUserId(2); //임의 설정
        String userNick = test.getNickName();
        String challengeName = challengeId.getQuest();
        Integer challengePrize = challengeId.getPrize();
        List<MateEntity> mates = mateService.findMatesByUserId(test);
        List<String> tasks = new ArrayList<>();

        List<ToDoListEntity> userTasks = todoService.findByUserIdAndChallengeId(test,challengeId);
        model.addAttribute("userTodos",userTasks);

        if(mates == null){
            String msg = "챌린지를 함께할 투두메이트를 설정하세요!";
            System.out.println(msg);
        }else {
            for (MateEntity mate : mates) {
                String mateNick = mate.getMateNickname();
                UserEntity mateUser = userService.findByNickName(mateNick); //메이트의 사용자 정보 접근

                List<ToDoListEntity> mateTasks =  todoService.findByUserIdAndChallengeId(mateUser,challengeId);
                //현재 챌린지 정보와 메이트 userId를 이용해 to_do_list 받아오기
                model.addAttribute("mymateNick",mateNick);
                model.addAttribute("todoItems", mateTasks);
            }

        }

        model.addAttribute("userNick",userNick);
        model.addAttribute("challengeName",challengeName);
        model.addAttribute("challengePrize",challengePrize);

        return "todopage";
    }


    @PostMapping("/add-todo")
    public ResponseEntity<String> addTodo(@RequestBody Map<String, Object> requestBody) {
        String userNick = (String) requestBody.get("userNick");
        Long challengeId = ((Number) requestBody.get("challengeId")).longValue();
        String todoText = (String) requestBody.get("todoText");

        ChallengeEntity challengeEntityId = challengeService.findByChallengeId(challengeId);

        UserEntity test = userService.findByUserId(2); //임의 설정
        ToDoListEntity to_do_list = new ToDoListEntity();
        to_do_list.setUserId(test);
        to_do_list.setChallengeId(challengeEntityId);
        to_do_list.setDetails(todoText);
        to_do_list.setComplete("진행 중");
       //클릭하면 db에 반영되는 controller mapping 따로 필요

        todoService.saveMate(to_do_list);
        return ResponseEntity.ok("{\"message\": \"Todo added successfully\"}");
    }

    @PostMapping("/update-complete/{challengeId}")
    public ResponseEntity<String> updateCompleteStatus(
            @PathVariable Long challengeId,
            @RequestBody Map<String, Object> requestBody) {

        UserEntity test = userService.findByUserId(2);
        String details = (String) requestBody.get("details");

        boolean checked = (boolean) requestBody.get("checked");
        // Fetch the ToDoListEntity using challengeId, currentUser, and details
        ChallengeEntity challengeIdNew = challengeService.findByChallengeId(challengeId);
        ToDoListEntity toDoListEntity = todoService.findByChallengeIdAndUserIdAndDetails(challengeIdNew, test, details);
        if (toDoListEntity != null) {
            toDoListEntity.setComplete(checked ? "완료" : "진행 중");
            todoService.saveMate(toDoListEntity);
            return ResponseEntity.ok("{\"message\": \"Complete status updated\"}");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
