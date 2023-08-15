package dev.namiga.sharingToDoList.repository;

import dev.namiga.sharingToDoList.domain.ChallengeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Long> {

}
