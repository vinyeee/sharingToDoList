package dev.namiga.sharingToDoList.repository;

import dev.namiga.sharingToDoList.domain.ChallengeEntity;
import dev.namiga.sharingToDoList.domain.ToDoListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<ToDoListEntity,Long> {

    List<ToDoListEntity> findByUserIdAndChallengeId(long userId, ChallengeEntity challengeId);

}
