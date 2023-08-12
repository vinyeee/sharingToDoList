package dev.namiga.sharingToDoList.service;

import dev.namiga.sharingToDoList.domain.Challenge;
import dev.namiga.sharingToDoList.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    public void createChallenge(Challenge challenge) {
        challengeRepository.save(challenge);
    }
}