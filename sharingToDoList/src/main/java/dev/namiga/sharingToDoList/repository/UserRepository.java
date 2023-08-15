package dev.namiga.sharingToDoList.repository;

import dev.namiga.sharingToDoList.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(long userId);

    UserEntity findByNickName(String nickName);

}