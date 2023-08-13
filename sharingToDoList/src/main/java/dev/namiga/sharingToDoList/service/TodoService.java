package dev.namiga.sharingToDoList.service;

import dev.namiga.sharingToDoList.domain.Challenge;
import dev.namiga.sharingToDoList.domain.To_do_list;
import dev.namiga.sharingToDoList.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<To_do_list> findByUserIdAndChallengeId(long userId, Challenge challengeId){
        return todoRepository.findByUserIdAndChallengeId(userId, challengeId);
    }
}
