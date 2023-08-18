package dev.namiga.sharingToDoList.service;

import dev.namiga.sharingToDoList.domain.ChallengeEntity;
import dev.namiga.sharingToDoList.domain.UserEntity;
import dev.namiga.sharingToDoList.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    public void createChallenge(ChallengeEntity challenge) {
        challengeRepository.save(challenge);
    }

    public ChallengeEntity findByChallengeId(long challengeId) {
        return challengeRepository.findByChallengeId(challengeId);
    }

}