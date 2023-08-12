package dev.namiga.sharingToDoList.service;

import dev.namiga.sharingToDoList.domain.User;
import dev.namiga.sharingToDoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User findByUserId(long userId) {
        return userRepository.findByUserId(userId);
    }

}