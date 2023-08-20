package dev.namiga.sharingToDoList.service;

import dev.namiga.sharingToDoList.domain.ChallengeEntity;
import dev.namiga.sharingToDoList.domain.MateEntity;
import dev.namiga.sharingToDoList.domain.ToDoListEntity;
import dev.namiga.sharingToDoList.domain.UserEntity;
import dev.namiga.sharingToDoList.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoRepository toDoRepository;

    public List<ToDoListEntity> findByUserIdAndChallengeId(UserEntity userId, ChallengeEntity challengeId){
        return todoRepository.findByUserIdAndChallengeId(userId, challengeId);
    }

    public void saveMate(ToDoListEntity to_do_list) {
        todoRepository.save(to_do_list);
    }

    public ToDoListEntity findByChallengeIdAndUserIdAndDetails(ChallengeEntity challengeId, UserEntity userId, String details) {
        return todoRepository.findByChallengeIdAndUserIdAndDetails(challengeId,userId,details);
    }

    public int getCompletedCountByChallengeIdAndUserId(ChallengeEntity challengeId, UserEntity userId) {
        List<ToDoListEntity> toDoList = toDoRepository.findByChallengeIdAndUserId(challengeId, userId);

        int completedCount = (int) toDoList.stream()
                .filter(entry -> entry.getComplete().equals("완료"))
                .count();

        return completedCount;
    }
}
