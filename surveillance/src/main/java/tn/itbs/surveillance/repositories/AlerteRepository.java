package tn.itbs.surveillance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.itbs.surveillance.entity.Alerte;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AlerteRepository extends JpaRepository<Alerte, Long> {
    List<Alerte> findByNiveauGravite(String niveauGravite);
    List<Alerte> findByDateDetectionBetween(LocalDateTime debut, LocalDateTime fin);
    List<Alerte> findByDateDetectionAfter(LocalDateTime date);
    List<Alerte> findByType(String type);
}

