package dev.namiga.sharingToDoList.service;

import java.util.List;
import dev.namiga.sharingToDoList.domain.Mate;
import dev.namiga.sharingToDoList.domain.User;
import dev.namiga.sharingToDoList.repository.MateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MateService {

    @Autowired
    private MateRepository mateRepository;

    public List<Mate> findMatesByUserId(User userId) {
        return mateRepository.findMatesByUserId(userId);
    }


}
