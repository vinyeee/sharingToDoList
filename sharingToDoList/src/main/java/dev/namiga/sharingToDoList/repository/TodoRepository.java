package dev.namiga.sharingToDoList.repository;

import dev.namiga.sharingToDoList.domain.ChallengeEntity;
import dev.namiga.sharingToDoList.domain.ToDoListEntity;
import dev.namiga.sharingToDoList.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<ToDoListEntity, Long> {

    List<ToDoListEntity> findByUserIdAndChallengeId(UserEntity userId, ChallengeEntity challengeId);

}
