package dev.namiga.sharingToDoList.repository;

import java.util.List;
import dev.namiga.sharingToDoList.domain.MateEntity;
import dev.namiga.sharingToDoList.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateRepository extends JpaRepository<MateEntity, String> {
    List<MateEntity> findMatesByUserId(UserEntity userId);

    List<MateEntity> findByUserId(UserEntity userId);

}
