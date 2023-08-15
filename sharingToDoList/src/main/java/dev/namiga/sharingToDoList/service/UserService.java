package dev.namiga.sharingToDoList.service;

import dev.namiga.sharingToDoList.domain.UserEntity;
import dev.namiga.sharingToDoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public UserEntity findByUserId(long userId) {
        return userRepository.findByUserId(userId);
    }

    public UserEntity findByNickName(String nickName) {
        return userRepository.findByNickName(nickName);
    }

}