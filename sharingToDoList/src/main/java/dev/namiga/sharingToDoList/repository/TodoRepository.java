package dev.namiga.sharingToDoList.repository;

import dev.namiga.sharingToDoList.domain.Challenge;
import dev.namiga.sharingToDoList.domain.To_do_list;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository {

    List<To_do_list> findByUserIdAndChallengeId(long userId, Challenge challengeId);

}
