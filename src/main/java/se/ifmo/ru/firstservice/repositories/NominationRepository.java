package se.ifmo.ru.firstservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ifmo.ru.firstservice.model.Nomination;

@Repository
public interface NominationRepository extends JpaRepository<Nomination, Long> {
}