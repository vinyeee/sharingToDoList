package dev.namiga.sharingToDoList.repository;

import java.util.List;
import dev.namiga.sharingToDoList.domain.Mate;
import dev.namiga.sharingToDoList.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateRepository extends JpaRepository<Mate, String> {
    List<Mate> findMatesByUserId(User userId);

    List<Mate> findByUserId(User userId);
}
