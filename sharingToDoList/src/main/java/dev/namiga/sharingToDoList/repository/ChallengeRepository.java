package dev.namiga.sharingToDoList.repository;

import dev.namiga.sharingToDoList.domain.ChallengeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Long> {

    ChallengeEntity findByChallengeId(long challengeId);

    ChallengeEntity findPrizeByChallengeId(Long challengeId);
}
