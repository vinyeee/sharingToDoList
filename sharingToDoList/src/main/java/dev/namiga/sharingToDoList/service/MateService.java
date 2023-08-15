package dev.namiga.sharingToDoList.service;

import java.util.List;
import dev.namiga.sharingToDoList.domain.MateEntity;
import dev.namiga.sharingToDoList.domain.UserEntity;
import dev.namiga.sharingToDoList.repository.MateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MateService {

    @Autowired
    private MateRepository mateRepository;

    public List<MateEntity> findMatesByUserId(UserEntity userId) {
        return mateRepository.findMatesByUserId(userId);
    }


}
